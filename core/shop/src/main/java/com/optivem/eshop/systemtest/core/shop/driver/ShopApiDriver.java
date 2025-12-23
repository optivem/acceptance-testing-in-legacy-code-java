package com.optivem.eshop.systemtest.core.shop.driver;

import com.optivem.eshop.systemtest.core.shop.client.dtos.PlaceOrderRequest;
import com.optivem.lang.Closer;
import com.optivem.eshop.systemtest.core.commons.error.Error;
import com.optivem.http.JsonHttpClient;
import com.optivem.eshop.systemtest.core.commons.error.ProblemDetailResponse;
import com.optivem.eshop.systemtest.core.shop.client.api.ShopApiClient;
import com.optivem.eshop.systemtest.core.shop.client.dtos.GetOrderResponse;
import com.optivem.eshop.systemtest.core.shop.client.dtos.PlaceOrderResponse;
import com.optivem.lang.Result;

import java.net.http.HttpClient;

public class ShopApiDriver implements ShopDriver {
    private final HttpClient httpClient;
    private final ShopApiClient apiClient;

    public ShopApiDriver(String baseUrl) {
        this.httpClient = HttpClient.newHttpClient();
        var testHttpClient = new JsonHttpClient<>(httpClient, baseUrl, ProblemDetailResponse.class);
        this.apiClient = new ShopApiClient(testHttpClient);
    }

    @Override
    public Result<Void, Error> goToShop() {
        return apiClient.health().checkHealth();
    }

    @Override
    public Result<PlaceOrderResponse, Error> placeOrder(PlaceOrderRequest request) {
        return apiClient.orders().placeOrder(request);
    }

    @Override
    public Result<Void, Error> cancelOrder(String orderNumber) {
        return apiClient.orders().cancelOrder(orderNumber);
    }

    @Override
    public Result<GetOrderResponse, Error> viewOrder(String orderNumber) {
        return apiClient.orders().viewOrder(orderNumber);
    }

    @Override
    public void close() {
        Closer.close(httpClient);
    }
}

