package com.optivem.eshop.systemtest.core.external.erp.commands.base;

import com.optivem.eshop.systemtest.core.external.erp.driver.ErpDriver;
import com.optivem.testing.dsl.BaseCommand;
import com.optivem.testing.dsl.Context;

public abstract class BaseErpCommand<TResponse, TVerification> extends BaseCommand<ErpDriver, TResponse, TVerification> {
    protected BaseErpCommand(ErpDriver driver, Context context) {
        super(driver, context);
    }
}

