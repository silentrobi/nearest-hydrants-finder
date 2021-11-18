package com.bookiply.interview.assignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HydrantDto {
    private String unitId;
    private int distanceToFire;
}
