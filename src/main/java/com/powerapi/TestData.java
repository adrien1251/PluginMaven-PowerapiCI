package com.powerapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestData {

    private long timestamp;
    private String testName;
    private String startOrEnd;

    public TestData(String testDataCSV) {
        String[] parsingCSV = testDataCSV.split(";");
        for (String st : parsingCSV) {
            String[] secParsing = st.split("=");
            final String switchArg = secParsing[0];
            if (secParsing[0].equals("timestamp")) timestamp = Long.parseLong(secParsing[1]);
            else if (secParsing[0].equals("testname")) testName = secParsing[1];
            else if (secParsing[0].equals("startorend")) startOrEnd = secParsing[1];
        }

    }
}
