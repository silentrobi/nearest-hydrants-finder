package com.bookiply.interview.assignment.dtos;

import lombok.Data;

@Data
public class FireExtinguishActionDto {
    private CoordinateDto coordinate;
    private int truckCount;
}
