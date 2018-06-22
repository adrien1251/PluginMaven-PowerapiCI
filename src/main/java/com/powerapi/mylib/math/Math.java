package com.powerapi.mylib.math;

public class Math {
    /**
     * Convert the energy in times to Joules
     *
     * @param averagePowerInMilliWatts
     * @param testDurationInMs
     * @return Joules
     */
    public static Double convertToJoule(double averagePowerInMilliWatts, double testDurationInMs) {
        double averagePowerInWatt = averagePowerInMilliWatts / 1000;
        double durationInSec = testDurationInMs / 1000;

        return averagePowerInWatt * durationInSec;
    }

}
