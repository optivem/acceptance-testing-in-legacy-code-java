package com.optivem.eshop.systemtest.core.clients.api;

import com.optivem.eshop.systemtest.core.clients.api.controllers.OrderControllerClient;

import java.net.http.HttpClient;

public class ApiClient implements AutoCloseable {

    private final HttpClient client;
    private final OrderControllerClient orderControllerClient;

    public ApiClient(String baseUrl) {
        this.client = HttpClient.newHttpClient();
        this.orderControllerClient = new OrderControllerClient(client, baseUrl);
    }

    public OrderControllerClient getOrderController() {
        return orderControllerClient;
    }

    @Override
    public void close() {
        client.close();
    }
}

