package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.GameBuilder;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;

public class StartControllerTest {

    private State state;
    private StartController startController;

    @Before
    public void before(){
        Game game = new GameBuilder().build();
        this.state = new State();
        this.startController = new StartController(game, state);
    }

     @Test
    public void givenStartControllerWhenStartGameThenChangeState() {
        assertEquals(StateValue.INITIAL, state.getValueState());
        startController.start();
        assertEquals(StateValue.IN_GAME, state.getValueState());
    }

}