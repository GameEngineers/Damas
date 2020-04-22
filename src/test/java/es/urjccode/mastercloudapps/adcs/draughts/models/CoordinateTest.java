package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CoordinateTest {

    @Test
    public void voidGivenCoordinateWhenGetDirectionThenDirection() {
        assertEquals(Direction.NE, new Coordinate(3,3).getDirection(new Coordinate(4,4)));
        assertEquals(Direction.SE, new Coordinate(3,3).getDirection(new Coordinate(2,4)));
        assertEquals(Direction.SW, new Coordinate(3,3).getDirection(new Coordinate(2,2)));
        assertEquals(Direction.NW, new Coordinate(3,3).getDirection(new Coordinate(4,2)));
        assertEquals(null, new Coordinate(3,5).getDirection(new Coordinate(4,0)));
    }

    @Test
    public void testGivenTwoCoordinatesWhenIsDiagonalThenTrue(){
        assertTrue(new Coordinate(0,0).isOnDiagonal(new Coordinate(1,1)));
        assertTrue(new Coordinate(0,0).isOnDiagonal(new Coordinate(7,7)));
        assertTrue(new Coordinate(3,4).isOnDiagonal(new Coordinate(4,5)));
        assertTrue(new Coordinate(3,4).isOnDiagonal(new Coordinate(2,5)));
        assertTrue(new Coordinate(3,4).isOnDiagonal(new Coordinate(2,3)));
        assertTrue(new Coordinate(3,4).isOnDiagonal(new Coordinate(4,3)));
        
    }

    @Test
    public void testGivenTwoCoordinatesWhenIsDiagonalThenBad(){
        assertFalse(new Coordinate(3,5).isOnDiagonal(new Coordinate(3,5)));
        assertFalse(new Coordinate(3,5).isOnDiagonal(new Coordinate(4,5)));
        assertFalse(new Coordinate(3,5).isOnDiagonal(new Coordinate(3,6)));
        assertFalse(new Coordinate(3,5).isOnDiagonal(new Coordinate(2,5)));
        assertFalse(new Coordinate(3,5).isOnDiagonal(new Coordinate(3,4)));     
    }


    @Test
    public void testGivenTwoCoordinatesWhenGetBetweenDiagonalCoordinateThenOk() {
        assertEquals(new Coordinate(1, 1), new Coordinate(2, 2).getBetweenDiagonalCoordinate(new Coordinate(0, 0)));
        assertEquals(new Coordinate(3, 1), new Coordinate(2, 2).getBetweenDiagonalCoordinate(new Coordinate(4, 0)));
        assertEquals(new Coordinate(3, 3), new Coordinate(2, 2).getBetweenDiagonalCoordinate(new Coordinate(4, 4)));
        assertEquals(new Coordinate(1, 3), new Coordinate(2, 2).getBetweenDiagonalCoordinate(new Coordinate(0, 4)));
    }

    @Test
    public void testGivenTwoCoordinatesWhenGetDiagonalDistanceThenResult() {
        assertEquals(3, new Coordinate(3, 4).getDiagonalDistance(new Coordinate(0, 7)));
        assertEquals(1, new Coordinate(0, 0).getDiagonalDistance(new Coordinate(-1, 1)));
    }

    @Test
    public void testGivenCoordinateWhenGetDiagonalCoordinatesWithOneDistanceThenAll(){
        Coordinate coordinate = new Coordinate(4,3);
        List<Coordinate> diagonalCoordinates = Arrays.asList(new Coordinate[]{
            new Coordinate(5,4),
            new Coordinate(3,4),
            new Coordinate(3,2),
            new Coordinate(5,2)
        });
        assertEquals(diagonalCoordinates, coordinate.getDiagonalCoordinates(1));
    }

    @Test
    public void testGivenCoordinateWhenGetDiagonalCoordinatesWithTwoDistanceThenAll(){
        Coordinate coordinate = new Coordinate(4,3);
        List<Coordinate> diagonalCoordinates = Arrays.asList(new Coordinate[]{
            new Coordinate(6,5),
            new Coordinate(2,5),
            new Coordinate(2,1),
            new Coordinate(6,1)
        });
        assertEquals(diagonalCoordinates, coordinate.getDiagonalCoordinates(2));
    }

    @Test
    public void testGivenCoordinateWhenGetDiagonalCoordinatesWithOneDistanceThenAny(){
        Coordinate coordinate = new Coordinate(0,1);
        List<Coordinate> diagonalCoordinates = Arrays.asList(new Coordinate[]{
            new Coordinate(1,2),
            new Coordinate(1,0),
        });
        assertEquals(diagonalCoordinates, coordinate.getDiagonalCoordinates(1));
    }

    @Test
    public void testGivenCoordinateWhenGetDiagonalCoordinatesWithTwoDistanceThenAny(){
        Coordinate coordinate = new Coordinate(0,1);
        List<Coordinate> diagonalCoordinates = Arrays.asList(new Coordinate[]{
            new Coordinate(2,3)
        });
        assertEquals(diagonalCoordinates, coordinate.getDiagonalCoordinates(2));
    }

    @Test
    public void testGivenCoordinateWhenGetDiagonalCoordinatesWithOneDistanceThenAnyAgain(){
        Coordinate coordinate = new Coordinate(7,0);
        List<Coordinate> shiftedCoordinates = Arrays.asList(new Coordinate[]{
            new Coordinate(6,1),
        });
        assertEquals(shiftedCoordinates, coordinate.getDiagonalCoordinates(1));
    }

    @Test
    public void testGivenCoordinateWhenGetDiagonalCoordinatesWithTwoDistanceThenAnyAgain(){
        Coordinate coordinate = new Coordinate(7,0);
        List<Coordinate> shiftedCoordinates = Arrays.asList(new Coordinate[]{
            new Coordinate(5,2),
        });
        assertEquals(shiftedCoordinates, coordinate.getDiagonalCoordinates(2));
    }

    @Test
    public void testGivenCoordinateWhenGetDiagonalCoordinatesThenEmpty(){
        Coordinate origin = new Coordinate(0,0);
        Coordinate target = new Coordinate(1,1);
        assertEquals(new ArrayList<Coordinate>(), origin.getBetweenDiagonalCoordinates(target));
    }

    @Test
    public void testGivenCoordinateWhenGetDiagonalCoordinatesThenNotEmpty(){
        Coordinate origin = new Coordinate(7,3);
        Coordinate target = new Coordinate(3,7);
        List<Coordinate> coordinates = Arrays.asList(
            new Coordinate(6,4),
            new Coordinate(5,5),
            new Coordinate(4,6)
        );
        assertEquals(coordinates, origin.getBetweenDiagonalCoordinates(target));
    }
}