package com.optivem.eshop.systemtest.core.drivers.system.shop.api.client.controllers;

import com.optivem.commons.http.HttpGateway;
import com.optivem.commons.http.HttpUtils;
import com.optivem.commons.utils.Result;

public class HealthController {

    private static final String ENDPOINT = "/health";

    private final HttpGateway httpClient;

    public HealthController(HttpGateway httpClient) {
        this.httpClient = httpClient;
    }

    public Result<Void> checkHealth() {
        var httpResponse = httpClient.get(ENDPOINT);
        return HttpUtils.getOkResultOrFailure(httpResponse);
    }
}

