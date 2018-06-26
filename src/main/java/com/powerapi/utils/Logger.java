package com.powerapi.utils;

import org.apache.maven.plugin.logging.Log;

public class Logger {
    private static Logger logger = null;
    private static Log log;

    public static void setLog(Log log){
        Logger.log = log;
    }

    public static void info(String message){
        log.info(message);
    }

    public static void error(String message){
        log.error(message);
    }

    public static void info(String message, Throwable throwable){
        log.info(message, throwable);
    }

    public static void error(String message, Throwable throwable){
        log.error(message, throwable);
    }
}
