package com.powerapi;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Says "Hi" to the user.
 */
@Mojo(name = "runtest")
public class PowerapiMojo extends AbstractMojo {
    List<String> powerapiCSVList = new ArrayList<String>();
    List<String> testCSVList = new ArrayList<String>();

    public void execute() throws MojoExecutionException {
        executes();
        getLog().info("Data sending");


        System.out.println("powerapi");
        for(String st : powerapiCSVList){
            System.out.println(st);
        }

        System.out.println("test");
        for(String st : testCSVList){
            System.out.println(st);
        }

        new ESQuery().sendPowerapiciData(125412451, "MASTER", "40", "unname", "uneurl", powerapiCSVList, testCSVList, getXmlReport());

        getLog().info("Data send");
    }

    public void executes() {
        String[] cmd = {"sh", "-c", "(mvn test -DforkCount=0 | grep timestamp= | cut -d '-' -f 2 | tr -d ' ') > test1.csv & powerapi duration 30 modules procfs-cpu-simple monitor --frequency 50 --console --pids \\$! | grep muid) > data1.csv;"};

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            getLog().info(readProc(p));

            p.waitFor();
            Process powerapiProc = Runtime.getRuntime().exec(new String[]{"sh", "-c", "cat data1.csv | tr '\n' ' '"});
            powerapiCSVList.add(readProc(powerapiProc));

            Process testProc = Runtime.getRuntime().exec(new String[]{"sh", "-c", "cat test1.csv | grep timestamp= | cut -d '-' -f 2 | tr -d ' '"});
            testCSVList.add(readProc(testProc));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }


    }

    public String readProc(Process p) throws IOException, InterruptedException {
        String retour = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            retour += line;
        }
        reader.close();

        return retour;
    }

    public String getXmlReport() {
        String[] cmd = {"sh", "-c", "cat target/surefire-reports/TEST-* | sed s/'testsuite>'/'testsuite>\\\\n'/g | grep 'testsuite\\\\|testcase'"};
        String retour = "";
        try {
            Process powerapiProc = Runtime.getRuntime().exec(cmd);
            retour += readProc(powerapiProc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return retour;
    }
}