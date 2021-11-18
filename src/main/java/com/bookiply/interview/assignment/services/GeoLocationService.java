package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.models.GeoPoint;
import org.springframework.stereotype.Component;

@Component
public class GeoLocationService implements IGeoLocationService{

    @Override
    public double distance(GeoPoint startP, GeoPoint endP) {
        double lat1 = startP.getLatitude();
        double lat2 = endP.getLatitude();
        double lon1 = startP.getLongitude();
        double lon2 = endP.getLongitude();

        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double Radius = 6371.0; // in km
        return Radius * c * 1000; // in meter
    }
}
