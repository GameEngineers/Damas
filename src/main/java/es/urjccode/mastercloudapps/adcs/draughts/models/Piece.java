package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

public abstract class Piece {

	protected Color color;
	private static String[] CODES = {"b", "n"};

	Piece(Color color) {
		assert color != null;
		this.color = color;
	}

	Error isCorrectMovement(List<Piece> betweenDiagonalPieces, int pair, Coordinate... coordinates){
		assert coordinates[pair] != null;
		assert coordinates[pair + 1] != null;
		if (!coordinates[pair].isOnDiagonal(coordinates[pair + 1]))
			return Error.NOT_DIAGONAL;
		for(Piece piece : betweenDiagonalPieces)
			if (this.color == piece.getColor())
				return Error.COLLEAGUE_EATING;
		return this.isCorrectDiagonalMovement(betweenDiagonalPieces.size(), pair, coordinates);
	}

	abstract Error isCorrectDiagonalMovement(int amountBetweenDiagonalPieces, int pair, Coordinate... coordinates);

	boolean isLimit(Coordinate coordinate) {
		return coordinate.isFirst() && this.getColor() == Color.WHITE
				|| coordinate.isLast() && this.getColor() == Color.BLACK;
	}

	boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE)
			return difference > 0;
		return difference < 0;
	}

	public Color getColor() {
		return this.color;
	}

	@Override
	public String toString() {
		return this.getCode();
	}

	public String getCode(){
		return Piece.CODES[this.color.ordinal()];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		Piece other = (Piece) obj;
		if (color != other.color)
			return false;
		return true;
	}

}