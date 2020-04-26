package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

	private Board board;
	private Turn turn;
    private Random random;

	Game(Board board, Random random) {
		this.turn = new Turn();
		this.board = board;
		this.random = random;
	}

	public Game() {
		this(new Board(), new Random(System.currentTimeMillis()));
		this.reset();
	}

	public void reset() {
		for (int i = 0; i < Coordinate.getDimension(); i++)
			for (int j = 0; j < Coordinate.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Color color = Color.getInitialColor(coordinate);
				Pawn pawn = null;
				if (color != null)
					pawn = new Pawn(color);
				this.board.put(coordinate, pawn);
			}
		if (this.turn.getColor() != Color.WHITE)
			this.turn.change();
	}

	public Error move(Coordinate... coordinates) {
		Error error = null;
		List<Coordinate> removedCoordinates = new ArrayList<Coordinate>();
		int pair = 0;
		do {
			error = this.isCorrectPairMove(pair, coordinates);
			if (error == null) {
				this.pairMove(removedCoordinates, pair, coordinates);
				pair++;
			}
		} while (pair < coordinates.length - 1 && error == null);
		error = this.isCorrectGlobalMove(error, removedCoordinates, coordinates);
		if (error == null) {
            checkRemovePiece(removedCoordinates, coordinates[coordinates.length - 1]);
		    this.turn.change();
        }else
			this.unMovesUntilPair(removedCoordinates, pair, coordinates);
		return error;
	}

    private void checkRemovePiece(List<Coordinate> removedCoordinates, Coordinate lastCoordinateMove) {
        if (removedCoordinates.isEmpty()) {
            List<Coordinate> coordinatesWithPieceCanEat = board.getCoordinatesWithPieceCanEat(turn.getColor(), lastCoordinateMove);
            if (!coordinatesWithPieceCanEat.isEmpty())
                removeRandomPiece(coordinatesWithPieceCanEat);
        }
    }

    private void removeRandomPiece(List<Coordinate> coordinatesWithPieceCanEat) {
	    random.setSeed(System.currentTimeMillis());
        int PieceRandomIndex = random.nextInt(coordinatesWithPieceCanEat.size());
        board.remove(coordinatesWithPieceCanEat.get(PieceRandomIndex));
    }

    private Error isCorrectPairMove(int pair, Coordinate... coordinates) {
		assert coordinates[pair] != null;
		assert coordinates[pair + 1] != null;
		if (board.isEmpty(coordinates[pair]))
			return Error.EMPTY_ORIGIN;
		if (this.turn.getOppositeColor() == this.board.getColor(coordinates[pair]))
			return Error.OPPOSITE_PIECE;
		if (!this.board.isEmpty(coordinates[pair + 1]))
			return Error.NOT_EMPTY_TARGET;
		List<Pawn> betweenDiagonalPawns =
			this.board.getBetweenDiagonalPieces(coordinates[pair], coordinates[pair + 1]);
		return this.board.getPiece(coordinates[pair]).isCorrectMovement(betweenDiagonalPawns, pair, coordinates);
	}

	private void pairMove(List<Coordinate> removedCoordinates, int pair, Coordinate... coordinates) {
		Coordinate forRemoving = this.getBetweenDiagonalPiece(pair, coordinates);
		if (forRemoving != null) {
			removedCoordinates.add(0, forRemoving);
			this.board.remove(forRemoving);
		}
		this.board.move(coordinates[pair], coordinates[pair + 1]);
	}

	private Coordinate getBetweenDiagonalPiece(int pair, Coordinate... coordinates) {
		assert coordinates[pair].isOnDiagonal(coordinates[pair + 1]);
		List<Coordinate> betweenCoordinates = coordinates[pair].getBetweenDiagonalCoordinates(coordinates[pair + 1]);
		if (betweenCoordinates.isEmpty())
			return null;
		for (Coordinate coordinate : betweenCoordinates) {
			if (this.getPiece(coordinate) != null)
				return coordinate;
		}
		return null;
	}

	private Error isCorrectGlobalMove(Error error, List<Coordinate> removedCoordinates, Coordinate... coordinates){
		if (error != null)
			return error;
		if (coordinates.length > 2 && coordinates.length > removedCoordinates.size() + 1)
			return Error.TOO_MUCH_JUMPS;
		return null;
	}

	private void unMovesUntilPair(List<Coordinate> removedCoordinates, int pair, Coordinate... coordinates) {
		for (int j = pair; j > 0; j--)
			this.board.move(coordinates[j], coordinates[j - 1]);
		for (Coordinate removedPiece : removedCoordinates)
			this.board.put(removedPiece, new Pawn(this.getOppositeTurnColor()));
	}

	public boolean isBlocked() {
		for (Coordinate coordinate : this.board.getCoordinatesWithActualColor(this.turn.getColor()))
			if (!this.isBlocked(coordinate))
				return false;
		return true;
	}

    private boolean isBlocked(Coordinate coordinate) {
		for (int i = 1; i <= 2; i++)
			for (Coordinate target : coordinate.getDiagonalCoordinates(i))
				if (this.isCorrectPairMove(0, coordinate, target) == null)
					return false;
		return true;
	}

	public void cancel() {
		for (Coordinate coordinate : this.board.getCoordinatesWithActualColor(this.turn.getColor()))
			this.board.remove(coordinate);
		this.turn.change();
	}

	public Color getColor(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getColor(coordinate);
	}

	public Color getTurnColor() {
		return this.turn.getColor();
	}

	private Color getOppositeTurnColor() {
		return this.turn.getOppositeColor();
	}

	public Pawn getPiece(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getPiece(coordinate);
	}

	public int getDimension() {
		return Coordinate.getDimension();
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		result = prime * result + ((turn == null) ? 0 : turn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (board == null) {
			if (other.board != null)
				return false;
		} else if (!board.equals(other.board))
			return false;
		if (turn == null) {
			if (other.turn != null)
				return false;
		} else if (!turn.equals(other.turn))
			return false;
		return true;
	}

}
