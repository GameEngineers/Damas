package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class IsBlockedGameTest extends GameTest {

    @Test
    public void testGivenGameWhenIsBlockedWithWhiteEmptyPiecesThenTrue(){
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedWithBlackEmptyPiecesThenTrue(){
        setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedWithWhiteOnePieceThenTrue(){
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  n     ",
            " n      ",
            "b       ");
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedWithBlackOnePieceThenTrue(){
        setGame(Color.BLACK,
            " n      ",
            "b b     ",
            "   b    ",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedWithWhiteTwoPieceThenTrue(){
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "n n n   ",
            " n n    ",
            "b b     ");
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedWithBlackTwoPieceThenTrue(){
        setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "n n n   ",
            " n n    ",
            "b b     ");
        assertTrue(this.game.isBlocked());
    }

    @Test
    public void testGivenGameWhenIsBlockedThenFalse(){
        setGame(Color.WHITE,
            "        ",
            " b      ",
            "        ",
            "        ",
            " n   n  ",
            "        ",
            "   b    ",
            "        ");
        assertFalse(this.game.isBlocked());
    }
    
}