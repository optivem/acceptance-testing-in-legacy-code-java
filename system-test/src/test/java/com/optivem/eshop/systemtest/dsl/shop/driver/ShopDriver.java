package com.optivem.eshop.systemtest.dsl.shop.driver;

import com.optivem.eshop.systemtest.dsl.shop.driver.commons.dtos.GetOrderResponse;
import com.optivem.eshop.systemtest.dsl.shop.driver.commons.dtos.PlaceOrderRequest;
import com.optivem.eshop.systemtest.dsl.shop.driver.commons.dtos.PlaceOrderResponse;
import com.optivem.results.Result;

public interface ShopDriver extends AutoCloseable {
    Result<Void> goToShop();

    Result<PlaceOrderResponse> placeOrder(PlaceOrderRequest request);

    Result<Void> cancelOrder(String orderNumber);

    Result<GetOrderResponse> viewOrder(String orderNumber);
}
