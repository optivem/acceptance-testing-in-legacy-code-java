package com.optivem.eshop.systemtest.core.tax.driver.client;

import com.optivem.eshop.systemtest.core.tax.driver.client.commons.TaxHttpClient;
import com.optivem.eshop.systemtest.core.tax.driver.client.controllers.HealthController;

public abstract class BaseTaxClient {

    protected final TaxHttpClient httpClient;
    private final HealthController healthController;

    protected BaseTaxClient(TaxHttpClient httpClient) {
        this.httpClient = httpClient;
        this.healthController = new HealthController(httpClient);
    }

    public HealthController health() {
        return healthController;
    }
}

