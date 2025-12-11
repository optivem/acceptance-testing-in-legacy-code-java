package com.optivem.eshop.systemtest.dsl.external.tax.commands.base;

import com.optivem.eshop.systemtest.dsl.external.tax.driver.TaxApiDriver;
import com.optivem.testing.dsl.BaseCommand;
import com.optivem.testing.dsl.Context;

public abstract class BaseTaxCommand<TResponse, TVerification> extends BaseCommand<TaxApiDriver, TResponse, TVerification> {
    protected BaseTaxCommand(TaxApiDriver driver, Context context) {
        super(driver, context);
    }
}

