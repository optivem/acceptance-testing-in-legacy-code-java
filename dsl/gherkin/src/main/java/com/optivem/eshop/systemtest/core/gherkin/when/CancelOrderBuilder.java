package com.optivem.eshop.systemtest.core.gherkin.when;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.ScenarioDsl;
import com.optivem.eshop.systemtest.core.gherkin.then.ThenClause;

import static com.optivem.eshop.systemtest.core.gherkin.GherkinDefaults.DEFAULT_ORDER_NUMBER;

public class CancelOrderBuilder {
    private final SystemDsl app;
    private final ScenarioDsl scenario;
    private String orderNumber;

    public CancelOrderBuilder(SystemDsl app, ScenarioDsl scenario) {
        this.app = app;
        this.scenario = scenario;
        withOrderNumber(DEFAULT_ORDER_NUMBER);
    }

    public CancelOrderBuilder withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public ThenClause then() {
        // Execute the cancel order
        var result = app.shop().cancelOrder()
                .orderNumber(orderNumber)
                .execute();

        return new ThenClause(app, scenario, orderNumber, result);
    }
}
