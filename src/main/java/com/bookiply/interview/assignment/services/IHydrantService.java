package com.bookiply.interview.assignment.services;

import com.bookiply.interview.assignment.models.Hydrant;

import java.io.IOException;

public interface IHydrantService {
    Hydrant[] getHydrantsOfNewYorkCity() throws IOException;
}
