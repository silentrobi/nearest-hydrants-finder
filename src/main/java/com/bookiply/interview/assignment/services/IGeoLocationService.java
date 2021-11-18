package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.models.GeoPoint;

public interface IGeoLocationService {

    double distance(GeoPoint startP, GeoPoint endP );
}
