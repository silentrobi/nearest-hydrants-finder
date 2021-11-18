package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;

public interface IGeoLocationService {
    long distance(GeoCoordinate startP, GeoCoordinate endP);
}
