package com.bookiply.interview.assignment.controllers.v1;

import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;
import com.bookiply.interview.assignment.dtos.FirehoseDto;
import com.bookiply.interview.assignment.services.IFireExtinguishActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class FireExtinguishController {
    private final IFireExtinguishActionService fireExtinguishActionService;

    @Autowired
    public FireExtinguishController(IFireExtinguishActionService fireExtinguishActionService){
        this.fireExtinguishActionService = fireExtinguishActionService;
    }

    @PostMapping("/closest-hydrants")
    public ResponseEntity<FirehoseDto> getClosestHydrants(@RequestBody FireExtinguishActionDto fireExtinguishActionDto) throws IOException {
        return new ResponseEntity<>(fireExtinguishActionService.getRequiredFirehoses(fireExtinguishActionDto), HttpStatus.OK);
    }
}
