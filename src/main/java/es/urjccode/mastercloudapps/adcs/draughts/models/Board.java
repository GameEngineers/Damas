package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Board {

    private Piece[][] pieces;

    Board() {
        this.pieces = new Piece[Coordinate.getDimension()][Coordinate.getDimension()];
        for (int i = 0; i < Coordinate.getDimension(); i++)
            for (int j = 0; j < Coordinate.getDimension(); j++)
                this.pieces[i][j] = null;
    }

    Piece getPiece(Coordinate coordinate) {
        assert coordinate != null;
        return this.pieces[coordinate.getRow()][coordinate.getColumn()];
    }

    void put(Coordinate coordinate, Piece piece) {
        this.pieces[coordinate.getRow()][coordinate.getColumn()] = piece;
    }

    Piece remove(Coordinate coordinate) {
        assert this.getPiece(coordinate) != null;
        Piece piece = this.getPiece(coordinate);
        this.put(coordinate, null);
        return piece;
    }

    void move(Coordinate origin, Coordinate target) {
        assert this.getPiece(origin) != null;
        this.put(target, this.remove(origin));
    }

    List<Piece> getBetweenDiagonalPieces(Coordinate origin, Coordinate target) {
        List<Piece> betweenDiagonalPieces = new ArrayList<Piece>();
        if (origin.isOnDiagonal(target))
            for (Coordinate coordinate : origin.getBetweenDiagonalCoordinates(target)) {
                Piece piece = this.getPiece(coordinate);
                if (piece != null)
                    betweenDiagonalPieces.add(piece);
            }
        return betweenDiagonalPieces;
    }

    Color getColor(Coordinate coordinate) {
        final Piece piece = this.getPiece(coordinate);
        if (piece == null)
            return null;
        return piece.getColor();
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
            Piece piece = this.getPiece(new Coordinate(row, j));
            if (piece == null)
                string += " ";
            else {
                string += piece;
            }
        }
        return string + row + "\n";
    }

    public List<Coordinate> getCoordinatesWithPieceCanEat(Color color, Coordinate lastMoveCoordinate) {
        assert color != null;
        List<Coordinate> piecesCanEat = new ArrayList<Coordinate>();
        List<Coordinate> coordinatesWithActualColor = getCoordinatesWithActualColor(color);
        for (Coordinate coordinate : coordinatesWithActualColor) {
            Piece piece = getPiece(coordinate);
            if (!lastMoveCoordinate.equals(coordinate) && this.canEat(piece, coordinate))
                piecesCanEat.add(coordinate);
        }
        return piecesCanEat;
    }

    private boolean canEat(Piece piece, Coordinate coordinate) {
        for (int nivel = 1; nivel < Coordinate.getDimension(); nivel++) {
            List<Coordinate> diagonalCoordinates = coordinate.getDiagonalCoordinates(nivel);
            for (Coordinate diagonalCoordinate : diagonalCoordinates) {
                Coordinate[] coordinates = {coordinate, diagonalCoordinate};
                if (canMoveEating(piece, coordinates))
                    return true;
            }
        }
        return false;
    }

    private boolean canMoveEating(Piece piece, Coordinate... coordinates) {
        List<Piece> beetweenDiagonalPieces = getBetweenDiagonalPieces(coordinates[0], coordinates[coordinates.length - 1]);
        Error error = piece.isCorrectMovement(beetweenDiagonalPieces, 0, coordinates);
        if (error == null && !beetweenDiagonalPieces.isEmpty() && isEmpty(coordinates[coordinates.length - 1]))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(pieces);
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
        if (!Arrays.deepEquals(pieces, other.pieces))
            return false;
        return true;
    }

    List<Coordinate> getCoordinatesWithActualColor(Color color) {
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        for (int i = 0; i < Coordinate.getDimension(); i++) {
            for (int j = 0; j < Coordinate.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Piece piece = getPiece(coordinate);
                if (piece != null && piece.getColor() == color)
                    coordinates.add(coordinate);
            }
        }
        return coordinates;
    }
}
