package com.optivem.eshop.systemtest.core.dsl.erp;

import com.optivem.eshop.systemtest.core.drivers.external.erp.api.ErpApiDriver;
import com.optivem.eshop.systemtest.core.dsl.commons.context.Context;
import com.optivem.eshop.systemtest.core.dsl.erp.commands.CreateProduct;
import com.optivem.eshop.systemtest.core.dsl.erp.commands.GoToErp;

public class ErpDsl {
    private final ErpApiDriver driver;
    private final Context context;

    public ErpDsl(ErpApiDriver driver, Context context) {
        this.driver = driver;
        this.context = context;
    }

    public GoToErp goToErp() {
        return new GoToErp(driver, context);
    }

    public CreateProduct createProduct() {
        return new CreateProduct(driver, context);
    }
}

