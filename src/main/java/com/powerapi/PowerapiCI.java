package com.powerapi;

public class PowerapiCI {
    public PowerapiCI(double power, long timestamp, String testName, long timeBeginTest, long timeEndTest, long testDuration, double energy) {
        this.power = power;
        this.timestamp = timestamp;
        this.testName = testName;
        this.timeBeginTest = timeBeginTest;
        this.timeEndTest = timeEndTest;
        this.testDuration = testDuration;
        this.energy = energy;
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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public long getTimeBeginTest() {
        return timeBeginTest;
    }

    public void setTimeBeginTest(long timeBeginTest) {
        this.timeBeginTest = timeBeginTest;
    }

    public long getTimeEndTest() {
        return timeEndTest;
    }

    public void setTimeEndTest(long timeEndTest) {
        this.timeEndTest = timeEndTest;
    }

    public long getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(long testDuration) {
        this.testDuration = testDuration;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    private double power;
    private long timestamp;
    private String testName;
    private long timeBeginTest;
    private long timeEndTest;
    private long testDuration;
    private double energy;
}
