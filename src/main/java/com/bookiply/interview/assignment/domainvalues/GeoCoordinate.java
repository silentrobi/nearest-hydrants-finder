package com.bookiply.interview.assignment.domainvalues;

import com.google.common.base.Preconditions;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class GeoCoordinate {

    private static final Double MAX_LATITUDE = 90.0;
    private static final Double MIN_LATITUDE = -90.0;
    private static final Double MAX_LONGITUDE = 180.0;
    private static final Double MIN_LONGITUDE = -180.0;

    @NotNull(message = "longitude is required")
    private Double longitude;

    @NotNull(message = "longitude is required")
    private Double latitude;


    public GeoCoordinate() {
    }

    public GeoCoordinate(Double latitude, Double longitude) {
        Preconditions.checkArgument(latitude >= MIN_LATITUDE, "latitude is lower than min_latitude: " + MIN_LATITUDE);
        Preconditions.checkArgument(latitude <= MAX_LATITUDE, "latitude is higher than max_latitude: " + MAX_LATITUDE);
        Preconditions.checkArgument(longitude >= MIN_LONGITUDE, "longitude is lower than min_longitude: " + MIN_LONGITUDE);
        Preconditions.checkArgument(longitude <= MAX_LONGITUDE, "longitude is higher than max_longitude: " + MAX_LONGITUDE);
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
