package com.optivem.eshop.systemtest.core.dsl.system.commands;

import com.optivem.eshop.systemtest.core.drivers.system.ShopDriver;
import com.optivem.eshop.systemtest.core.drivers.system.commons.dtos.PlaceOrderRequest;
import com.optivem.eshop.systemtest.core.drivers.system.commons.dtos.PlaceOrderResponse;
import com.optivem.results.Result;

public class PlaceOrderCommand {
    private final ShopDriver driver;
    private String sku;
    private String quantity;
    private String country;

    public PlaceOrderCommand(ShopDriver driver) {
        this.driver = driver;
    }

    public PlaceOrderCommand sku(String sku) {
        this.sku = sku;
        return this;
    }

    public PlaceOrderCommand quantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public PlaceOrderCommand country(String country) {
        this.country = country;
        return this;
    }

    public Result<PlaceOrderResponse> execute() {
        var request = PlaceOrderRequest.builder()
                .sku(sku)
                .quantity(quantity)
                .country(country)
                .build();
        return driver.placeOrder(request);
    }
}
