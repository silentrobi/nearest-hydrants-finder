package com.bookiply.interview.assignment.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hydrant {
    private String objectId;
    private String unitId;
    private GeoPoint geoPoint;
    private double distanceToFire;

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

    @JsonProperty("objectid")
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @JsonProperty("unitid")
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("the_geom")
    private void unpackNested(Map<String,Object> geom) {
        List<Double> coordinates = (List<Double>) geom.get("coordinates");
        this.geoPoint = new GeoPoint(coordinates.get(0),coordinates.get(1));
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
}
