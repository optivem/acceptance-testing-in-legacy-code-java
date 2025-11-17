package com.optivem.eshop.systemtest.core.clients.system.api.controllers;

import com.optivem.eshop.systemtest.core.clients.commons.BaseController;
import com.optivem.eshop.systemtest.core.clients.system.api.dtos.GetOrderResponse;
import com.optivem.eshop.systemtest.core.clients.system.api.dtos.PlaceOrderRequest;
import com.optivem.eshop.systemtest.core.clients.system.api.dtos.PlaceOrderResponse;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class OrderController extends BaseController {

    public OrderController(HttpClient client, String baseUrl) {
        super(client, baseUrl, "orders");
    }

    public HttpResponse<String> placeOrder(String sku, String quantity, String country) {
        var request = new PlaceOrderRequest();
        request.setSku(sku);
        request.setQuantity(quantity);
        request.setCountry(country);

        return post(request);
    }

    public PlaceOrderResponse assertOrderPlacedSuccessfully(HttpResponse<String> httpResponse) {
        assertCreated(httpResponse);
        return readBody(httpResponse, PlaceOrderResponse.class);
    }

    public void assertOrderPlacementFailed(HttpResponse<String> httpResponse) {
        assertUnprocessableEntity(httpResponse);
    }

    public String getErrorMessage(HttpResponse<String> httpResponse) {
        return httpResponse.body();
    }

    public HttpResponse<String> viewOrder(String orderNumber) {
        return get(orderNumber);
    }

    public GetOrderResponse assertOrderViewedSuccessfully(HttpResponse<String> httpResponse) {
        assertOk(httpResponse);
        return readBody(httpResponse, GetOrderResponse.class);
    }

    public HttpResponse<String> cancelOrder(String orderNumber) {
        return post(orderNumber + "/cancel");
    }

    public void assertOrderCancelledSuccessfully(HttpResponse<String> httpResponse) {
        assertNoContent(httpResponse);
    }
}

