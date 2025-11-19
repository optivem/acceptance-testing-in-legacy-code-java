package com.optivem.eshop.systemtest.core.drivers;

public class DriverCloser {

    public static void close(AutoCloseable... drivers) {
        for (var driver : drivers) {
            if (driver != null) {
                try {
                    driver.close();
                } catch (Exception e) {
                    System.err.println("Error closing driver: " + e.getMessage());
                }
            }
        }
    }
}

