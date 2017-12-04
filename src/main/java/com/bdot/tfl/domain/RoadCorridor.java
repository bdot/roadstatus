package com.bdot.tfl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bharat
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadCorridor {
    private String id;
    private String displayName;
    private String statusSeverity;
    private String statusSeverityDescription;
    private String url;

    /* Helpers */

    /* G & S */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStatusSeverity() {
        return statusSeverity;
    }

    public void setStatusSeverity(String statusSeverity) {
        this.statusSeverity = statusSeverity;
    }

    public String getStatusSeverityDescription() {
        return statusSeverityDescription;
    }

    public void setStatusSeverityDescription(String statusSeverityDescription) {
        this.statusSeverityDescription = statusSeverityDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
