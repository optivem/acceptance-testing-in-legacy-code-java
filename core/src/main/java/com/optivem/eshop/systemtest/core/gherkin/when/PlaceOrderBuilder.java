package com.optivem.eshop.systemtest.core.gherkin.when;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.then.ThenClause;

public class PlaceOrderBuilder {
    private final SystemDsl app;
    private String orderNumber;
    private String sku;
    private int quantity;

    public PlaceOrderBuilder(SystemDsl app) {
        this.app = app;
    }

    public PlaceOrderBuilder withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public PlaceOrderBuilder withSku(String sku) {
        this.sku = sku;
        return this;
    }

    public PlaceOrderBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ThenClause then() {
        // Execute the place order
        app.shop().placeOrder()
                .orderNumber(orderNumber)
                .sku(sku)
                .quantity(quantity)
                .execute();

        return new ThenClause(app, orderNumber);
    }
}
