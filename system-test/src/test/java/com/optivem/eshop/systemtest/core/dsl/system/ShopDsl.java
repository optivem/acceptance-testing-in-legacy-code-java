package com.optivem.eshop.systemtest.core.dsl.system;

import com.optivem.eshop.systemtest.core.drivers.system.ShopDriver;
import com.optivem.eshop.systemtest.core.drivers.system.commons.dtos.GetOrderResponse;
import com.optivem.eshop.systemtest.core.dsl.system.commands.PlaceOrderCommand;
import com.optivem.results.Result;

public class ShopDsl {
    private final ShopDriver driver;

    public ShopDsl(ShopDriver driver) {
        this.driver = driver;
    }

    public Result<Void> goToShop() {
        return driver.goToShop();
    }

    public PlaceOrderCommand placeOrder() {
        return new PlaceOrderCommand(driver);
    }

    public Result<Void> cancelOrder(String orderNumber) {
        return driver.cancelOrder(orderNumber);
    }


    public Result<GetOrderResponse> viewOrder(String orderNumber) {
        return driver.viewOrder(orderNumber);
    }


}
