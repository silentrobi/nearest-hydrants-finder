package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;
import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;
import com.bookiply.interview.assignment.models.Hydrant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.Collections;
import java.util.List;

@Service
public class FireExtinguishActionService implements IFireExtinguishActionService {
    private final IGeoLocationService geoLocationService;
    private final IHydrantService hydrantService;

    @Autowired
    public FireExtinguishActionService(IGeoLocationService geoLocationService, IHydrantService hydrantService) {
        this.geoLocationService = geoLocationService;
        this.hydrantService = hydrantService;
    }

    @Override
    public List<Hydrant> getRequiredFirehoses(FireExtinguishActionDto fireServiceActionDto) throws IOException {
        List<Hydrant> hydrants = hydrantService.getHydrantsOfNewYorkCity();

        GeoCoordinate coordinate = fireServiceActionDto.getCoordinate();
        hydrants.forEach(hydrant -> hydrant.setDistanceToFire(geoLocationService
                .distance(hydrant.getCoordinate(),
                        new GeoCoordinate(coordinate.getLatitude(), coordinate.getLongitude()))));

        //sort array by distanceToFire
        Collections.sort(hydrants);

        //select hydrants where hydrants[truckCount]
        List<Hydrant> requiredHydrants = hydrants.subList(0, fireServiceActionDto.getTruckCount());

        return requiredHydrants;
    }
}
