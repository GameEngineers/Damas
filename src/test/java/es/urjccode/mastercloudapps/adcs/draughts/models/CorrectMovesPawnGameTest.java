package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CorrectMovesPawnGameTest extends GameTest {

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
            "b       ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "        ",
            " b      ",
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
            "   n    ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ");
        this.setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "    n   ",
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
            " b      ",
            "        ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "   b    ",
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
            "n       ",
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
            "  n     ",
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
            "b       ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "    b   ",
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
            " n      ",
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
            "     n  ",
            "        ");
        this.assertMove(
            new Coordinate(2, 1), 
            new Coordinate(4, 3),
            new Coordinate(6, 5));
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugts(){
        this.setGame(Color.WHITE,
            "        ",
            "b       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            " B      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(1,0), 
            new Coordinate(0,1));
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts(){
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ",
            "   n    ",
            "        ");
        this.setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ",
            "        ",
            "  N     ");
        this.assertMove(
                new Coordinate(6,3), 
                new Coordinate(7,2));
    }

}