package com.powerapi.Entity;

import com.powerapi.mylib.Constants;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
}
