package com.bookiply.interview.assignment.controllers.mapper;

import com.bookiply.interview.assignment.dtos.FirehoseDto;
import com.bookiply.interview.assignment.dtos.HydrantDto;
import com.bookiply.interview.assignment.models.Hydrant;

import java.util.Arrays;

public class FireHoseMapper {
    public static FirehoseDto mapToFirehoseDto(Hydrant[] hydrants)
    {
            FirehoseDto.FirehoseDtoBuilder firehoseDtoBuilder = FirehoseDto.newBuilder()
                    .setTotalFirehosesLength(Arrays.stream(hydrants).mapToLong(Hydrant::getDistanceToFire).sum())
                    .setHydrants(Arrays.stream(hydrants).map(x -> new HydrantDto(x.getUnitId(),  x.getDistanceToFire())).toArray(HydrantDto[]::new));

            return firehoseDtoBuilder.createFirehoseDto();
    }
}
