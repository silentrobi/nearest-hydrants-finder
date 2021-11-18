package com.bookiply.interview.assignment.dtos;

import lombok.Data;

@Data
public class FirehoseDto {
    private int totalFirehosesLength;
    private HydrantDto[] hydrants;
}
