package org.example.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppLogger {


    private static final Logger LOGGER = Logger.getLogger(AppLogger.class.getName());




    public static void logSuccess(String message) {
        LOGGER.log(Level.INFO, "[SUCCESS] " + message);
    }


    public static void logError(String message, Throwable throwable) {
        LOGGER.log(Level.SEVERE, "[ERROR] " + message, throwable);
    }
}
