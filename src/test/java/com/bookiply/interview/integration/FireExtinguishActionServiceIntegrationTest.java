package com.bookiply.interview.integration;

import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;
import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;

import com.bookiply.interview.assignment.models.Hydrant;
import com.bookiply.interview.assignment.services.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class FireExtinguishActionServiceIntegrationTest {

    @TestConfiguration
    static class FireExtinguishActionServiceTestContextConfiguration {

        @Bean
        public IGeoLocationService geoLocationService() {
            return new GeoLocationService();
        }

        @Bean
        public IHydrantService hydrantService() {
            return new HydrantService();
        }
        @Bean
        public IFireExtinguishActionService fireExtinguishActionService() {
            return new FireExtinguishActionService(geoLocationService(), hydrantService());
        }
    }

    @Autowired
    private IFireExtinguishActionService fireExtinguishActionService;

    @Test
    public void shouldReturnHydrantList_OnGetRequiredFirehosesInvoked() throws Exception {
        FireExtinguishActionDto actionDto = new FireExtinguishActionDto();
        actionDto.setCoordinate(new GeoCoordinate(40.7722168, -73.79457092));
        actionDto.setTruckCount(3);

        List<Hydrant> found = fireExtinguishActionService.getRequiredFirehoses(actionDto);

        Assert.assertEquals(3, found.size());
        Assert.assertEquals("169", found.get(0).getObjectId());
        Assert.assertEquals("H425919a", found.get(0).getUnitId());
        Assert.assertEquals(0, found.get(0).getDistanceToFire());
    }

    @Test
    public void shouldReturnHydrantList_OnGetRequiredFirehosesInvokedHashMap() throws Exception {
        FireExtinguishActionDto actionDto = new FireExtinguishActionDto();
        actionDto.setCoordinate(new GeoCoordinate(40.7722168, -73.79457092));
        actionDto.setTruckCount(3);

        List<Hydrant> found = fireExtinguishActionService.getRequiredByHashMap(actionDto);

        Assert.assertEquals(3, found.size());
        Assert.assertEquals("169", found.get(0).getObjectId());
        Assert.assertEquals("H425919a", found.get(0).getUnitId());
        Assert.assertEquals(0, found.get(0).getDistanceToFire());
    }
}
