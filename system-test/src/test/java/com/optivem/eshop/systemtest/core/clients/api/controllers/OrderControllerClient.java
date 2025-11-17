package com.optivem.eshop.systemtest.core.clients.api.controllers;

import com.optivem.eshop.systemtest.core.clients.api.dtos.GetOrderResponse;
import com.optivem.eshop.systemtest.core.clients.api.dtos.PlaceOrderRequest;
import com.optivem.eshop.systemtest.core.clients.api.dtos.PlaceOrderResponse;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class OrderControllerClient extends BaseControllerClient {

    public OrderControllerClient(HttpClient client, String baseUrl) {
        super(client, baseUrl);
    }

    public HttpResponse<String> placeOrder(String sku, String quantity, String country) {
        var request = new PlaceOrderRequest();
        request.setSku(sku);
        request.setQuantity(quantity);
        request.setCountry(country);

        return post("api/orders", request);
    }

    public PlaceOrderResponse confirmOrderPlacedSuccessfully(HttpResponse<String> httpResponse) {
        assertCreated(httpResponse);
        return readBody(httpResponse, PlaceOrderResponse.class);
    }

    public void confirmOrderPlacementFailed(HttpResponse<String> httpResponse) {
        assertUnprocessableEntity(httpResponse);
    }

    public String getErrorMessage(HttpResponse<String> httpResponse) {
        return httpResponse.body();
    }

    public HttpResponse<String> viewOrder(String orderNumber) {
        var endpoint = "api/orders/" + orderNumber;
        return get(endpoint);
    }

    public GetOrderResponse confirmOrderViewedSuccessfully(HttpResponse<String> httpResponse) {
        assertOk(httpResponse);
        return readBody(httpResponse, GetOrderResponse.class);
    }

    public HttpResponse<String> cancelOrder(String orderNumber) {
        var endpoint = "api/orders/" + orderNumber + "/cancel";
        return post(endpoint);
    }

    public void confirmOrderCancelledSuccessfully(HttpResponse<String> httpResponse) {
        assertNoContent(httpResponse);
    }
}

