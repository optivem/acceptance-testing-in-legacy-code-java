package com.optivem.eshop.systemtest.core.drivers.system.shop.api.client.controllers;

import com.optivem.eshop.systemtest.core.drivers.commons.clients.TestHttpClient;
import com.optivem.eshop.systemtest.core.drivers.commons.clients.TestHttpUtils;
import com.optivem.eshop.systemtest.core.drivers.commons.Result;

public class HealthController {

    private static final String ENDPOINT = "/health";

    private final TestHttpClient httpClient;

    public HealthController(TestHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Result<Void> checkHealth() {
        var httpResponse = httpClient.get(ENDPOINT);
        return TestHttpUtils.getOkResultOrFailure(httpResponse);
    }
}

