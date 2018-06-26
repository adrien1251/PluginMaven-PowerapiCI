package com.powerapi.service;

import com.powerapi.PowerapiCI;
import com.powerapi.PowerapiData;
import com.powerapi.TestData;
import com.powerapi.dao.PowerapiDao;
import com.powerapi.dao.SurefireDao;
import com.powerapi.mylib.Constants;
import com.powerapi.mylib.converter.Converter;
import com.powerapi.Entity.ResultatApplication;
import com.powerapi.mylib.math.Math;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PowerapiService {
    private SurefireDao surefireDao = SurefireDao.getInstance();


    private PowerapiDao powerapiDao = PowerapiDao.getInstance();

    /**
     * Aggregate two lists to return list<PowerapiCI>
     *
     * @param powerapiList list of PowerapiData
     * @param testList     list of TestData
     * @return list <PowerapiCI>
     */
    public List<PowerapiCI> findListPowerapiCI(List<PowerapiData> powerapiList, List<TestData> testList) {
        List<PowerapiCI> powerapiCIList = new ArrayList<>();
        ArrayList<Double> powerList = new ArrayList<>();

        while (!testList.isEmpty() && testList.size() >= 2) {
            powerList.clear();

            TestData endTest = testList.remove(testList.size() - 1);
            TestData beginTest = null;
            for (TestData t : testList) {
                if (t.getTestName().equals(endTest.getTestName())) {
                    beginTest = t;
                    break;
                }
            }
            testList.remove(beginTest);

            if (beginTest.getTimestamp() > endTest.getTimestamp()) {
                TestData tmp = beginTest;
                beginTest = endTest;
                endTest = tmp;
            }

            long testDurationInMs = endTest.getTimestamp() - beginTest.getTimestamp();

            List<PowerapiData> allPowerapi = new ArrayList<>();
            for (PowerapiData papiD : powerapiList) {
                if (papiD.getTimestamp() >= beginTest.getTimestamp() && papiD.getTimestamp() <= endTest.getTimestamp()) {
                    allPowerapi.add(papiD);
                }
            }

            for (PowerapiData papiD : allPowerapi) {
                powerList.add(papiD.getPower());
            }

            if (powerList.size() != 0) {
                double sumPowers = 0;
                for (Double power : powerList) {
                    sumPowers += power;
                }

                double averagePowerInMilliWatts = sumPowers / powerList.size();
                double energy = Math.convertToJoule(averagePowerInMilliWatts, (double) testDurationInMs);


                for (PowerapiData papiD : allPowerapi) {
                    powerapiCIList.add(new PowerapiCI(papiD.getPower(), papiD.getTimestamp(), beginTest.getTestName(), beginTest.getTimestamp(), endTest.getTimestamp(), testDurationInMs, energy));
                }

            } else {/* Si aucune mesure n'a été prise pour ce test */
                powerapiCIList.add(new PowerapiCI(0d, beginTest.getTimestamp(), beginTest.getTestName(), beginTest.getTimestamp(), endTest.getTimestamp(), (long) 0, (double) 0));
            }

        }

        return addEstimatedEnergyFormTests(powerapiCIList, powerapiList);
    }

    public List<PowerapiCI> addEstimatedEnergyFormTests(List<PowerapiCI> powerapiCIList, List<PowerapiData> powerapiList) {
        String lastTestName = "";
        double timeBefore = 0;
        double timeAfter = 0;
        double timeFirst;
        double timeLast;
        Double powerBefore;
        Double powerAfter;
        Double powerFirst;
        Double powerLast;
        ArrayList<Double> powerList = new ArrayList<>();
        ArrayList<Long> timeList = new ArrayList<>();
        double totalEnergy;
        double estimatedEnergyFromBeforeToFirst;
        double estimatedEnergyFromLastToAfter;
        double estimatedEnergyFromBeginToFirst;
        double estimatedEnergyFromLastToEnd;
        double estimatedEnergyFromFirstToAfter;
        double estimatedEnergyFromLFirstToEnd;
        double estimatedEnergyFromBeforeToAfter;
        double estimatedEnergyFromBeginToEnd;
        List<PowerapiData> allPowerapi;

//        powerapiList.sort();
        for (PowerapiCI test : powerapiCIList) {
            if (!test.getTestName().equals(lastTestName)) {
                powerBefore = 0.0;
                powerAfter = Double.MAX_VALUE;
                powerList.clear();
                timeList.clear();

                for (PowerapiData papid : powerapiList) {
                    if (papid.getTimestamp() < test.getTimeBeginTest()) {
                        powerBefore = papid.getPower();
                        timeBefore = papid.getTimestamp();
                    }

                    if (papid.getTimestamp() > test.getTimeEndTest()) {
                        powerAfter = papid.getPower();
                        timeAfter = papid.getTimestamp();
                        break;
                    }
                }

                allPowerapi = new ArrayList<>();
                for (PowerapiData papiD : powerapiList) {
                    if (papiD.getTimestamp() >= test.getTimestamp() && papiD.getTimestamp() <= test.getTimestamp()) {
                        allPowerapi.add(papiD);
                    }
                }

                if (allPowerapi.isEmpty()) {
                    System.out.println("Nom du test vide: " + test.getTestName());
                }

                for (PowerapiData papiD : allPowerapi) {
                    powerList.add(papiD.getPower());
                    timeList.add(papiD.getTimestamp());
                }


                if (powerList.size() != 0) {
                    timeFirst = (double) timeList.get(0);
                    powerFirst = powerList.get(0);
                    timeLast = (double) timeList.get(timeList.size() - 1);
                    powerLast = powerList.get(powerList.size() - 1);

                    estimatedEnergyFromBeforeToFirst = Math.convertToJoule((powerBefore + powerFirst) / 2, (double) timeFirst - timeBefore);
                    estimatedEnergyFromLastToAfter = Math.convertToJoule((powerLast + powerAfter) / 2, (double) timeAfter - timeLast);
                    estimatedEnergyFromBeginToFirst = (estimatedEnergyFromBeforeToFirst * (timeFirst - test.getTimeBeginTest())) / Constants.FREQUENCY;
                    estimatedEnergyFromLastToEnd = (estimatedEnergyFromLastToAfter * (test.getTimeEndTest() - timeLast)) / Constants.FREQUENCY;
                    totalEnergy = estimatedEnergyFromBeginToFirst + test.getEnergy() + estimatedEnergyFromLastToEnd;
                } else if (powerList.size() == 1) {
                    timeFirst = (double) timeList.get(0);
                    powerFirst = powerList.get(0);

                    estimatedEnergyFromBeforeToFirst = Math.convertToJoule((powerBefore + powerFirst) / 2, (double) timeFirst - timeBefore);
                    estimatedEnergyFromFirstToAfter = Math.convertToJoule((powerFirst + powerAfter) / 2, (double) timeAfter - timeFirst);

                    estimatedEnergyFromBeginToFirst = (estimatedEnergyFromBeforeToFirst * (timeFirst - test.getTimeBeginTest())) / Constants.FREQUENCY;
                    estimatedEnergyFromLFirstToEnd = (estimatedEnergyFromFirstToAfter * (test.getTimeEndTest() - timeFirst)) / Constants.FREQUENCY;
                    totalEnergy = estimatedEnergyFromBeginToFirst + estimatedEnergyFromLFirstToEnd;
                } else {
                    estimatedEnergyFromBeforeToAfter = Math.convertToJoule((powerBefore + powerAfter) / 2, (double) timeAfter - timeBefore);
                    estimatedEnergyFromBeginToEnd = (estimatedEnergyFromBeforeToAfter * (test.getTimeEndTest() - test.getTimeBeginTest())) / Constants.FREQUENCY;

                    totalEnergy = estimatedEnergyFromBeginToEnd;
                }

                for (PowerapiCI testid : powerapiCIList) {
                    if (testid.getTestName().equals(test.getTestName())) {
                        testid.setEnergy((totalEnergy));
                    }
                }

                lastTestName = test.getTestName();
            }
        }
        return powerapiCIList;
    }

    public void sendPowerapiciData(long debutApp, String branch, String buildName, String commitName, String urlScm, List<String> powerapiCSV, List<String> testCSV) {
        if (powerapiCSV.isEmpty() || testCSV.isEmpty() || testCSV.size() != powerapiCSV.size()) {
            System.out.println("Listes vides ou pas de la même taille");
            return;
        }

        String appName = urlScm.substring(urlScm.lastIndexOf("/") + 1, urlScm.length() - 4);
        Map<String, String> classes = surefireDao.parseSurefireXML();

        List<List<PowerapiCI>> powerapiCIList = new ArrayList<>();

        for (int i = 0; i < powerapiCSV.size(); i++) {
            String[] powerapi = powerapiCSV.get(i).split("mW");
            List<PowerapiData> powerapiList = new ArrayList<>();
            for (String st : powerapi) {
                powerapiList.add(new PowerapiData(st));
            }
            String[] test = testCSV.get(i).split("\n");
            List<TestData> testList = new ArrayList<>();
            for(String st : test){
                testList.add(new TestData(st));
            }
            powerapiCIList.add(findListPowerapiCI(powerapiList, testList));
        }


        ResultatApplication resultatApplication = new ResultatApplication(debutApp, branch, buildName, commitName, appName, urlScm);
        resultatApplication = Converter.fillResultatApplication(resultatApplication, powerapiCIList, classes);

        powerapiDao.sendResultat(Constants.ACTUAL_INDEX, resultatApplication);
    }
}
