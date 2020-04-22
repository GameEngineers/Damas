package es.urjccode.mastercloudapps.adcs.draughts.views;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class PlayViewTest {

    @Mock
    PlayController playController;
    @Mock
    Console console;
    @InjectMocks
    PlayView playView;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenPlayViewWhenInteractThenCancel() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("-1").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).cancel();
    }

    @Test
    public void testGivenPlayViewWhenInteractWithEmptyThenError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }

    @Test
    public void testGivenPlayViewWhenInteractWithNotPointThenError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("87,68").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }
    

    @Test
    public void testGivenPlayViewWhenInteractWithBadFormatThenError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("a3.42").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }

    @Test
    public void testGivenPlayViewWhenInteractWithBadRangeThenError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("93.49").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }

    @Test
    public void testGivenPlayViewWhenInteractWithNegativeThenError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("43.-34").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }

    @Test
    public void testGivenPlayViewWhenInteractWithSecondNegativeThenError() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("4-3.34").thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }

    @Test
    public void testGivenPlayViewWhenInteractWithTwoCoordiantesThenOk() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("32.41");
        playView.interact(playController);
        verify(playController).move(new Coordinate(2, 1), new Coordinate(3, 0));
    }

    @Test
    public void testGivenPlayViewWhenInteractWithThirdCoordiantesThenOk() {
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("23.32.41");
        playView.interact(playController);
        verify(playController).move(
            new Coordinate(1, 2), 
            new Coordinate(2, 1),
            new Coordinate(3, 0));
    }

}