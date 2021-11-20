package com.bookiply.interview.unit;

import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;
import com.bookiply.interview.assignment.services.GeoLocationService;
import com.bookiply.interview.assignment.services.IGeoLocationService;
import org.junit.Assert;
import org.junit.Test;

public class GeoLocationServiceUnitTest {

    private final IGeoLocationService geoLocationService = new GeoLocationService();

    @Test
    public void distanceShouldBeZero_ForSameGeoLocationPoints() {
        GeoCoordinate coordinate = new GeoCoordinate(40.7722168, -73.79457092);
        double distance = geoLocationService.distance(coordinate, coordinate);

        Assert.assertEquals(0, distance, 0);
    }

    @Test
    public void distanceShouldBeMatchExpectedValues_ForDifferentGeoLocationPoints() {
        GeoCoordinate coordinate1 = new GeoCoordinate(41.008621669219785, 28.979869398791546);
        GeoCoordinate coordinate2 = new GeoCoordinate(41.025819416551755, 28.974038581146605);
        GeoCoordinate coordinate3 = new GeoCoordinate(41.01999736371343, 28.957271729708253);
        GeoCoordinate coordinate4 = new GeoCoordinate(41.02725552945109, 28.951558599003416);

        double distance1 = geoLocationService.distance(coordinate1, coordinate2);
        double distance2 = geoLocationService.distance(coordinate3, coordinate4);

        Assert.assertEquals(1974, distance1, 0);
        Assert.assertEquals(939, distance2, 0);
    }
}
