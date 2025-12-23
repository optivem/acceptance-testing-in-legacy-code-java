package com.optivem.eshop.systemtest.core.erp.dsl;

import com.optivem.eshop.systemtest.core.erp.driver.ErpDriver;
import com.optivem.testing.dsl.UseCaseContext;
import com.optivem.eshop.systemtest.core.erp.dsl.commands.GetProduct;
import com.optivem.eshop.systemtest.core.erp.dsl.commands.ReturnsProduct;
import com.optivem.eshop.systemtest.core.erp.dsl.commands.GoToErp;
import com.optivem.lang.Closer;

import java.io.Closeable;

public abstract class ErpDsl implements Closeable {
    protected final ErpDriver driver;
    protected final UseCaseContext context;

    protected ErpDsl(ErpDriver driver, UseCaseContext context) {
        this.driver = driver;
        this.context = context;
    }

    @Override
    public void close() {
        Closer.close(driver);
    }

    public GoToErp goToErp() {
        return new GoToErp(driver, context);
    }

    public ReturnsProduct returnsProduct() {
        return new ReturnsProduct(driver, context);
    }

    public GetProduct getProduct() {
        return new GetProduct(driver, context);
    }
}

