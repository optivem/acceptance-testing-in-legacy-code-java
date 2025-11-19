package com.optivem.eshop.systemtest.core.drivers.system;

import com.optivem.eshop.systemtest.core.clients.system.api.ShopApiClient;
import com.optivem.eshop.systemtest.core.clients.system.api.dtos.GetOrderResponse;

import java.net.http.HttpResponse;

public class ShopApiDriver implements AutoCloseable {

    private final ShopApiClient shopApiClient;

    public ShopApiDriver(ShopApiClient shopApiClient) {
        this.shopApiClient = shopApiClient;
    }

    public String placeOrder(String sku, int quantity, String country) {
        var httpResponse = shopApiClient.orders().placeOrder(sku, String.valueOf(quantity), country);
        var response = shopApiClient.orders().assertOrderPlacedSuccessfully(httpResponse);
        return response.getOrderNumber();
    }

    public GetOrderResponse getOrderDetails(String orderNumber) {
        var httpResponse = shopApiClient.orders().viewOrder(orderNumber);
        return shopApiClient.orders().assertOrderViewedSuccessfully(httpResponse);
    }

    public void cancelOrder(String orderNumber) {
        var httpResponse = shopApiClient.orders().cancelOrder(orderNumber);
        shopApiClient.orders().assertOrderCancelledSuccessfully(httpResponse);
    }

    public HttpResponse<String> attemptPlaceOrder(String sku, String quantity, String country) {
        return shopApiClient.orders().placeOrder(sku, quantity, country);
    }

    public void assertOrderPlacementFailed(HttpResponse<String> httpResponse) {
        shopApiClient.orders().assertOrderPlacementFailed(httpResponse);
    }

    public String getOrderPlacementErrorMessage(HttpResponse<String> httpResponse) {
        return shopApiClient.orders().getErrorMessage(httpResponse);
    }

    @Override
    public void close() {
        if (shopApiClient != null) {
            shopApiClient.close();
        }
    }
}

