package com.bookiply.interview.unit;

import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;
import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;

import com.bookiply.interview.assignment.models.Hydrant;
import com.bookiply.interview.assignment.services.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
public class FireExtinguishActionServiceUnitTest {

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

    @MockBean
    IHydrantService hydrantService;

    @Autowired
    private IFireExtinguishActionService fireExtinguishActionService;

    @Test
    public void shouldReturnHydrantList_OnGetRequiredFirehosesInvoked() throws Exception {
        FireExtinguishActionDto actionDto = new FireExtinguishActionDto();
        actionDto.setCoordinate(new GeoCoordinate(40.7722168, -73.79457092));
        actionDto.setTruckCount(3);

        Mockito.when(hydrantService.getHydrantsOfNewYorkCity()).thenReturn(mockHydrants());

        List<Hydrant> found = fireExtinguishActionService.getRequiredFirehoses(actionDto);

        Assert.assertEquals(3, found.size());
        Assert.assertEquals("169", found.get(0).getObjectId());
        Assert.assertEquals("H425919a", found.get(0).getUnitId());
        Assert.assertEquals(0, found.get(0).getDistanceToFire());
    }

    @Test
    public void shouldReturnSortedHydrantList_ByDistanceToFire_OnGetRequiredFirehosesInvoked() throws Exception {
        FireExtinguishActionDto actionDto = new FireExtinguishActionDto();
        actionDto.setCoordinate(new GeoCoordinate(40.7722168, -73.79457092));
        actionDto.setTruckCount(6);

        Mockito.when(hydrantService.getHydrantsOfNewYorkCity()).thenReturn(mockHydrants());

        List<Hydrant> found = fireExtinguishActionService.getRequiredFirehoses(actionDto);

        List<Long> sortedDistanceToFire = found.stream()
                .map(Hydrant::getDistanceToFire)
                .collect(Collectors.toList());

        Assert.assertEquals(6, sortedDistanceToFire.size());
        Assert.assertTrue(sortedDistanceToFire.get(0) < sortedDistanceToFire.get(1));
        Assert.assertTrue(sortedDistanceToFire.get(1) < sortedDistanceToFire.get(2));
        Assert.assertTrue(sortedDistanceToFire.get(2) < sortedDistanceToFire.get(3));
        Assert.assertTrue(sortedDistanceToFire.get(3) < sortedDistanceToFire.get(4));
        Assert.assertTrue(sortedDistanceToFire.get(4) < sortedDistanceToFire.get(5));
    }

    private List<Hydrant> mockHydrants() {
        List<Hydrant> hydrants = new ArrayList<>();
        hydrants.add(new Hydrant("169", "H425919a", 40.7722168, -73.79457092));
        hydrants.add(new Hydrant("170", "H325449", 40.64434814, -73.9128952));
        hydrants.add(new Hydrant("172", "H307276", 40.72505569, -73.95304108));
        hydrants.add(new Hydrant("173", "H301843", 40.693988, -73.99462891));
        hydrants.add(new Hydrant("174", "H439410", 40.73529053, -73.93569183));
        hydrants.add(new Hydrant("175", "H328476", 40.63402557, -73.91147614));

        return hydrants;
    }
}
