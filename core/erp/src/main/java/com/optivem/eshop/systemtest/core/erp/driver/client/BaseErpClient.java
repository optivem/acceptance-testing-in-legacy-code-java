package com.optivem.eshop.systemtest.core.erp.driver.client;

import com.optivem.eshop.systemtest.core.commons.error.ProblemDetailResponse;
import com.optivem.eshop.systemtest.core.erp.driver.client.controllers.HealthController;
import com.optivem.eshop.systemtest.core.erp.driver.client.controllers.ProductController;
import com.optivem.http.JsonHttpClient;

import java.util.function.Function;

/**
 * Base ERP client with common endpoints shared between real and stub implementations.
 */
public abstract class BaseErpClient<T extends ProductController> {

    protected final JsonHttpClient<ProblemDetailResponse> httpClient;
    private final HealthController healthController;
    private final T productController;

    protected BaseErpClient(
            JsonHttpClient<ProblemDetailResponse> httpClient,
            Function<JsonHttpClient<ProblemDetailResponse>, T> productControllerFactory) {
        this.httpClient = httpClient;
        this.healthController = new HealthController(httpClient);
        this.productController = productControllerFactory.apply(httpClient);
    }

    public HealthController health() {
        return healthController;
    }

    public T products() {
        return productController;
    }
}
