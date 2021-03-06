package com.powerapi;

import com.powerapi.service.PowerapiService;
import com.powerapi.utils.CommonUtils;
import com.powerapi.utils.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Says "Hi" to the user.
 */
@Mojo(name = "runtest")
public class PowerapiMojo extends AbstractMojo {
    private PowerapiService powerapiService = new PowerapiService();


    private List<String> powerapiCSVList = new ArrayList<>();
    private List<String> testCSVList = new ArrayList<>();

    public void execute()  {
        Logger.setLog(getLog());

        executes();
        powerapiService.sendPowerapiciData(125412451, "MASTER", "40", "unname", "uneurl", powerapiCSVList, testCSVList);

        getLog().info("Data send");
    }

    private void executes() {
       // String[] cmd = {"sh", "-c", "echo toto > untest.csv; (mvn test -DforkCount=0 | grep timestamp= | cut -d '-' -f 2 | tr -d ' ') > test1.csv & powerapi duration 30 modules procfs-cpu-simple monitor --frequency 50 --console --pids \\$! | grep muid) > data1.csv;"};
        String[] cmd1 = {"sh", "-c", "(mvn test -DforkCount=0 | grep timestamp= | cut -d '-' -f 2 | tr -d ' ') > test1.csv & (powerapi duration 30 modules procfs-cpu-simple monitor --frequency 50 --console --all | grep muid) > data1.csv;"};

        try {
            getLog().info("En cours d'execution...");
            Process p = Runtime.getRuntime().exec(cmd1);
            getLog().info(CommonUtils.readProcessus(p));

            p.waitFor();
            Process powerapiProc = Runtime.getRuntime().exec(new String[]{"sh", "-c", "cat data1.csv | tr '\n' ' '"});
            powerapiCSVList.add(CommonUtils.readProcessus(powerapiProc));

            Process testProc = Runtime.getRuntime().exec(new String[]{"sh", "-c", "cat test1.csv | grep timestamp= | cut -d '-' -f 2 | tr -d ' '"});
            testCSVList.add(CommonUtils.readProcessus(testProc));
        } catch (IOException | InterruptedException e) {
            getLog().error("", e);
        }


    }



}