package es.urjccode.mastercloudapps.adcs.draughts.views;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.ResumeController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.YesNoDialog;

@RunWith(MockitoJUnitRunner.class)
public class ResumeViewTest {

    @Mock
    ResumeController resumeController;
    @Mock
    YesNoDialog yesNoDialog;
    @InjectMocks
    ResumeView resumeView;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGivenResumeViewWhenAnswerYesThenReset(){
        when(yesNoDialog.read(anyString())).thenReturn(true);
        resumeView.interact(resumeController);
        verify(resumeController).reset();
    }

    @Test
    public void testGivenResumeViewWhenAnswerNoThenNext(){
        when(yesNoDialog.read(anyString())).thenReturn(false);
        resumeView.interact(resumeController);
        verify(resumeController).next();
    }

}