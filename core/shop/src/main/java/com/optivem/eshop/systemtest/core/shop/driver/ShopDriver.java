package com.optivem.eshop.systemtest.core.shop.driver;

import com.optivem.eshop.systemtest.core.shop.client.dtos.GetOrderResponse;
import com.optivem.eshop.systemtest.core.shop.client.dtos.PlaceOrderRequest;
import com.optivem.eshop.systemtest.core.shop.client.dtos.PlaceOrderResponse;
import com.optivem.eshop.systemtest.core.commons.error.Error;
import com.optivem.lang.Result;

public interface ShopDriver extends AutoCloseable {
    Result<Void, Error> goToShop();

    Result<PlaceOrderResponse, Error> placeOrder(PlaceOrderRequest request);

    Result<Void, Error> cancelOrder(String orderNumber);

    Result<GetOrderResponse, Error> viewOrder(String orderNumber);
}
