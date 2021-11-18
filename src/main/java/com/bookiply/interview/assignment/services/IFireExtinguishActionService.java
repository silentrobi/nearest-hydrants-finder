package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;
import com.bookiply.interview.assignment.dtos.FirehoseDto;

import java.io.IOException;

public interface IFireExtinguishActionService {
    FirehoseDto getRequiredFirehoses(FireExtinguishActionDto fireServiceActionDto) throws IOException;
}
