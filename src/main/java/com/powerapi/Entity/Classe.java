package com.powerapi.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
        methods = new ArrayList<>();
    }
}
