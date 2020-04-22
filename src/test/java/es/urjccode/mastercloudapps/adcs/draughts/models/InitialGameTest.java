package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InitialGameTest extends GameTest {
   
    @Test
    public void testGivenGameWhenIsNewThenPrefixedLocations() {
        this.game = new Game();
        this.setExpectedGame(Color.WHITE,
            " n n n n",
            "n n n n ",
            " n n n n",
            "        ",
            "        ",
            "b b b b ",
            " b b b b",
            "b b b b ");
        assertEquals(this.expectedGame, this.game);
    }

    @Test
    public void testGivenGameWhenCancelThenWithoutPieces() {
        this.game = new Game();
        this.game.cancel();
        this.setExpectedGame(Color.BLACK,
            " n n n n",
            "n n n n ",
            " n n n n",
            "        ",
            "        ",
            "        ",
            "        ",
            "        ");
        assertEquals(this.expectedGame, this.game);
    }

    @Test
    public void testGivenGameWhenClearThenPrefixedLocations() {
        this.game = new Game();
        this.game.move(
            new Coordinate(5, 0),
            new Coordinate(4, 1));
        this.game.reset();
        this.setExpectedGame(Color.WHITE,
            " n n n n",
            "n n n n ",
            " n n n n",
            "        ",
            "        ",
            "b b b b ",
            " b b b b",
            "b b b b ");
        assertEquals(this.expectedGame, this.game);
    }
    
}