package com.optivem.eshop.systemtest.core.gherkin.given;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.ScenarioDsl;
import com.optivem.eshop.systemtest.core.gherkin.when.WhenClause;
import com.optivem.eshop.systemtest.core.shop.dsl.commands.PlaceOrder;

public class OrderBuilder {
    private final GivenClause givenClause;
    private final SystemDsl app;
    private final PlaceOrder placeOrder;
    private String orderNumber;
    private String country; // Keep for cross-cutting logic in GivenClause

    public OrderBuilder(GivenClause givenClause, SystemDsl app) {
        this.givenClause = givenClause;
        this.app = app;
        this.placeOrder = app.shop().placeOrder();
        // placeOrder has default country "US"
    }

    public OrderBuilder withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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
        this.country = country;
        placeOrder.country(country);
        return this;
    }

    public GivenClause and() {
        return givenClause;
    }

    public WhenClause when() {
        return givenClause.when();
    }

    void execute(SystemDsl app) {
        placeOrder.orderNumber(orderNumber)
                .execute()
                .shouldSucceed();
    }

    void executeAndCancel(SystemDsl app) {
        execute(app);
        app.shop().cancelOrder()
                .orderNumber(this.orderNumber)
                .execute()
                .shouldSucceed();
    }

    String getCountry() {
        // If country was explicitly set, return it; otherwise return the default from PlaceOrder
        return country != null ? country : "US";
    }
}
