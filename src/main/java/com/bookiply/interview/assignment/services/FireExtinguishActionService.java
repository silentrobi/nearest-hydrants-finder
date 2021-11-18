package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.dtos.CoordinateDto;
import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;
import com.bookiply.interview.assignment.dtos.FirehoseDto;
import com.bookiply.interview.assignment.dtos.HydrantDto;
import com.bookiply.interview.assignment.models.GeoPoint;
import com.bookiply.interview.assignment.models.Hydrant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

@Service
public class FireExtinguishActionService implements IFireExtinguishActionService {
    private final IGeoLocationService geoLocationService;
    private final ObjectMapper objectMapper;
    private final IHydrantService hydrantService;

    @Autowired
    public FireExtinguishActionService(IGeoLocationService geoLocationService, ObjectMapper objectMapper, IHydrantService hydrantService) {
        this.geoLocationService = geoLocationService;
        this.objectMapper = objectMapper;
        this.hydrantService = hydrantService;
    }

    @Override
    public FirehoseDto getRequiredFirehoses(FireExtinguishActionDto fireServiceActionDto) throws IOException {
        Hydrant[] hydrants = hydrantService.getHydrantsOfNewYorkCity();

        CoordinateDto coordinateDto = fireServiceActionDto.getCoordinate();
        Arrays.stream(hydrants).forEach(hydrant -> hydrant.setDistanceToFire(geoLocationService
                .distance(hydrant.getGeoPoint(),
                        new GeoPoint(coordinateDto.getLatitude(), coordinateDto.getLongitude()))));
        //sort array by distanceToFire
        Arrays.sort(hydrants);

        //select hydrants where hydrants[truckCount]
        Hydrant[] requiredHydrants = Arrays.copyOfRange(hydrants, 0, fireServiceActionDto.getTruckCount());

        FirehoseDto firehoseDto = new FirehoseDto();
        firehoseDto.setTotalFirehosesLength(Arrays.stream(requiredHydrants).mapToLong(x -> x.getDistanceToFire()).sum());
        firehoseDto.setHydrants(Arrays.stream(requiredHydrants).map(x -> new HydrantDto(x.getUnitId(),  x.getDistanceToFire())).toArray(HydrantDto[]::new));
        return firehoseDto;
    }
}
