package com.powerapi.mylib.json;

import com.powerapi.mylib.Constants;

import java.util.List;

public class ResultatApplication {
    private long timestamp;
    private String branch;
    private String build_name;
    private String build_url;
    /**
     * Energy total of the project
     */
    private double energy;
    private String app_name;
    private long duration;
    private String commit_name;
    private List<Classe> classes;
    private String scm_url;

    public ResultatApplication(long timestamp, String branch, String build_name, double energy, String app_name, long duration, String commit_name, List<Classe> classes) {
        this.timestamp = timestamp;
        this.branch = branch;
        this.build_name = build_name;
        this.energy = energy;
        this.app_name = app_name;
        this.duration = duration;
        this.commit_name = commit_name;
        this.classes = classes;
    }

    public ResultatApplication(long timestamp, String branch, String build_name, String commit_name, String app_name, String scm_url) {
        this.timestamp = timestamp;
        this.branch = branch;
        this.build_name = build_name;
        this.commit_name = commit_name;
        this.app_name = app_name;
        this.scm_url = scm_url;
        this.build_url = Constants.BUILD_URL + branch + "/" + build_name + "/pipeline";
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBuild_name() {
        return build_name;
    }

    public void setBuild_name(String build_name) {
        this.build_name = build_name;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getCommit_name() {
        return commit_name;
    }

    public void setCommit_name(String commit_name) {
        this.commit_name = commit_name;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public String getScm_url() {
        return scm_url;
    }

    public void setScm_url(String scm_url) {
        this.scm_url = scm_url;
    }

    public String getBuild_url(){ return build_url; }


}
