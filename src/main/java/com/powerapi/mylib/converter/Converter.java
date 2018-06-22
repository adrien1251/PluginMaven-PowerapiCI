package com.powerapi.mylib.converter;

import com.google.gson.Gson;
import com.powerapi.PowerapiCI;
import com.powerapi.mylib.json.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Converter {


    public static String resultatApplicationToJson(ResultatApplication resultatApplication) {
        return new Gson().toJson(resultatApplication).toString() + '\n';
    }

    public static ResultatApplication fillResultatApplication(ResultatApplication resultatApplication, List<List<PowerapiCI>> powerapiCIList, final Map<String, String> classes) {
        List<Classe> classeL = new ArrayList<Classe>();

        String lastTestName = "";
        String lastClassName = "";
        for (PowerapiCI papici : powerapiCIList.get(0)) {
            if (!papici.getTestName().equals(lastTestName)) {
                if (!classes.get(papici.getTestName()).equals(lastClassName)) {
                    lastClassName = classes.get(papici.getTestName());
                    classeL.add(new Classe(lastClassName));
                }

                List<Iteration> iterations = new ArrayList<Iteration>();
                int cpt = 1;

                for (List<PowerapiCI> papiciL : powerapiCIList) {
                    List<PowerData> powerDatas = new ArrayList<PowerData>();
                    double averageEnergy = 0;
                    long time_b = 0;
                    long time_e = 0;
                    for (PowerapiCI papici1 : papiciL) {
                        if (papici1.getTestName().equals(papici.getTestName())) {
                            powerDatas.add(new PowerData(papici1.getPower(), papici1.getTimestamp()));
                            averageEnergy = papici1.getEnergy();
                            time_b = papici1.getTimeBeginTest();
                            time_e = papici1.getTimeEndTest();
                        }

                    }

                    iterations.add(new Iteration(cpt, averageEnergy, time_b, time_e, powerDatas));
                    cpt++;
                }

                Methods newMethods = new Methods(papici.getTestName(), (papici.getTimeEndTest() - papici.getTimeBeginTest()));
                newMethods.setIterations(iterations);

                double energy = 0;
                for (Iteration ite : newMethods.getIterations()) {
                    energy += ite.getEnergy();
                }
                newMethods.setEnergy(energy / newMethods.getIterations().size());

                for (Classe c : classeL) {
                    if (c.getName().equals(classes.get(papici.getTestName()))) {
                        c.getMethods().add(newMethods);
                        break;
                    }
                }

            }

            lastTestName = papici.getTestName();
        }

        for (Classe c : classeL) {
            long duration = 0;
            for (Methods m : c.getMethods()) {
                duration += m.getDuration();
            }
            c.setDuration(duration);

            double energy = 0;
            for (Methods m : c.getMethods()) {
                energy += m.getEnergy();
            }
            c.setEnergy(energy);
        }

        resultatApplication.setClasses(classeL);

        //Total Energy
        double totalEnergy = 0;
        long totalDuration = 0;
        for (Classe c : resultatApplication.getClasses()) {
            totalEnergy += c.getEnergy();
            totalDuration += c.getDuration();
        }

        resultatApplication.setDuration(totalDuration);
        resultatApplication.setEnergy(totalEnergy);


        return resultatApplication;
    }

}
