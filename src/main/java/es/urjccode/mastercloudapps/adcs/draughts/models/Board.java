package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Board {

    private Pawn[][] pawns;

    Board() {
        this.pawns = new Pawn[Coordinate.getDimension()][Coordinate.getDimension()];
        for (int i = 0; i < Coordinate.getDimension(); i++)
            for (int j = 0; j < Coordinate.getDimension(); j++)
                this.pawns[i][j] = null;
    }

    Pawn getPiece(Coordinate coordinate) {
        assert coordinate != null;
        return this.pawns[coordinate.getRow()][coordinate.getColumn()];
    }

    void put(Coordinate coordinate, Pawn pawn) {
        this.pawns[coordinate.getRow()][coordinate.getColumn()] = pawn;
    }

    Pawn remove(Coordinate coordinate) {
        assert this.getPiece(coordinate) != null;
        Pawn pawn = this.getPiece(coordinate);
        this.put(coordinate, null);
        return pawn;
    }

    void move(Coordinate origin, Coordinate target) {
        assert this.getPiece(origin) != null;
        this.put(target, this.remove(origin));
    }

    List<Pawn> getBetweenDiagonalPieces(Coordinate origin, Coordinate target) {
        List<Pawn> betweenDiagonalPawns = new ArrayList<Pawn>();
        if (origin.isOnDiagonal(target))
            for (Coordinate coordinate : origin.getBetweenDiagonalCoordinates(target)) {
                Pawn pawn = this.getPiece(coordinate);
                if (pawn != null)
                    betweenDiagonalPawns.add(pawn);
            }
        return betweenDiagonalPawns;
    }

    Color getColor(Coordinate coordinate) {
        final Pawn pawn = this.getPiece(coordinate);
        if (pawn == null)
            return null;
        return pawn.getColor();
    }

    boolean isEmpty(Coordinate coordinate) {
        return this.getPiece(coordinate) == null;
    }

    @Override
    public String toString() {
        String string = "";
        string += this.toStringHorizontalNumbers();
        for (int i = 0; i < Coordinate.getDimension(); i++)
            string += this.toStringHorizontalPiecesWithNumbers(i);
        string += this.toStringHorizontalNumbers();
        return string;
    }

    private String toStringHorizontalNumbers() {
        String string = " ";
        for (int j = 0; j < Coordinate.getDimension(); j++)
            string += j;
        return string + "\n";
    }

    private String toStringHorizontalPiecesWithNumbers(int row) {
        String string = " " + row;
        for (int j = 0; j < Coordinate.getDimension(); j++) {
            Pawn pawn = this.getPiece(new Coordinate(row, j));
            if (pawn == null)
                string += " ";
            else {
                string += pawn;
            }
        }
        return string + row + "\n";
    }

    public List<Coordinate> getCoordinatesWithColorCanEat(Color color, Coordinate lastMoveCoordinate) {
        assert color != null;
        List<Coordinate> piecesCanEat = new ArrayList<Coordinate>();
        List<Coordinate> coordinatesWithActualColor = getCoordinatesWithActualColor(color);
        for (Coordinate coordinate : coordinatesWithActualColor) {
            Pawn pawn = getPiece(coordinate);
            if (!lastMoveCoordinate.equals(coordinate) && this.canEat(pawn, coordinate))
                piecesCanEat.add(coordinate);
        }
        return piecesCanEat;
    }

    private boolean canEat(Pawn pawn, Coordinate coordinate) {
        List<Coordinate> diagonalCoordinates = coordinate.getDiagonalCoordinates(2);
        for (Coordinate diagonalCoordinate : diagonalCoordinates) {
            Coordinate[] coordinates = {coordinate, diagonalCoordinate};
            if (canMoveEating(pawn, coordinates))
                return true;
        }
        return false;
    }

    private boolean canMoveEating(Pawn pawn, Coordinate... coordinates) {
        List<Pawn> betweenDiagonalPawns = getBetweenDiagonalPieces(coordinates[0], coordinates[coordinates.length - 1]);
        Error error = pawn.isCorrectMovement(betweenDiagonalPawns, 0, coordinates);
        if (error == null && !betweenDiagonalPawns.isEmpty() && isEmpty(coordinates[coordinates.length - 1]))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(pawns);
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
        Board other = (Board) obj;
        if (!Arrays.deepEquals(pawns, other.pawns))
            return false;
        return true;
    }

    List<Coordinate> getCoordinatesWithActualColor(Color color) {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        for (int i = 0; i < Coordinate.getDimension(); i++) {
            for (int j = 0; j < Coordinate.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Pawn pawn = getPiece(coordinate);
                if (pawn != null && pawn.getColor() == color)
                    coordinates.add(coordinate);
            }
        }
        return coordinates;
    }
}
