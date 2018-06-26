package com.powerapi.dao;

import com.powerapi.utils.CommonUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

public class SurefireDao {
    private static final SurefireDao INSTANCE =  new SurefireDao();

    private SurefireDao(){

    }

    public static SurefireDao getInstance(){
        return INSTANCE;
    }

    public HashMap<String, String> parseSurefireXML() {
        HashMap<String, String> classes = new HashMap<>();

        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            ByteArrayInputStream input = new ByteArrayInputStream(("<test>" + getXmlReport() + "</test>").getBytes());
            Document doc = builder.parse(input);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("testsuite");
            //TODO : Can be simplified by XPATH request
            for (int i = 0; i < nList.getLength(); i++) {
                Element element = (Element) nList.item(i);
                NodeList testCaseNode = element.getElementsByTagName("testcase");

                for (int j = 0; j < testCaseNode.getLength(); j++) {
                    Element testCase = (Element) testCaseNode.item(j);
                    classes.put(testCase.getAttribute("name"), ((Element) nList.item(i)).getAttribute("name"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return classes;
    }

    public String getXmlReport() {
        //TODO : Replace sh by java
        String[] cmd = {"sh", "-c", "cat target/surefire-reports/TEST-* | sed s/'testsuite>'/'testsuite>\\n'/g | grep 'testsuite\\|testcase'"};
        String retour = "";
        try {
            Process powerapiProc = Runtime.getRuntime().exec(cmd);
            retour += CommonUtils.readProcessus(powerapiProc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retour;
    }
}
