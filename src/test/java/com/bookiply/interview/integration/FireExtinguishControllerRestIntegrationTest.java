package com.bookiply.interview.integration;

import com.bookiply.interview.assignment.App;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class},
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
public class FireExtinguishControllerRestIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenNullGeoLocationInput_thenReturnError_forFireExtinguishAction() throws Exception {
        String actionJson = "{\"truckCount\":6}";

        // @formatter:off
        mvc.perform(post("/api/v1/closest-hydrants").contentType(MediaType.APPLICATION_JSON).content(actionJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.coordinate", is("coordinate is required")));
        // @formatter:on
    }
    @Test
    public void whenEmptyGeoLocationInput_thenReturnError_forFireExtinguishAction() throws Exception {
        String actionJson = "{\"coordinate\":{},\"truckCount\":6}";

        // @formatter:off
        mvc.perform(post("/api/v1/closest-hydrants").contentType(MediaType.APPLICATION_JSON).content(actionJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['coordinate.latitude']", is("latitude is required")))
                .andExpect(jsonPath("$['coordinate.longitude']", is("longitude is required")));
        // @formatter:on
    }

    @Test
    public void whenInvalidGeoLocationInput_thenReturnError_forFireExtinguishAction() throws Exception {
        String actionJson = "{\"coordinate\":{\"latitude\":90.7722168,\"longitude\":-183.79457092},\"truckCount\":6}";

        // @formatter:off
        mvc.perform(post("/api/v1/closest-hydrants").contentType(MediaType.APPLICATION_JSON).content(actionJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['coordinate.longitude']", is("longitude is lower than min_longitude: -180.0")))
                .andExpect(jsonPath("$['coordinate.latitude']", is("latitude is higher  than max_latitude: 90.0")));
        // @formatter:on
    }

    @Test
    public void whenValidGeoLocationInput_thenReturnFireHoseJsonResponse() throws Exception {
        String actionJson = "{\"coordinate\":{\"latitude\":40.7722168,\"longitude\":-73.79457092},\"truckCount\":4}";

        // @formatter:off
        mvc.perform(post("/api/v1/closest-hydrants").contentType(MediaType.APPLICATION_JSON).content(actionJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalFirehosesLength", greaterThan(0)))
                .andExpect(jsonPath("$.hydrants", hasSize(4)));
        // @formatter:on
    }
}
