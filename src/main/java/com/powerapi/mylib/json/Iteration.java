package com.powerapi.mylib.json;

import java.util.List;

public class Iteration {
    private Integer number;
    private double energy;
    private long time_begin;
    private long time_end;
    private List<PowerData> power_data;

    public Iteration(Integer number, double energy, long time_begin, long time_end, List<PowerData> power_data) {
        this.number = number;
        this.energy = energy;
        this.time_begin = time_begin;
        this.time_end = time_end;
        this.power_data = power_data;
    }

    public Iteration(Integer number, double energy, long time_begin, long time_end) {
        this.number = number;
        this.energy = energy;
        this.time_begin = time_begin;
        this.time_end = time_end;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public long getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(long time_begin) {
        this.time_begin = time_begin;
    }

    public long getTime_end() {
        return time_end;
    }

    public void setTime_end(long time_end) {
        this.time_end = time_end;
    }

    public List<PowerData> getPower_data() {
        return power_data;
    }

    public void setPower_data(List<PowerData> power_data) {
        this.power_data = power_data;
    }


}
