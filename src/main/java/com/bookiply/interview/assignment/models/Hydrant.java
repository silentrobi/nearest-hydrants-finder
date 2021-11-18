package com.bookiply.interview.assignment.models;

import com.fasterxml.jackson.annotation.*;

import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Hydrant implements Comparable<Hydrant>{

    @JsonCreator
    public Hydrant( @JsonProperty("objectid") String objectId,
                    @JsonProperty("unitid") String unitId,
                    @JsonProperty("latitude") double latitude,
                    @JsonProperty("longitude") double longitude) {
        this.objectId = objectId;
        this.unitId = unitId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geoPoint = new GeoPoint(latitude, longitude);
    }

    private String objectId;
    private String unitId;
    private GeoPoint geoPoint;
    private double distanceToFire;
    private double longitude;
    private double latitude;


    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public double getDistanceToFire() {
        return distanceToFire;
    }

    public void setDistanceToFire(double distanceToFire) {
        this.distanceToFire = distanceToFire;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Override
    public String toString() {
        return "Hydrant{" +
                "objectId='" + objectId + '\'' +
                ", unitId='" + unitId + '\'' +
                ", geoPoint=" + geoPoint.toString() +
                ", distanceToFire=" + distanceToFire +
                '}';
    }

    @Override
    public int compareTo(Hydrant o) {
        return Double.compare(this.getDistanceToFire(), o.getDistanceToFire());
    }
}
