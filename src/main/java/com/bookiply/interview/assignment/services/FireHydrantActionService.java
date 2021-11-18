package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.dtos.CoordinateDto;
import com.bookiply.interview.assignment.dtos.FireServiceActionDto;
import com.bookiply.interview.assignment.dtos.FirehoseDto;
import com.bookiply.interview.assignment.dtos.HydrantDto;
import com.bookiply.interview.assignment.models.GeoPoint;
import com.bookiply.interview.assignment.models.Hydrant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Service
public class FireHydrantActionService implements IFireHydrantActionService {

    @Autowired
    private IGeoLocationService geoLocationService;

    public List<Hydrant> getHydrants() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Hydrant> hydrants = mapper
                .readValue(new URL("https://data.cityofnewyork.us/resource/5bgh-vtsn.json"),
                        new TypeReference<List<Hydrant>>() {});

        return hydrants;

    }

    @Override
    public FirehoseDto getRequiredFirehoses(FireServiceActionDto fireServiceActionDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        long startTime = System.currentTimeMillis();
        Hydrant[] hydrants = mapper
                .readValue(new URL("https://data.cityofnewyork.us/resource/5bgh-vtsn.json"),
                        Hydrant[].class);

        long endTime = System.currentTimeMillis();
        System.out.println(hydrants.length);

        CoordinateDto coordinateDto = fireServiceActionDto.getCoordinate();
        Arrays.stream(hydrants).forEach(hydrant -> hydrant.setDistanceToFire(geoLocationService
                .distance(hydrant.getGeoPoint(),
                        new GeoPoint(coordinateDto.getLatitude(), coordinateDto.getLongitude()))));
        Arrays.sort(hydrants);
        Hydrant[] requiredHydrants = Arrays.copyOfRange(hydrants, 0, fireServiceActionDto.getTruckCount());

        FirehoseDto firehoseDto = new FirehoseDto();
        firehoseDto.setTotalFirehosesLength(Arrays.stream(requiredHydrants).mapToInt(x -> (int) x.getDistanceToFire()).sum());
        firehoseDto.setHydrants(Arrays.stream(requiredHydrants).map(x -> new HydrantDto(x.getUnitId(), (int) x.getDistanceToFire())).toArray(HydrantDto[]::new));
        return firehoseDto;
    }
}
