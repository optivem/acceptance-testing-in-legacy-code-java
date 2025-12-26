package com.optivem.eshop.systemtest.core.gherkin.then;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.ScenarioDsl;
import com.optivem.eshop.systemtest.core.shop.dsl.commands.base.ErrorFailureVerification;
import com.optivem.eshop.systemtest.core.shop.dsl.commands.base.ShopUseCaseResult;

public class FailureVerificationBuilder {
    private final SystemDsl app;
    private final ScenarioDsl scenario;
    private final String orderNumber;
    private final ErrorFailureVerification failureVerification;

    public FailureVerificationBuilder(SystemDsl app, ScenarioDsl scenario, String orderNumber, ShopUseCaseResult<?, ?> result) {
        if (result == null) {
            throw new IllegalStateException("Cannot verify failure: no operation was executed");
        }
        this.app = app;
        this.scenario = scenario;
        this.orderNumber = orderNumber;
        this.failureVerification = result.shouldFail();
    }

    public FailureVerificationBuilder errorMessage(String expectedMessage) {
        failureVerification.errorMessage(expectedMessage);
        return this;
    }

    public FailureVerificationBuilder fieldErrorMessage(String expectedField, String expectedMessage) {
        failureVerification.fieldErrorMessage(expectedField, expectedMessage);
        return this;
    }

    public ThenClause and() {
        return new ThenClause(app, scenario, orderNumber);
    }
}
