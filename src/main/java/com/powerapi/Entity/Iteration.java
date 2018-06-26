package com.powerapi.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Iteration {
    private Integer n;
    private double energy;
    private long time_begin;
    private long time_end;
    private List<PowerData> power_data;

    public Iteration(Integer number, double energy, long time_begin, long time_end, List<PowerData> power_data) {
        this.n = number;
        this.energy = energy;
        this.time_begin = time_begin;
        this.time_end = time_end;
        this.power_data = power_data;
    }

    public Iteration(Integer number, double energy, long time_begin, long time_end) {
        this.n = number;
        this.energy = energy;
        this.time_begin = time_begin;
        this.time_end = time_end;
    }

}
