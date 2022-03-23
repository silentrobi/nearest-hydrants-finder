package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;
import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;
import com.bookiply.interview.assignment.models.Hydrant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FireExtinguishActionService implements IFireExtinguishActionService {
    private final IGeoLocationService geoLocationService;
    private final IHydrantService hydrantService;

    @Autowired
    public FireExtinguishActionService( final IGeoLocationService geoLocationService, final IHydrantService hydrantService) {
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
        long startTime = System.currentTimeMillis();
        //sort array by distanceToFire
        Collections.sort(hydrants);

        //select hydrants where hydrants[truckCount]
        List<Hydrant> requiredHydrants = hydrants.subList(0, fireServiceActionDto.getTruckCount());

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        return requiredHydrants;
    }

    @Override
    public List<Hydrant> getRequiredByHashMap(FireExtinguishActionDto fireServiceActionDto) throws IOException {
        List<Hydrant> hydrants = hydrantService.getHydrantsOfNewYorkCity();

        GeoCoordinate coordinate = fireServiceActionDto.getCoordinate();
        hydrants.forEach(hydrant -> hydrant.setDistanceToFire(geoLocationService
                .distance(hydrant.getCoordinate(),
                        new GeoCoordinate(coordinate.getLatitude(), coordinate.getLongitude()))));

        long startTime = System.currentTimeMillis();

        Map<Long, Hydrant> map = hydrants.stream().collect(Collectors.toMap(Hydrant::getDistanceToFire, Function.identity()));

        TreeMap<Long, Hydrant> sorted= new TreeMap(map);
        Long[] keys = sorted.keySet().toArray(new Long[0]);
        List<Hydrant> requiredHydrants = new ArrayList<>();
        for(int i = 0; i <fireServiceActionDto.getTruckCount(); i++){
            requiredHydrants.add(sorted.get(keys[i]));
        }

        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        return requiredHydrants;
    }
}
