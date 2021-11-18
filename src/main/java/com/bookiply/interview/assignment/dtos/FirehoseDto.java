package com.bookiply.interview.assignment.dtos;

import lombok.Data;

@Data
public class FirehoseDto {
    private long totalFirehosesLength;
    private HydrantDto[] hydrants;
}
