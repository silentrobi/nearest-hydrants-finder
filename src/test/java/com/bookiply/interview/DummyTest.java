package com.bookiply.interview;

import com.bookiply.interview.assignment.dtos.FireService;
import com.bookiply.interview.assignment.models.GeoPoint;
import com.bookiply.interview.assignment.models.Hydrant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DummyTest {

    @Test
    public void someTest() {
        Assert.assertTrue(true);
    }

    private double haversine(double[] coordinate1, double[] coordinate2) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(coordinate2[1] - coordinate1[1]);
        double dLon = Math.toRadians(coordinate2[0] - coordinate1[0]);

        // convert to radians
        coordinate1[1] = Math.toRadians(coordinate1[1]);
        coordinate2[1] = Math.toRadians(coordinate2[1]);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(coordinate1[1]) *
                        Math.cos(coordinate2[1]);
        double rad = 6371.0;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    private double calculationByDistance(GeoPoint StartP, GeoPoint EndP) {
        double lat1 = StartP.getLatitude()/1E6;
        double lat2 = EndP.getLatitude()/1E6;
        double lon1 = StartP.getLongitude()/1E6;
        double lon2 = EndP.getLongitude()/1E6;
        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double Radius = 6371.0;
        return Radius * c;
    }

    class HydrantToFireComparator implements Comparator<Hydrant> {

        @Override
        public int compare(Hydrant o1, Hydrant o2) {
            return (o1.getDistanceToFire() > o2.getDistanceToFire() ? 1 : -1);
        }
    }

    @Test
    public final void whenSerializing_thenCorrect() throws JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        long startTime = System.currentTimeMillis();
        Hydrant[] hydrants = mapper
                .readValue(new URL("https://data.cityofnewyork.us/resource/5bgh-vtsn.json"),
                        Hydrant[].class);

        long endTime = System.currentTimeMillis();
        System.out.println(hydrants.length);

        System.out.println("get hydrants call execution time --->  " + (endTime - startTime) + " milliseconds");

        GeoPoint targetGeoPoint = new GeoPoint(-73.91289250895464, 40.644346617665086);

        Arrays.stream(hydrants).forEach(hydrant -> hydrant.setDistanceToFire(calculationByDistance(hydrant.getGeoPoint(), targetGeoPoint)));
        Arrays.sort(hydrants, new HydrantToFireComparator());

        //System.out.println(hydrants[0]);
        for (Hydrant h: hydrants
             ) {
            System.out.println(h);
        }
        Assert.assertTrue(hydrants.length > 10);
//        Assert.assertThat(dtoAsString, containsString("stringValue"));
//        Assert.assertThat(dtoAsString, containsString("booleanValue"));
    }
}
