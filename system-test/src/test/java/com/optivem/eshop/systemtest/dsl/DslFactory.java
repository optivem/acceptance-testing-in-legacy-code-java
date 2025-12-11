package com.optivem.eshop.systemtest.dsl;

import com.optivem.testing.dsl.Context;
import com.optivem.eshop.systemtest.dsl.external.erp.ErpDsl;
import com.optivem.eshop.systemtest.dsl.shop.ShopDsl;
import com.optivem.eshop.systemtest.dsl.external.tax.TaxDsl;

public class DslFactory {
    private final Context context;

    public DslFactory(Context context) {
        this.context = context;
    }

    public DslFactory() {
        this(new Context());
    }

    public ShopDsl createShopDsl() {
        return new ShopDsl(context);
    }

    public ErpDsl createErpDsl() {
        return new ErpDsl(context);
    }

    public TaxDsl createTaxDsl() {
        return new TaxDsl(context);
    }
}
