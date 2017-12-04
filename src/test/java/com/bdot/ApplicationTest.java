package com.bdot;

import com.bdot.common.AppConstants;
import com.bdot.tfl.TflClient;
import com.bdot.tfl.domain.RoadCorridor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by bharat
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {
    @Rule
    public OutputCapture capture = new OutputCapture();

    @InjectMocks
    private Application app = new Application();

    @Mock
    private TflClient tflClient;

    @Test
    public void withNoArgs() throws Exception {
        app.run();
        assertThat(capture.toString(), containsString(AppConstants.Errors.MISSING_INPUT));
    }

    @Test
    public void withValidRoadId() throws Exception {
        RoadCorridor roadCorridor = new RoadCorridor();
        roadCorridor.setId("A2");
        roadCorridor.setDisplayName("A2 Road");
        roadCorridor.setStatusSeverity("Good");
        roadCorridor.setStatusSeverityDescription("No Exceptional Delays");
        Mockito.when(tflClient.getRoadStatus(Mockito.anyString())).thenReturn(roadCorridor);
        app.run(roadCorridor.getId());

        Mockito.verify(tflClient, VerificationModeFactory.times(1)).getRoadStatus(Mockito.anyString());
        assertThat(capture.toString(), containsString(roadCorridor.getDisplayName()));
        assertThat(capture.toString(), containsString(roadCorridor.getStatusSeverity()));
        assertThat(capture.toString(), containsString(roadCorridor.getStatusSeverityDescription()));
    }

    @Test
    public void withAnInvalidRoadId() throws Exception {
        Mockito.when(tflClient.getRoadStatus(Mockito.anyString())).thenReturn(null);
        app.run("ANY_INVALID_ROAD_ID");

        Mockito.verify(tflClient, VerificationModeFactory.times(1)).getRoadStatus(Mockito.anyString());
        assertThat(capture.toString(), containsString("ANY_INVALID_ROAD_ID is not a valid road"));
    }

}
