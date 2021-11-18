package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.models.GeoPoint;

public interface IGeoLocationService {
    long distance(GeoPoint startP, GeoPoint endP);
}
