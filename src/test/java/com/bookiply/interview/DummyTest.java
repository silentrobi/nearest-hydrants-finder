package com.bookiply.interview;

import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;
import com.bookiply.interview.assignment.models.Hydrant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;

public class DummyTest {

    @Test
    public void someTest() {
        Assert.assertTrue(true);
    }

    private double calculationByDistance(GeoCoordinate startP, GeoCoordinate endP) {
        double lat1 = startP.getLatitude();
        double lat2 = endP.getLatitude();
        double lon1 = startP.getLongitude();
        double lon2 = endP.getLongitude();

        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double Radius = 6371.0;
        return Radius * c * 1000; // results in meter
    }
    private double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }

    private double distance3(GeoCoordinate gp1, GeoCoordinate gp2) {
        double lat1 = gp1.getLatitude();
        double lat2 = gp2.getLatitude();
        double lon1 = gp1.getLongitude();
        double lon2 = gp2.getLongitude();

        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lon2-lon1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km

        return d * 1000; //Distance in Meter
    }

    class HydrantToFireComparator implements Comparator<Hydrant> {

        @Override
        public int compare(Hydrant o1, Hydrant o2) {
            return Double.compare(o1.getDistanceToFire(), o2.getDistanceToFire());
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

        GeoCoordinate targetGeoPoint = new GeoCoordinate(40.7722168, -73.79457092);

        //Arrays.stream(hydrants).forEach(hydrant -> hydrant.setDistanceToFire(calculationByDistance(hydrant.getGeoPoint(), targetGeoPoint)));
        Arrays.sort(hydrants);

        endTime = System.currentTimeMillis();
        System.out.println("sort hydrants by distanceToFire execution time --->  " + (endTime - startTime) + " milliseconds");

        GeoCoordinate gp1 = new GeoCoordinate(59.3293371,13.4877472);
        GeoCoordinate gp2 = new GeoCoordinate(59.3225525,13.4619422);

        System.out.println(gp1.toString());
        System.out.println(gp2.toString());
        Double dis = calculationByDistance(gp1,  gp2);
        Double dis3 = distance3( gp1 , gp2);
        System.out.println(dis);
        System.out.println(dis3);
        for (Hydrant h: hydrants
             ) {
            System.out.println(h);
        }
        Assert.assertTrue(hydrants.length > 10);
//        Assert.assertThat(dtoAsString, containsString("stringValue"));
//        Assert.assertThat(dtoAsString, containsString("booleanValue"));
    }
}
