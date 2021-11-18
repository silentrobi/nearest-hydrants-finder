package com.bookiply.interview.assignment.models;

import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hydrant implements Comparable<Hydrant>{

    @JsonCreator
    public Hydrant( @JsonProperty("objectid") String objectId,
                    @JsonProperty("unitid") String unitId,
                    @JsonProperty("latitude") double latitude,
                    @JsonProperty("longitude") double longitude) {
        this.objectId = objectId;
        this.unitId = unitId;
        this.coordinate = new GeoCoordinate(latitude, longitude);
    }

    private String objectId;
    private String unitId;
    private GeoCoordinate coordinate;
    private long distanceToFire;

    public GeoCoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(GeoCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public long getDistanceToFire() {
        return distanceToFire;
    }

    public void setDistanceToFire(long distanceToFire) {
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
                ", geoPoint=" + coordinate.toString() +
                ", distanceToFire=" + distanceToFire +
                '}';
    }

    @Override
    public int compareTo(Hydrant o) {
        return Double.compare(this.getDistanceToFire(), o.getDistanceToFire());
    }
}
