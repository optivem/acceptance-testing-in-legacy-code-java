package com.optivem.eshop.systemtest.core.clients.system.api;

import com.optivem.eshop.systemtest.core.clients.commons.TestHttpClient;
import com.optivem.eshop.systemtest.core.clients.system.api.controllers.EchoController;
import com.optivem.eshop.systemtest.core.clients.system.api.controllers.OrderController;
import com.optivem.eshop.systemtest.core.clients.system.api.dtos.ProblemDetailResponse;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ShopApiClient implements AutoCloseable {

    private final HttpClient httpClient;
    private final TestHttpClient testHttpClient;
    private final EchoController echoController;
    private final OrderController orderController;

    public ShopApiClient(String baseUrl) {
        this.httpClient = HttpClient.newHttpClient();
        this.testHttpClient = new TestHttpClient(httpClient, baseUrl);
        this.echoController = new EchoController(testHttpClient);
        this.orderController = new OrderController(testHttpClient);
    }

    public EchoController echo() {
        return echoController;
    }

    public OrderController orders() {
        return orderController;
    }

    public List<String> getErrorMessages(HttpResponse<String> httpResponse) {
        var problemDetail = testHttpClient.readBody(httpResponse, ProblemDetailResponse.class);

        var errors = new ArrayList<String>();

        if (problemDetail.getDetail() != null) {
            errors.add(problemDetail.getDetail());
        }

        if(problemDetail.getErrors() != null) {
            for (var error : problemDetail.getErrors()) {
                errors.add(error.getMessage());
            }
        }

        return errors;
    }

    @Override
    public void close() {
        httpClient.close();
    }
}

