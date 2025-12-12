package com.optivem.eshop.systemtest.core.external.erp.driver.client;

import com.optivem.http.HttpGateway;
import com.optivem.eshop.systemtest.core.external.erp.driver.client.controllers.HealthController;
import com.optivem.eshop.systemtest.core.external.erp.driver.client.controllers.ProductController;

public class ErpApiClient {

    private final HealthController healthController;
    private final ProductController productController;

    public ErpApiClient(HttpGateway httpGateway) {
        this.healthController = new HealthController(httpGateway);
        this.productController = new ProductController(httpGateway);
    }

    public HealthController health() {
        return healthController;
    }

    public ProductController products() {
        return productController;
    }
}
