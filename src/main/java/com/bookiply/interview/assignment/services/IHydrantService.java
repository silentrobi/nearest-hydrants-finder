package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.models.Hydrant;

import java.io.IOException;
import java.util.List;

public interface IHydrantService {
    List<Hydrant> getHydrantsOfNewYorkCity() throws IOException;
}
