package com.bdot.tfl;

import com.bdot.common.AppConstants.TfL;
import com.bdot.tfl.domain.RoadCorridor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bharat
 */
@Component
public class TflClient {
    @Value("${tfl.app.id}")
    private String appId;

    @Value("${tfl.app.key}")
    private String appKey;

    @Autowired
    private RestTemplate restTemplate;

    public RoadCorridor getRoadStatus(String roadId) {
        try {
            RoadCorridor[] roadStatusArray = restTemplate.getForObject(TfL.API_ROADS_URL_TEMPLATE, RoadCorridor[].class, roadId, appId, appKey);
            return roadStatusArray[0];
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
        }
        return null;
    }
}
