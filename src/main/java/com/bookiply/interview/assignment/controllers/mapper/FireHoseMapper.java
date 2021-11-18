package com.bookiply.interview.assignment.controllers.mapper;

import com.bookiply.interview.assignment.dtos.FirehoseDto;
import com.bookiply.interview.assignment.dtos.HydrantDto;
import com.bookiply.interview.assignment.models.Hydrant;

import java.util.Arrays;
import java.util.List;

public class FireHoseMapper {
    public static FirehoseDto mapToFirehoseDto(List<Hydrant> hydrants)
    {
            FirehoseDto.FirehoseDtoBuilder firehoseDtoBuilder = FirehoseDto.newBuilder()
                    .setTotalFirehosesLength(hydrants.stream().mapToLong(Hydrant::getDistanceToFire).sum())
                    .setHydrants(hydrants.stream().map(x -> new HydrantDto(x.getUnitId(),  x.getDistanceToFire())).toArray(HydrantDto[]::new));

            return firehoseDtoBuilder.createFirehoseDto();
    }
}
