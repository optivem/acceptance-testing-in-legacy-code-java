package com.optivem.eshop.systemtest.core.gherkin.given;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.ScenarioDsl;
import com.optivem.eshop.systemtest.core.gherkin.when.WhenClause;
import com.optivem.eshop.systemtest.core.shop.dsl.commands.CancelOrder;
import com.optivem.eshop.systemtest.core.shop.dsl.commands.PlaceOrder;

public class OrderBuilder {
    private final GivenClause givenClause;
    private final SystemDsl app;
    private final PlaceOrder placeOrder;
    private final CancelOrder cancelOrder;
    private boolean isCancelled;

    public OrderBuilder(GivenClause givenClause, SystemDsl app) {
        this.givenClause = givenClause;
        this.app = app;
        this.placeOrder = app.shop().placeOrder();
        this.cancelOrder = app.shop().cancelOrder();
        this.isCancelled = false;
    }

    public OrderBuilder withOrderNumber(String orderNumber) {
        this.placeOrder.orderNumber(orderNumber);
        this.cancelOrder.orderNumber(orderNumber);
        return this;
    }

    public OrderBuilder withSku(String sku) {
        placeOrder.sku(sku);
        return this;
    }

    public OrderBuilder withQuantity(int quantity) {
        placeOrder.quantity(quantity);
        return this;
    }

    public OrderBuilder withCountry(String country) {
        placeOrder.country(country);
        return this;
    }

    public OrderBuilder cancelled() {
        isCancelled = true;
        return this;
    }

    public GivenClause and() {
        return givenClause;
    }

    public WhenClause when() {
        return givenClause.when();
    }

    void execute(SystemDsl app) {
        placeOrder.execute().shouldSucceed();

        if(isCancelled) {
            cancelOrder.execute().shouldSucceed();
        }
    }
}
