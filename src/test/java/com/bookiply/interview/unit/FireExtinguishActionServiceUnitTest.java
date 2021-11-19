package com.bookiply.interview.unit;

import com.bookiply.interview.assignment.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

public class FireExtinguishActionServiceUnitTest {

    @TestConfiguration
    static class FireExtinguishActionServiceTestContextConfiguration {

        @Bean
        public ObjectMapper objectMapper(){
            return new ObjectMapper();
        }
        @Bean
        public IGeoLocationService geoLocationService(){
            return new GeoLocationService();
        }
    }

    @Autowired
    private IFireExtinguishActionService fireExtinguishActionService;
}
