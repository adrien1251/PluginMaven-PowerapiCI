package com.powerapi.mylib.json;

import java.util.ArrayList;
import java.util.List;

public class Classe {
    private String name;
    private double energy;
    private long duration;
    private List<Methods> methods;

    public Classe(String name, double energy, long duration, List<Methods> methods) {
        this.name = name;
        this.energy = energy;
        this.duration = duration;
        this.methods = methods;
    }

    public Classe(String name) {
        this.name = name;
        methods = new ArrayList<Methods>();
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

    public List<Methods> getMethods() {
        return methods;
    }

    public void setMethods(List<Methods> methods) {
        this.methods = methods;
    }


}
