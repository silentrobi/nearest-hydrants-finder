package com.bookiply.interview.unit;

import com.bookiply.interview.assignment.App;
import com.bookiply.interview.assignment.controllers.FireExtinguishController;
import com.bookiply.interview.assignment.models.Hydrant;
import com.bookiply.interview.assignment.services.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FireExtinguishController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ContextConfiguration(classes={App.class})
public class FireExtinguishControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IFireExtinguishActionService fireExtinguishActionService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void whenGivenValidInputs_getClosestHydrants_returnsFireHosesDto() throws Exception {
        String actionJson = "{\"coordinate\":{\"latitude\":40.7722168,\"longitude\":-73.79457092},\"truckCount\":3}";

        List<Hydrant> hydrants = new ArrayList<>();
        hydrants.add(new Hydrant("169", "H425919a", 40.7722168, -73.79457092));
        hydrants.add(new Hydrant("170", "H325449", 40.64434814, -73.9128952));
        hydrants.add(new Hydrant("172", "H307276", 40.72505569, -73.95304108));

        hydrants.get(0).setDistanceToFire(0);
        hydrants.get(1).setDistanceToFire(301);
        hydrants.get(2).setDistanceToFire(438);

        given(fireExtinguishActionService.getRequiredFirehoses(Mockito.any())).willReturn(hydrants);

        // @formatter:off
        mvc.perform(post("/api/v1/closest-hydrants").contentType(MediaType.APPLICATION_JSON).content(actionJson)).andExpect(status().isOk())
                .andExpect(jsonPath("$.hydrants", hasSize(3)))
                .andExpect(jsonPath("$.totalFirehosesLength", is(739)));
        verify(fireExtinguishActionService, VerificationModeFactory
                .times(1))
                .getRequiredFirehoses(Mockito.any());

        // @formatter:on
        reset(fireExtinguishActionService);
    }
}
