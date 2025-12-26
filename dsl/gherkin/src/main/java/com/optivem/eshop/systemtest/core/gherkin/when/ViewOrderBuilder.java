package com.optivem.eshop.systemtest.core.gherkin.when;

import static com.optivem.eshop.systemtest.core.gherkin.GherkinDefaults.DEFAULT_ORDER_NUMBER;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.ScenarioDsl;
import com.optivem.eshop.systemtest.core.gherkin.then.ThenClause;

public class ViewOrderBuilder {
    private final SystemDsl app;
    private final ScenarioDsl scenario;

    private String orderNumber;

    public ViewOrderBuilder(SystemDsl app, ScenarioDsl scenario) {
        this.app = app;
        this.scenario = scenario;
        
        withOrderNumber(DEFAULT_ORDER_NUMBER);
    }

    public ViewOrderBuilder withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public ThenClause then() {
        var result = app.shop().viewOrder()
                .orderNumber(orderNumber)
                .execute();

        return new ThenClause(app, scenario, orderNumber, result);
    }
}
