package com.powerapi.mylib.math;

public class Math {

    public static Double convertToJoule(double averagePowerInMilliWatts, double testDurationInMs) {
        double averagePowerInWatt = averagePowerInMilliWatts / 1000;
        double durationInSec = testDurationInMs / 1000;

        return averagePowerInWatt * durationInSec;
    }

}
