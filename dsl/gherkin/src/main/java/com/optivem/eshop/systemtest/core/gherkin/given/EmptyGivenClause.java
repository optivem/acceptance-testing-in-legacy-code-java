package com.optivem.eshop.systemtest.core.gherkin.given;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.ScenarioDsl;
import com.optivem.eshop.systemtest.core.gherkin.when.WhenClause;

public class EmptyGivenClause {
    private final SystemDsl app;
    private final ScenarioDsl scenario;

    public EmptyGivenClause(SystemDsl app, ScenarioDsl scenario) {
        this.app = app;
        this.scenario = scenario;
    }

    public WhenClause when() {
        return new WhenClause(app, scenario);
    }
}
