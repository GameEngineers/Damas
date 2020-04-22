package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PieceTest {

    @Test
    public void testGivenPieceWhenIsAdvancedThenTrue(){
        assertTrue(new Pawn(Color.WHITE).isAdvanced(new Coordinate(5,0), new Coordinate(4,1)));
        assertTrue(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,1), new Coordinate(3,2)));
    }

    @Test
    public void testGivenPieceWhenNotIsAdvancedThenFalse(){
        assertFalse(new Pawn(Color.WHITE).isAdvanced(new Coordinate(5,0), new Coordinate(6,1)));
        assertFalse(new Pawn(Color.WHITE).isAdvanced(new Coordinate(5,0), new Coordinate(5,2)));
        assertFalse(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,1), new Coordinate(2,3)));
        assertFalse(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,1), new Coordinate(1,2)));
    }

}