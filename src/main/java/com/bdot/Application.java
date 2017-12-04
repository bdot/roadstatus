package com.bdot;

import com.bdot.common.AppConstants.Errors;
import com.bdot.tfl.TflClient;
import com.bdot.tfl.domain.RoadCorridor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import static java.lang.System.out;

/**
 * Created by bharat
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private TflClient tflClient;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && !StringUtils.isEmpty(args[0].trim())) {
            RoadCorridor roadCorridor = tflClient.getRoadStatus(args[0]);
            if (roadCorridor == null) {
                out.println(String.format("%s is not a valid road", args[0]));
            } else {
                printRoadStatus(roadCorridor);
            }
        } else {
            out.println(Errors.MISSING_INPUT);
        }
    }

    private void printRoadStatus(RoadCorridor roadCorridor) {
        out.println(String.format("The status of the %s is as follows \n" +
                "\t Road Status is %s \n" +
                "\t Road Status Description is %s", roadCorridor.getDisplayName(), roadCorridor.getStatusSeverity(), roadCorridor.getStatusSeverityDescription()));
    }
}
