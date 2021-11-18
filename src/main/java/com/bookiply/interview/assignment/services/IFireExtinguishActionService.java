package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;
import com.bookiply.interview.assignment.dtos.FirehoseDto;
import com.bookiply.interview.assignment.models.Hydrant;

import java.io.IOException;

public interface IFireExtinguishActionService {
    Hydrant[] getRequiredFirehoses(FireExtinguishActionDto fireServiceActionDto) throws IOException;
}
