package com.powerapi.dao;


import com.powerapi.enums.HttpMethod;
import com.powerapi.mylib.Constants;
import com.powerapi.mylib.converter.Converter;
import com.powerapi.mylib.json.ResultatApplication;

public class PowerapiDao {
    private static final PowerapiDao INSTANCE =  new PowerapiDao();

    private PowerapiDao(){

    }

    public static PowerapiDao getInstance(){
        return INSTANCE;
    }

    public void sendResultat(String index, ResultatApplication resultatApplication) {
        UtilsDao.executeQuery(Constants.ELASTIC_PATH + index +"/doc", Converter.resultatApplicationToJson(resultatApplication), HttpMethod.POST);
    }



}
