package com.optivem.eshop.systemtest.core.gherkin.when;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.ScenarioDsl;

public class WhenClause {
    private final SystemDsl app;
    private final ScenarioDsl scenario;

    public WhenClause(SystemDsl app, ScenarioDsl scenario) {
        this.app = app;
        this.scenario = scenario;
    }

    public PlaceOrderBuilder placeOrder() {
        return new PlaceOrderBuilder(app, scenario);
    }

    public CancelOrderBuilder cancelOrder() {
        return new CancelOrderBuilder(app, scenario);
    }

    public ViewOrderBuilder viewOrder() {
        return new ViewOrderBuilder(app, scenario);
    }
}
