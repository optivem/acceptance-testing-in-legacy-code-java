package com.optivem.eshop.systemtest.core.drivers.commons.clients;

public class Closer {
    public static void close(AutoCloseable client) {
        if(client != null) {
            try {
                client.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
