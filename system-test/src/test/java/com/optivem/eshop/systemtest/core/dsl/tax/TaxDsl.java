package com.optivem.eshop.systemtest.core.dsl.tax;

import com.optivem.eshop.systemtest.core.drivers.external.tax.api.TaxApiDriver;
import com.optivem.eshop.systemtest.core.dsl.commons.context.Context;
import com.optivem.eshop.systemtest.core.dsl.tax.commands.GoToTax;

public class TaxDsl {
    private final TaxApiDriver driver;
    private final Context context;

    public TaxDsl(TaxApiDriver driver, Context context) {
        this.driver = driver;
        this.context = context;
    }

    public GoToTax goToTax() {
        return new GoToTax(driver, context);
    }
}

