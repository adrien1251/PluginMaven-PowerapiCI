package com.powerapi;

public class PowerapiData implements Comparable {
    private String muid;
    private String devices;
    private String targets;
    private long timestamp;
    private Double power;

    public int compareTo(Object powerapiData) {
        return ((int) (timestamp - ((PowerapiData) powerapiData).timestamp));
    }

    public PowerapiData(String powerapiDataCSV) {
        String[] parsingCSV = powerapiDataCSV.split(";");
        for (String st : parsingCSV) {
            String[] secParsing = st.split("=");
            final String switchArg = secParsing[0];

            if(switchArg.equals("muid")) muid = secParsing[1];
            else if(switchArg.equals("devices")) devices = secParsing[1];
            else if(switchArg.equals("targets")) targets = secParsing[1];
            else if(switchArg.equals("timestamp")) timestamp = Long.parseLong(secParsing[1]);
            else if(switchArg.equals("power")) power = Double.parseDouble(secParsing[1]);
        }

    }

    public String getMuid() {
        return muid;
    }

    public void setMuid(String muid) {
        this.muid = muid;
    }

    public String getDevices() {
        return devices;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }


}
