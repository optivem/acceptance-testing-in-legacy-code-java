package com.optivem.eshop.systemtest.core.clients.api.dtos;

import lombok.Data;

@Data
public class PlaceOrderRequest {
    private String sku;
    private String quantity;
    private String country;
}

