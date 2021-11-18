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
    public HydrantService(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Cacheable("hydrants-newyork-city")
    @Override
    public List<Hydrant> getHydrantsOfNewYorkCity() throws IOException {
        return objectMapper
                .readValue(new URL("https://data.cityofnewyork.us/resource/5bgh-vtsn.json"),
                        new TypeReference<List<Hydrant>>(){});
    }
}
