package com.optivem.eshop.systemtest.core.shop.client.api;

import com.optivem.http.JsonHttpClient;
import com.optivem.eshop.systemtest.core.commons.error.ProblemDetailResponse;
import com.optivem.eshop.systemtest.core.shop.client.api.controllers.HealthController;
import com.optivem.eshop.systemtest.core.shop.client.api.controllers.OrderController;

public class ShopApiClient {

    private final HealthController healthController;
    private final OrderController orderController;

    public ShopApiClient(JsonHttpClient<ProblemDetailResponse> httpGateway) {
        this.healthController = new HealthController(httpGateway);
        this.orderController = new OrderController(httpGateway);
    }

    public HealthController health() {
        return healthController;
    }

    public OrderController orders() {
        return orderController;
    }
}

