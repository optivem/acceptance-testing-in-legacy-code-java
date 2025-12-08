package com.optivem.eshop.systemtest.core.dsl.tax.commands.base;

import com.optivem.eshop.systemtest.core.drivers.external.tax.api.TaxApiDriver;
import com.optivem.eshop.systemtest.core.dsl.commons.commands.base.BaseCommand;
import com.optivem.eshop.systemtest.core.dsl.commons.context.Context;

public abstract class BaseTaxCommand<TResponse, TVerification> extends BaseCommand<TaxApiDriver, TResponse, TVerification> {
    protected BaseTaxCommand(TaxApiDriver driver, Context context) {
        super(driver, context);
    }
}

