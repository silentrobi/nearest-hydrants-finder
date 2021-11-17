package com.bookiply.interview.assignment.dtos;

public class FireService {

    private double[] coordinates;
    private int fireTruckCount;

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getFireTruckCount() {
        return fireTruckCount;
    }

    public void setFireTruckCount(int fireTruckCount) {
        this.fireTruckCount = fireTruckCount;
    }
}
