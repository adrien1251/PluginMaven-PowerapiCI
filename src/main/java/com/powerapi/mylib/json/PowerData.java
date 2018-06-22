package com.powerapi.mylib.json;

public class PowerData {
    private double power;
    private long timestamp;

    public PowerData(double power, long timestamp) {
        this.power = power;
        this.timestamp = timestamp;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}
