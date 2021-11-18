package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.dtos.FireServiceActionDto;
import com.bookiply.interview.assignment.dtos.FirehoseDto;

import java.io.IOException;

public interface IFireHydrantActionService {
    FirehoseDto getRequiredFirehoses(FireServiceActionDto fireServiceActionDto) throws IOException;
}
