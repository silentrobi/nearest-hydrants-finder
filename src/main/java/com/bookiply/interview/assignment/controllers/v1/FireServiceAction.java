package com.bookiply.interview.assignment.controllers.v1;

import com.bookiply.interview.assignment.dtos.FireServiceActionDto;
import com.bookiply.interview.assignment.dtos.FirehoseDto;
import com.bookiply.interview.assignment.services.IFireHydrantActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/api/v1")
public class FireServiceAction {

    @Autowired
    private IFireHydrantActionService fireHydrantActionService;

    @PostMapping("/fire-hydrants")
    public ResponseEntity<FirehoseDto> getAllUsers(@RequestBody FireServiceActionDto fireServiceActionDto) throws IOException {
        return new ResponseEntity<FirehoseDto>(fireHydrantActionService.getRequiredFirehoses(fireServiceActionDto), HttpStatus.OK);
    }

}
