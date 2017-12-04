package com.bdot.common;

/**
 * Created by bharat
 */
public class AppConstants {
    public static class TfL {
        public static final String API_ROADS_URL_TEMPLATE = "https://api.tfl.gov.uk/Road/{roadId}?app_id={appId}&app_key={appKey}";
    }

    public static class Errors {
        public static final String MISSING_INPUT = "Please pass road ID as a first argument";
    }
}
