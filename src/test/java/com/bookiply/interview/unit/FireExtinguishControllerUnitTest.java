package com.bookiply.interview.unit;


import com.bookiply.interview.assignment.controllers.FireExtinguishController;
import com.bookiply.interview.assignment.domainvalues.GeoCoordinate;
import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;
import com.bookiply.interview.assignment.services.FireExtinguishActionService;
import com.bookiply.interview.assignment.services.IHydrantService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FireExtinguishController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class FireExtinguishControllerUnitTest {
    @Autowired
    private MockMvc mvc;

   @MockBean
    private IHydrantService hydrantService;

    @MockBean
    private FireExtinguishActionService fireExtinguishActionService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostWithInvalidGeoLocationValue_thenThrowIllegalArgumentException() throws Exception {
        FireExtinguishActionDto actionDto = new FireExtinguishActionDto();
        actionDto.setCoordinate(new GeoCoordinate(90.0, 43.221234));
        actionDto.setTruckCount(3);

        Gson gson = new Gson();

        given(fireExtinguishActionService.getRequiredFirehoses(Mockito.any())).willThrow(IllegalArgumentException.class);

        mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(actionDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("latitude is higher than max_latitude: 90.0"));
        verify(fireExtinguishActionService, VerificationModeFactory.times(1)).getRequiredFirehoses(Mockito.any());
        reset(fireExtinguishActionService);
    }
}
