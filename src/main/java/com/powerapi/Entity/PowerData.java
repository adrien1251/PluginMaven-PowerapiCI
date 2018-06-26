package com.powerapi.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PowerData {
    private double power;
    private long timestamp;

    public PowerData(double power, long timestamp) {
        this.power = power;
        this.timestamp = timestamp;
    }
}
