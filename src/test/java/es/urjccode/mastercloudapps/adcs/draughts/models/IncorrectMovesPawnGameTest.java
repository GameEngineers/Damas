package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IncorrectMovesPawnGameTest extends GameTest {

    private Color color;
    private String[] strings;

    protected void setGame(Color color, String... strings) {
        this.color = color;
        this.strings = strings;
        super.setGame(color, strings);
    }

    private void assertErrorMove(Error error, Coordinate... coordinates) {
        assertEquals(error, this.game.move(coordinates));
        assertEquals(new GameBuilder().color(color).rows(strings).build(), this.game);
    }

    @Test
    public void testGivenGameWhenMoveWHITEThenEMPTY_ORIGIN() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.EMPTY_ORIGIN, 
            new Coordinate(4, 3), 
            new Coordinate(3, 4));
    }

    @Test
    public void testGivenGameWhenMoveBLACKThenEMPTY_ORIGIN() {
        setGame(Color.BLACK, 
        "        ",
        "        ",
        "        ",
        "        ",
        "        ",
        "        ",
        "        ",
        "        ");
        assertErrorMove(Error.EMPTY_ORIGIN, 
            new Coordinate(4, 3), 
            new Coordinate(3, 4));
    }

    @Test
    public void testGivenGameWhenMoveWHITEThenOPPOSITE_PIECE() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.OPPOSITE_PIECE, 
            new Coordinate(2, 1), 
            new Coordinate(3, 0));
    }

    @Test
    public void testGivenGameWhenMoveBLACKThenOPPOSITE_PIECE() {
        setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ");
        assertErrorMove(Error.OPPOSITE_PIECE,
            new Coordinate(7, 2), 
            new Coordinate(6, 3));
    }

    @Test
    public void testGivenGameWhenMoveDownThenNOT_DIAGONAL() {
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL,
            new Coordinate(5, 2), 
            new Coordinate(4, 2));
    }

    @Test
    public void testGivenGameWhenMoveUpThenNOT_DIAGONAL() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  b     ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL,
            new Coordinate(5, 2), 
            new Coordinate(6, 2));
    }

    @Test
    public void testGivenGameWhenMoveRightThenNOT_DIAGONAL() {
        setGame(Color.BLACK, 
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL, 
            new Coordinate(0, 1), 
            new Coordinate(0, 2));
    }

    @Test
    public void testGivenGameWhenMoveLeftThenNOT_DIAGONAL() {
        setGame(Color.BLACK,
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL,
            new Coordinate(0, 1), 
            new Coordinate(0, 0));
    }

    @Test
    public void testGivenGameWhenMoveWHITEThenNOT_EMPTY_TARGET() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "      n ",
            "       b",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_EMPTY_TARGET,
            new Coordinate(4, 7), 
            new Coordinate(3, 6));
    }

    @Test
    public void testGivenGameWhenMoveBLACKThenNOT_EMPTY_TARGET() {
        setGame(Color.BLACK,
            " n      ",
            "n       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_EMPTY_TARGET,
            new Coordinate(0, 1), 
            new Coordinate(1, 0));
    }

    @Test
    public void testGivenGameWhenMoveWHITEEatingThenNOT_EMPTY_TARGET() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "   n    ",
            "  n     ",
            " b      ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_EMPTY_TARGET,
            new Coordinate(4, 1), 
            new Coordinate(2, 3));
    }

    @Test
    public void testGivenGameWhenMoveBLACKEatingThenNOT_EMPTY_TARGET() {
        setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "n       ",
            " b      ",
            "  b     ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_EMPTY_TARGET,
            new Coordinate(3, 0), 
            new Coordinate(5, 2));
    }

    @Test
    public void testGivenGameWhenMoveWHITEThenNOT_ADVANCED() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "       b",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_ADVANCED,
            new Coordinate(4, 7), 
            new Coordinate(5, 6));
    }

    @Test
    public void testGivenGameWhenMoveBLACKThenNOT_ADVANCED() {
        setGame(Color.BLACK,
            "        ",
            "n       ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_ADVANCED,
            new Coordinate(1, 0), 
            new Coordinate(0, 1));
    }

    
    @Test
    public void testGivenGameWhenMoveWHITEThenWITHOUT_EATING() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "    b   ",
            "        ",
            "        ");
        assertErrorMove(Error.WITHOUT_EATING,
            new Coordinate(5, 4), 
            new Coordinate(3, 2));
    }

    @Test
    public void testGivenGameWhenMoveBLACKThenWITHOUT_EATING() {
        setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "  n     ",
            "        ",
            "    b   ",
            "        ",
            "        ");
        assertErrorMove(Error.WITHOUT_EATING,
            new Coordinate(3, 2), 
            new Coordinate(5, 0));
    }

    @Test
    public void testGivenGameWhenMoveWHITEThenTOO_MUCH_ADVANCED() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "b       ",
            "        ",
            "        ");
        assertErrorMove(Error.TOO_MUCH_ADVANCED,
            new Coordinate(5, 0), 
            new Coordinate(2, 3));
    }

    @Test
    public void testGivenGameWhenMoveBLACKThenTOO_MUCH_ADVANCED() {
        setGame(Color.BLACK,
            "        ",
            "        ",
            " n      ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.TOO_MUCH_ADVANCED,
            new Coordinate(2, 1), 
            new Coordinate(5, 4));
    }

    @Test
    public void testGivenGameWhenMoveSecondUpThenNOT_DIAGONAL(){
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "   n    ",
            "  b     ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL,
            new Coordinate(5, 2), 
            new Coordinate(3, 4),
            new Coordinate(2, 4));
    }

    @Test
    public void testGivenGameWhenMoveSecondDownThenNOT_DIAGONAL(){
        setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "    n   ",
            "   b    ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL,
            new Coordinate(3, 4), 
            new Coordinate(5, 2),
            new Coordinate(4, 2));
    }

    @Test
    public void testGivenGameWhenMoveSecondRightThenNOT_DIAGONAL(){
        setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "    n   ",
            "   b    ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL,
            new Coordinate(3, 4), 
            new Coordinate(5, 2),
            new Coordinate(5, 3));
    }

    @Test
    public void testGivenGameWhenMoveSecondLeftThenNOT_DIAGONAL(){
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "    n   ",
            "   b    ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_DIAGONAL,
            new Coordinate(4, 3), 
            new Coordinate(2, 5),
            new Coordinate(1, 5));
    }

    @Test
    public void testGivenGameWhenMoveWHITESecondThenNOT_EMPTY_TARGET(){
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "    n   ",
            "   n    ",
            "        ",
            " n      ",
            "b       ");
        assertErrorMove(Error.NOT_EMPTY_TARGET,
            new Coordinate(7, 0), 
            new Coordinate(5, 2),
            new Coordinate(3, 4));
    }

    @Test
    public void testGivenGameWhenMoveBLACKSecondThenNOT_EMPTY_TARGET(){
        setGame(Color.BLACK,
            "        ",
            "  n     ",
            "   b    ",
            "        ",
            "   b    ",
            "  b     ",
            "        ",
            "        ");
        assertErrorMove(Error.NOT_EMPTY_TARGET,
            new Coordinate(1, 2), 
            new Coordinate(3, 4),
            new Coordinate(5, 2));
    }
    
    @Test
    public void testGivenGameWhenMoveBLACKEatingThenTOO_MUCH_ADVANCED(){
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "     n  ",
            "      b ",
            "        ",
            "        ");
        assertErrorMove(Error.TOO_MUCH_ADVANCED,
            new Coordinate(5, 6), 
            new Coordinate(3, 4),
            new Coordinate(0, 1));
    }

    @Test
    public void testGivenGameWhenMoveWHITESecondThenCOLLEAGUE_EATING(){
        setGame(Color.WHITE,
            "        ",
            "  b     ",
            "   b    ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.COLLEAGUE_EATING,
            new Coordinate(2, 3), 
            new Coordinate(0, 1));
    }

    @Test
    public void testGivenGameWhenMoveBLACKSecondThenCOLLEAGUE_EATING(){
        setGame(Color.BLACK,
            "        ",
            "  n     ",
            "   n    ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.COLLEAGUE_EATING,
            new Coordinate(1, 2), 
            new Coordinate(3, 4));
    }

    @Test
    public void testGivenGameWhenMoveWHITEThenTOO_MUCH_JUMPS() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            " b      ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.TOO_MUCH_JUMPS,
            new Coordinate(4, 1), 
            new Coordinate(3, 2), 
            new Coordinate(2, 3));
    }

    @Test
    public void testGivenGameWhenMoveBLACKThenTOO_MUCH_JUMPS() {
        setGame(Color.BLACK,
            "        ",
            "        ",
            "   n    ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.TOO_MUCH_JUMPS,
            new Coordinate(2, 3), 
            new Coordinate(3, 4), 
            new Coordinate(4, 3));
    }

    @Test
    public void testGivenGameWhenMoveWHITEEatingThenTOO_MUCH_JUMPS() {
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "  n     ",
            " b      ",
            "        ",
            "        ",
            "        ");
        assertErrorMove(Error.TOO_MUCH_JUMPS,
            new Coordinate(4, 1), 
            new Coordinate(2, 3), 
            new Coordinate(1, 2));
    }

}