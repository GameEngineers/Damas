package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CorrectMovesDraughtGameTest extends GameTest {

    private void assertMove(Coordinate... coordinates){
        assertNull(this.game.move(coordinates));
        assertEquals(this.game, this.expectedGame);
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteCorrectMovementThenOk() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "B       ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "        ",
            " B      ",
            "        ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(5, 0), 
            new Coordinate(4, 1)
        );
    }

    @Test
    public void testGivenGameWhenMoveWithBlackCorrectMovementThenOk() {
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            "   N    ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ");
        this.setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "    N   ",
            "        ",
            "b       ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(2, 3), 
            new Coordinate(3, 4));
    }

    @Test
    public void testGivenGameWhenMoveWithBlackEatingThenOk() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "  n     ",
            " B      ",
            "        ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "   B    ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        this.assertMove(new Coordinate(4, 1), new Coordinate(2, 3));
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteEatingThenOk() {
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "N       ",
            " b      ",
            "        ",
            "        ",
            "        ");
        this.setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  N     ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(3, 0), 
            new Coordinate(5, 2));
    }

    @Test
    public void testGivenGameWhenMoveWithBlackTwoEatingThenOk() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "   n    ",
            "        ",
            " n      ",
            "B       ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "    B   ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(5, 0), 
            new Coordinate(3, 2),
            new Coordinate(1, 4));
    }

    @Test
    public void testGivenGameWhenMoveWithWhiteTwoEatingThenOk() {
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            " N      ",
            "  b     ",
            "        ",
            "    b   ",
            "        ",
            "        ");
        this.setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "     N  ",
            "        ");
        this.assertMove(
            new Coordinate(2, 1), 
            new Coordinate(4, 3),
            new Coordinate(6, 5));
    }

    
    @Test
    public void testGivenGameWhenMoveWHITEThenWithoutNOT_ADVANCED() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "       B",
            "        ",
            "        ",
            "        ");
        setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "    B   ");
        assertMove(
            new Coordinate(4, 7), 
            new Coordinate(7, 4));
    }

    @Test
    public void testGivenGameWhenMoveBLACKThenWithoutNOT_ADVANCED() {
        setGame(Color.BLACK,
            "        ",
            "N       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        setExpectedGame(Color.WHITE,
            " N      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertMove(
            new Coordinate(1, 0), 
            new Coordinate(0, 1));
    }

    
    @Test
    public void testGivenGameWhenMoveWHITEThenWithoutWITHOUT_EATING() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "    B   ",
            "        ",
            "        ");
        setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "  B     ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertMove(
            new Coordinate(5, 4), 
            new Coordinate(3, 2));
    }

    @Test
    public void testGivenGameWhenMoveBLACKThenWithoutWITHOUT_EATING() {
        setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "  N     ",
            "        ",
            "    b   ",
            "        ",
            "        ");
        setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "      N ");
        assertMove(
            new Coordinate(3, 2), 
            new Coordinate(7, 6));
    }

    @Test
    public void testGivenGameWhenMoveWHITEThenWithoutTOO_MUCH_ADVANCED() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "B       ",
            "        ",
            "        ");
        setExpectedGame(Color.BLACK,
            "     B  ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertMove(
            new Coordinate(5, 0), 
            new Coordinate(0, 5));
    }

    @Test
    public void testGivenGameWhenMoveBLACKThenWithoutTOO_MUCH_ADVANCED() {
        setGame(Color.BLACK,
            "        ",
            "        ",
            " N      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "    N   ",
            "        ",
            "        ");
        assertMove(
            new Coordinate(2, 1), 
            new Coordinate(5, 4));
    }

    @Test
    public void testGivenGameWhenMoveWHITEThenWithoutTOO_MUCH_EATINGS() {
        setGame(Color.WHITE,
            "        ",
            "    n   ",
            "        ",
            "        ",
            "        ",
            "    n n ",
            " n      ",
            "B       ");
        setExpectedGame(Color.BLACK,
            "   B    ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertMove(
            new Coordinate(7, 0), 
            new Coordinate(4, 3),
            new Coordinate(6, 5),
            new Coordinate(4, 7),
            new Coordinate(0, 3));
    }
    
}