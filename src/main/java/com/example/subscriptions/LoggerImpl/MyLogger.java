package com.example.subscriptions.LoggerImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {

    private static final Logger LOGGER = LogManager.getLogger(MyLogger.class);

    public static final void trace(String msg){
        LOGGER.trace(msg);
    }
    public static final void debug(String msg){
        LOGGER.debug(msg);
    }
    public static final void info(String msg){
        LOGGER.info(msg);
    }
    public static final void warn(String msg){
        LOGGER.warn(msg);
    }
    public static final void error(String msg){
        LOGGER.error(msg);
    }
    public static final void fatal(String msg){
        LOGGER.fatal(msg);
    }
}
