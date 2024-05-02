package org.example.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppLogger {

    // Define a logger for the class
    private static final Logger LOGGER = Logger.getLogger(AppLogger.class.getName());



    // Method to log success messages
    public static void logSuccess(String message) {
        LOGGER.log(Level.INFO, "[SUCCESS] " + message);
    }

    // Method to log error messages
    public static void logError(String message, Throwable throwable) {
        LOGGER.log(Level.SEVERE, "[ERROR] " + message, throwable);
    }
}
