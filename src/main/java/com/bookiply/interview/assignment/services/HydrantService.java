package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.models.Hydrant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HydrantService {


    public List<Hydrant> getHydrants() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Hydrant> hydrants = mapper
                .readValue(new URL("https://data.cityofnewyork.us/resource/5bgh-vtsn.json"),
                        new TypeReference<List<Hydrant>>() {});

        return hydrants;

    }
}
