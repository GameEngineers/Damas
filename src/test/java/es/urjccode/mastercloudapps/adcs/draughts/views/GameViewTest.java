package es.urjccode.mastercloudapps.adcs.draughts.views;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.StartController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.GameBuilder;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class GameViewTest {

    @Mock
    Console console;
    @InjectMocks
    GameView gameView;
    @Captor
    ArgumentCaptor<String> argument;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGivenGameViewWhenInteractThenOk(){
        Game game = new GameBuilder().build();
        StartController startController = new StartController(game, new State());
        this.gameView.write(startController);
        verify(console, times(90)).write(argument.capture());
        List<String> rows = Arrays.asList(
        " 12345678",
        "1 n n n n",
        "2n n n n ",
        "3 n n n n",
        "4        ",
        "5        ",
        "6b b b b ",
        "7 b b b b",
        "8b b b b ",
        " 12345678");
        assertEquals(marshall(rows), marshall(argument.getAllValues()));
    }

    @Test
    public void testGivenGameViewWhenInteractWithDraughtThenOk(){
        Game game = new GameBuilder().rows(
            "        ",
            "b       ",
            "        ",
            "        ",
            "        ",
            "        ",
            " n      ",
            "        ").build();
        game.move(
          new Coordinate(1, 0),
          new Coordinate(0, 1)  
        );
        game.move(
          new Coordinate(6, 1),
          new Coordinate(7, 0)  
        );
        StartController startController = new StartController(game, new State());
        this.gameView.write(startController);
        verify(console, times(90)).write(argument.capture());
        List<String> rows = Arrays.asList(
        " 12345678",
        "1 B      ",
        "2        ",
        "3        ",
        "4        ",
        "5        ",
        "6        ",
        "7        ",
        "8N       ",
        " 12345678");
        assertEquals(marshall(rows), marshall(argument.getAllValues()));
    }

    private static String marshall(List<String> strings){
        String string = "";
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            string += iterator.next();
        }
        return string;
    }

}