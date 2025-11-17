package com.optivem.eshop.systemtest.core.clients;

public class ClientCloser {
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
