package com.optivem.eshop.systemtest.core.gherkin.given;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.ScenarioDsl;
import com.optivem.eshop.systemtest.core.gherkin.when.WhenClause;

public class OrderBuilder {
    private final GivenClause givenClause;
    private String orderNumber;
    private String sku;
    private int quantity = 1;
    private String country = "US";
    private boolean isCancelled;

    public OrderBuilder(GivenClause givenClause) {
        this.givenClause = givenClause;
        this.isCancelled = false;
    }

    public OrderBuilder withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public OrderBuilder withSku(String sku) {
        this.sku = sku;
        return this;
    }

    public OrderBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderBuilder withCountry(String country) {
        this.country = country;
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
        app.shop().placeOrder()
                .orderNumber(this.orderNumber)
                .sku(this.sku)
                .quantity(this.quantity)
                .country(this.country)
                .execute()
                .shouldSucceed();

        if(isCancelled) {
            app.shop().cancelOrder()
                    .orderNumber(this.orderNumber)
                    .execute()
                    .shouldSucceed();
        }
    }

    String getCountry() {
        return country;
    }
}
