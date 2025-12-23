package com.optivem.eshop.systemtest.core.shop.driver;

import com.optivem.eshop.systemtest.core.shop.client.dtos.GetOrderResponse;
import com.optivem.eshop.systemtest.core.shop.client.dtos.PlaceOrderRequest;
import com.optivem.eshop.systemtest.core.shop.client.dtos.PlaceOrderResponse;
import com.optivem.eshop.systemtest.core.shop.driver.dtos.error.SystemError;
import com.optivem.lang.Result;

public interface ShopDriver extends AutoCloseable {
    Result<Void, SystemError> goToShop();

    Result<PlaceOrderResponse, SystemError> placeOrder(PlaceOrderRequest request);

    Result<Void, SystemError> cancelOrder(String orderNumber);

    Result<GetOrderResponse, SystemError> viewOrder(String orderNumber);
}
