package com.bookiply.interview.assignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class HydrantDto {
    private String unitid;
    private long distanceToFire;

    public HydrantDto(){}

    public HydrantDto(String unitid, long distanceToFire) {
        this.unitid = unitid;
        this.distanceToFire = distanceToFire;
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public long getDistanceToFire() {
        return distanceToFire;
    }

    public void setDistanceToFire(long distanceToFire) {
        this.distanceToFire = distanceToFire;
    }

    @Override
    public String toString() {
        return "HydrantDto{" +
                "unitid='" + unitid + '\'' +
                ", distanceToFire=" + distanceToFire +
                '}';
    }
}
