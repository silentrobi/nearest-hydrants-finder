package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.models.Hydrant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class HydrantService implements IHydrantService{
    public final ObjectMapper objectMapper;

    public HydrantService(){
        this.objectMapper = new ObjectMapper();
    }

    @Cacheable("NEW_YORK_CITY_HYDRANTS")
    @Override
    public List<Hydrant> getHydrantsOfNewYorkCity() throws IOException {
        List<Hydrant> res = objectMapper
                .readValue(new URL("https://data.cityofnewyork.us/resource/5bgh-vtsn.json"),
                        new TypeReference<List<Hydrant>>(){});
        return res;
    }
}
