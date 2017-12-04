package com.bdot;


import com.bdot.common.AppConstants;
import com.bdot.tfl.TflClient;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationIT {
    @Autowired
    private Application application;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TflClient tflClient;

    @Rule
    public OutputCapture capture = new OutputCapture();

    @BeforeClass
    public static void setErrorLogging() {
        LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(Logger.ROOT_LOGGER_NAME, LogLevel.ERROR);
    }

    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
        Assertions.assertThat(tflClient).isNotNull();
    }

    @Test
    public void withNoArgs() throws Exception {
        application.run();
        assertThat(capture.toString(), containsString(AppConstants.Errors.MISSING_INPUT));
    }

    @Test
    public void withValidRoadId() throws Exception {
        application.run("A2");
        assertThat(capture.toString(), containsString("A2"));
        assertThat(capture.toString(), containsString("Road Status"));
        assertThat(capture.toString(), containsString("Road Status Description"));
    }

    @Test
    public void withAnInvalidRoadId() throws Exception {
        application.run("A233");
        assertThat(capture.toString(), containsString("A233 is not a valid road"));
    }

}