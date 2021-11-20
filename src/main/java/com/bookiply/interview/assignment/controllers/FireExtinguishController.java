package com.bookiply.interview.assignment.controllers;

import com.bookiply.interview.assignment.controllers.mapper.FireHoseMapper;
import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;
import com.bookiply.interview.assignment.dtos.FirehoseDto;
import com.bookiply.interview.assignment.services.IFireExtinguishActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class FireExtinguishController {

    private final IFireExtinguishActionService fireExtinguishActionService;

    @Autowired
    public FireExtinguishController(final IFireExtinguishActionService fireExtinguishActionService){
        this.fireExtinguishActionService = fireExtinguishActionService;
    }

    @PostMapping("/closest-hydrants")
    @ResponseStatus(HttpStatus.OK)
    public FirehoseDto getClosestHydrants(@Valid @RequestBody FireExtinguishActionDto fireExtinguishActionDto) throws IOException {
        return FireHoseMapper.mapToFirehoseDto(fireExtinguishActionService.getRequiredFirehoses(fireExtinguishActionDto));
    }
}
