package com.optivem.eshop.systemtest.dsl.external.tax;

import com.optivem.eshop.systemtest.dsl.DriverFactory;
import com.optivem.eshop.systemtest.dsl.external.tax.driver.TaxApiDriver;
import com.optivem.testing.dsl.Context;
import com.optivem.eshop.systemtest.dsl.external.tax.commands.GoToTax;
import com.optivem.lang.Closer;

import java.io.Closeable;

public class TaxDsl implements Closeable {
    private final TaxApiDriver driver;
    private final Context context;

    public TaxDsl(Context context) {
        this.driver = DriverFactory.createTaxApiDriver();
        this.context = context;
    }

    @Override
    public void close() {
        Closer.close(driver);
    }

    public GoToTax goToTax() {
        return new GoToTax(driver, context);
    }
}

