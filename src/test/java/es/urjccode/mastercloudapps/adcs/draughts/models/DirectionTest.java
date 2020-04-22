package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DirectionTest {

    @Test
    public void testGivenDirectionAndCoordinateWhenSameDirectionThenTrue(){
        assertTrue(Direction.NE.isOnDirection(new Coordinate(5,5)));
        assertTrue(Direction.SE.isOnDirection(new Coordinate(-1,1)));
        assertTrue(Direction.SW.isOnDirection(new Coordinate(-7,-7)));
        assertTrue(Direction.NW.isOnDirection(new Coordinate(3,-3)));
    }

    @Test
    public void testGivenDirectionAndCoordinateWhenSameDirectionThenFalse(){
        assertFalse(Direction.NW.isOnDirection(new Coordinate(7,7)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(3,7)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0,0)));
    }

}