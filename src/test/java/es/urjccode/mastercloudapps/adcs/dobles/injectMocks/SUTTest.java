package es.urjccode.mastercloudapps.adcs.dobles.injectMocks;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SUTTest {

    @Mock
    DOC doc;

    @InjectMocks
    SUT sut;

    @Test
    public void testM(){
        when(doc.get()).thenReturn(7);
        sut.exerciseSUT(true);
        verify(doc).exerciseDOC(true);
    }

    @Captor
    ArgumentCaptor<Boolean> result;

    @Test
    public void testM2(){
        // captura de salida
        sut.exerciseSUT(true);
        verify(doc).exerciseDOC(result.capture());
        assertEquals(true, result.getValue());
    }

    @Test
    public void testM3(){
        // configuración de entrada
        when(doc.get()).thenReturn(-1);
        sut.exerciseSUT(true);
    }

}