package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;
import com.bookiply.interview.assignment.dtos.FirehoseDto;
import com.bookiply.interview.assignment.dtos.HydrantDto;
import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;
import com.bookiply.interview.assignment.models.Hydrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

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
    public Hydrant[] getRequiredFirehoses(FireExtinguishActionDto fireServiceActionDto) throws IOException {
        Hydrant[] hydrants = hydrantService.getHydrantsOfNewYorkCity();

        GeoCoordinate coordinate = fireServiceActionDto.getCoordinate();
        Arrays.stream(hydrants).forEach(hydrant -> hydrant.setDistanceToFire(geoLocationService
                .distance(hydrant.getCoordinate(),
                        new GeoCoordinate(coordinate.getLatitude(), coordinate.getLongitude()))));

        //sort array by distanceToFire
        Arrays.sort(hydrants);

        //select hydrants where hydrants[truckCount]
        Hydrant[] requiredHydrants = Arrays.copyOfRange(hydrants, 0, fireServiceActionDto.getTruckCount());

//        FirehoseDto firehoseDto = new FirehoseDto();
//        firehoseDto.setTotalFirehosesLength(Arrays.stream(requiredHydrants).mapToLong(x -> x.getDistanceToFire()).sum());
//        firehoseDto.setHydrants(Arrays.stream(requiredHydrants).map(x -> new HydrantDto(x.getUnitId(),  x.getDistanceToFire())).toArray(HydrantDto[]::new));

        return requiredHydrants;
    }
}
