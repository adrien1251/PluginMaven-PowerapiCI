package com.powerapi.dao;

import com.powerapi.enums.HttpMethod;
import com.powerapi.utils.Logger;

import java.net.HttpURLConnection;
import java.net.URL;

public class UtilsDao {
    private static final UtilsDao INSTANCE =  new UtilsDao();

    private UtilsDao(){

    }

    public static UtilsDao getInstance(){
        return INSTANCE;
    }

    /**
     * Send Post data to an url
     *
     * @param url         the target to send
     * @param queryString the query to send
     * @param method      method http to use
     */
    public static void executeQuery(String url, String queryString, HttpMethod method) {
        try {
            URL baseUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) baseUrl.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-ndjson");

            connection.setRequestMethod(method.toString());
            connection.setDoOutput(true);

            byte[] postDataBytes = queryString.getBytes("UTF-8");
            connection.getOutputStream().write(postDataBytes);

            if (connection.getResponseCode() >= HttpURLConnection.HTTP_BAD_REQUEST) {
                Logger.error("Youps.. An error occrured !");
                Logger.error("Code: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Exception e) {
            Logger.error("ExecuteQuery: ", e);
        }
    }
}
