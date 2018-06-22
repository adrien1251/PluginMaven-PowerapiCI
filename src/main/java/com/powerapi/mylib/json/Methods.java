package com.powerapi.mylib.json;

import java.util.List;

/**
 * Class to build json
 */
public class Methods {
    /**
     * Name of the test
     */
    private String name;
    /**
     * Average energy of this test
     */
    private double energy;
    private long duration;
    private List<Iteration> iterations;

    public Methods(String name, double energy, long duration, List<Iteration> iterations) {
        this.name = name;
        this.energy = energy;
        this.duration = duration;
        this.iterations = iterations;
    }

    public Methods(String name, long duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<Iteration> getIterations() {
        return iterations;
    }

    public void setIterations(List<Iteration> iterations) {
        this.iterations = iterations;
    }


}
