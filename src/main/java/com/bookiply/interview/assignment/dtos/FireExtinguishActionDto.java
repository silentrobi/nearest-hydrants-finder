package com.bookiply.interview.assignment.dtos;

import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FireExtinguishActionDto {

    @Valid
    @NotNull(message = "coordinate is required")
    private GeoCoordinate coordinate;

    @Min(1)
    @NotNull(message = "truckCount is required")
    private Integer truckCount;

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
}
