package com.bookiply.interview.assignment.dtos;

import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;

public class FireExtinguishActionDto {
    private GeoCoordinate coordinate;
    private int truckCount;

    public void setCoordinate(GeoCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setTruckCount(int truckCount) {
        this.truckCount = truckCount;
    }

    public GeoCoordinate getCoordinate() {
        return coordinate;
    }

    public int getTruckCount() {
        return truckCount;
    }

    @Override
    public String toString() {
        return "FireExtinguishActionDto{" +
                "coordinate=" + coordinate +
                ", truckCount=" + truckCount +
                '}';
    }
}
