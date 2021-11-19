package com.bookiply.interview.integration;

import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;
import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class FireExtinguishActionServiceIntegrationTest {

    @Autowired
    private MockMvc mvc;

//    @TestConfiguration
//    static class FireExtinguishActionServiceTestContextConfiguration {
//
//        @Bean
//        public ObjectMapper objectMapper() {
//            return new ObjectMapper();
//        }
//
//        @Bean
//        public IGeoLocationService geoLocationService() {
//            return new GeoLocationService();
//        }
//
//        @Bean
//        public IHydrantService hydrantService() {
//            return new HydrantService(objectMapper());
//        }
//    }
//
//    @Autowired
//    private IFireExtinguishActionService fireExtinguishActionService;

    @Test
    public void whenInvalidGeoLocationInput_thenReturnError_forFireExtinguishAction() throws Exception {
        FireExtinguishActionDto actionDto = new FireExtinguishActionDto();
        actionDto.setCoordinate(new GeoCoordinate(91.0, 43.221234));
        actionDto.setTruckCount(3);

        Gson gson = new Gson();

        // @formatter:off
        mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(actionDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("latitude is higher than max_latitude: 90.0"));
        // @formatter:on
    }

    @Test
    public void whenEmptyGeoLocationInput_thenReturnError_forFireExtinguishAction() throws Exception {
        FireExtinguishActionDto actionDto = new FireExtinguishActionDto();
        actionDto.setCoordinate(new GeoCoordinate());
        actionDto.setTruckCount(3);

        Gson gson = new Gson();

        // @formatter:off
        mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(actionDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("latitude is higher than max_latitude: 90.0"));
        // @formatter:on
    }
}
