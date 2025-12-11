package com.optivem.eshop.systemtest.dsl;

import com.optivem.testing.dsl.Context;
import com.optivem.eshop.systemtest.dsl.external.erp.ErpDsl;
import com.optivem.eshop.systemtest.dsl.shop.ShopDsl;
import com.optivem.eshop.systemtest.dsl.external.tax.TaxDsl;
import com.optivem.lang.Closer;

import java.io.Closeable;

public class Dsl implements Closeable {
    private final Context context;
    private ShopDsl shop;
    private ErpDsl erp;
    private TaxDsl tax;

    public Dsl() {
        this.context = new Context();
    }

    public ShopDsl shop() {
        if (shop == null) {
            shop = new ShopDsl(context);
        }
        return shop;
    }

    public ErpDsl erp() {
        if (erp == null) {
            erp = new ErpDsl(context);
        }
        return erp;
    }

    public TaxDsl tax() {
        if (tax == null) {
            tax = new TaxDsl(context);
        }
        return tax;
    }

    @Override
    public void close() {
        Closer.close(shop);
        Closer.close(erp);
        Closer.close(tax);
    }
}

