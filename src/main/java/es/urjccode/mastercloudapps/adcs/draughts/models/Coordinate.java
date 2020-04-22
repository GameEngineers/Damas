package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

public class Coordinate {

    private int row;
    private int column;
    private static final int LOWER_LIMIT = 0;
    private static final int UPPER_LIMIT = 7;
    private static final int DIMENSION = UPPER_LIMIT + 1;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinate getInstance(String format) {
        assert format != null;
        try {
            int value = Integer.parseInt(format);
            int row = value / 10 - 1;
            int column = value % 10 - 1;
            Coordinate coordinate = new Coordinate(row, column);
            if (!coordinate.isWithIn())
                return null;
            return coordinate;
        } catch (Exception ex) {
            return null;
        }
    }

    private boolean isWithIn() {
        return Coordinate.LOWER_LIMIT <= row && row <= Coordinate.UPPER_LIMIT && Coordinate.LOWER_LIMIT <= column
                && column <= Coordinate.UPPER_LIMIT;
    }

    private Coordinate substract(Coordinate coordinate) {
        return new Coordinate(this.row - coordinate.row, this.column - coordinate.column);
    }

    private Coordinate plus(Coordinate coordinate) {
        return new Coordinate(this.row + coordinate.row, this.column + coordinate.column);
    }

    Direction getDirection(Coordinate coordinate) {
        assert coordinate != null;
        Coordinate substract = coordinate.substract(this);
        for (Direction direction : Direction.values()) 
            if (direction.isOnDirection(substract)) 
                return direction;
        return null;
    }

    boolean isOnDiagonal(Coordinate coordinate) {
        return this.getDirection(coordinate) != null;
    }

    int getDiagonalDistance(Coordinate coordinate) {
        assert this.isOnDiagonal(coordinate);
        return Math.abs(this.substract(coordinate).getRow());
    }

    Coordinate getBetweenDiagonalCoordinate(Coordinate coordinate) {
        assert this.getDiagonalDistance(coordinate) == 2;
        final Direction direction = this.getDirection(coordinate);
        return this.plus(direction.getDistanceCoordinate(1));
    }

    List<Coordinate> getBetweenDiagonalCoordinates(Coordinate coordinate){
        assert this.isOnDiagonal(coordinate);
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        final Direction direction = this.getDirection(coordinate);
        Coordinate cursor = this.plus(direction.getDistanceCoordinate(1));
        while (!cursor.equals(coordinate)){
            coordinates.add(cursor);
            cursor = cursor.plus(direction.getDistanceCoordinate(1));
        }
        return coordinates;
    }

    List<Coordinate> getDiagonalCoordinates(int level) {
        List<Coordinate> diagonalCoordinates = new ArrayList<Coordinate>();
        for (Direction direction : Direction.values()) {
            Coordinate diagonalCoordinate = this.plus(direction.getDistanceCoordinate(level));
            if (diagonalCoordinate != null && diagonalCoordinate.isWithIn())
                diagonalCoordinates.add(diagonalCoordinate);
        }
        return diagonalCoordinates;
    }

    boolean isBlack() {
        return (this.row + this.column) % 2 != 0;
    }

    public boolean isLast() {
        return this.row == Coordinate.UPPER_LIMIT;
    }

    public boolean isFirst() {
        return this.row == Coordinate.LOWER_LIMIT;
    }

    int getRow() {
        return this.row;
    }

    int getColumn() {
        return this.column;
    }

    public static int getDimension() {
        return Coordinate.DIMENSION;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
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
        Coordinate other = (Coordinate) obj;
        if (column != other.column)
            return false;
        if (row != other.row)
            return false;
        return true;
    }

}