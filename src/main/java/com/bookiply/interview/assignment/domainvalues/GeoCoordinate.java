package com.bookiply.interview.assignment.domainvalues;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class GeoCoordinate {

    @NotNull(message = "longitude is required")
    @DecimalMin(value= "-180.0", message = "longitude is lower than min_longitude: -180.0" )
    @DecimalMax(value= "180.0", message = "longitude is higher  than max_longitude: 180.0" )
    private Double longitude;

    @NotNull(message = "latitude is required")
    @DecimalMin(value= "-90.0", message = "latitude is lower than min_latitude: -90.0" )
    @DecimalMax(value= "90.0", message = "latitude is higher  than max_latitude: 90.0" )
    private Double latitude;

    public GeoCoordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "GeoPoint{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
