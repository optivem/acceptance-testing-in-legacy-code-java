package com.optivem.eshop.systemtest.dsl.external.erp;

import com.optivem.eshop.systemtest.dsl.DriverFactory;
import com.optivem.eshop.systemtest.dsl.external.erp.driver.ErpApiDriver;
import com.optivem.testing.dsl.Context;
import com.optivem.eshop.systemtest.dsl.external.erp.commands.CreateProduct;
import com.optivem.eshop.systemtest.dsl.external.erp.commands.GoToErp;
import com.optivem.lang.Closer;

import java.io.Closeable;

public class ErpDsl implements Closeable {
    private final ErpApiDriver driver;
    private final Context context;

    public ErpDsl(Context context) {
        this.driver = DriverFactory.createErpApiDriver();
        this.context = context;
    }

    @Override
    public void close() {
        Closer.close(driver);
    }

    public GoToErp goToErp() {
        return new GoToErp(driver, context);
    }

    public CreateProduct createProduct() {
        return new CreateProduct(driver, context);
    }
}

