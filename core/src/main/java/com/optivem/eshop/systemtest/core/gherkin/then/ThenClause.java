package com.optivem.eshop.systemtest.core.gherkin.then;

import com.optivem.eshop.systemtest.core.SystemDsl;

public class ThenClause {
    private final SystemDsl app;
    private final String orderNumber;

    public ThenClause(SystemDsl app, String orderNumber) {
        this.app = app;
        this.orderNumber = orderNumber;
    }

    public SuccessVerificationBuilder shouldSucceed() {
        return new SuccessVerificationBuilder(app, orderNumber);
    }
}
