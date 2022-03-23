package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.dtos.FireExtinguishActionDto;
import com.bookiply.interview.assignment.models.Hydrant;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IFireExtinguishActionService {
    List<Hydrant> getRequiredFirehoses(FireExtinguishActionDto fireServiceActionDto) throws IOException;
    List<Hydrant> getRequiredByHashMap(FireExtinguishActionDto fireServiceActionDto) throws IOException;
}
