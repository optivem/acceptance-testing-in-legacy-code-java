package com.optivem.eshop.systemtest.core.gherkin.given;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.when.WhenClause;

import java.util.ArrayList;
import java.util.List;

public class GivenClause {
    private final SystemDsl app;
    private final List<ProductBuilder> products = new ArrayList<>();

    public GivenClause(SystemDsl app) {
        this.app = app;
    }

    public ProductBuilder product() {
        var productBuilder = new ProductBuilder(this);
        products.add(productBuilder);
        return productBuilder;
    }

    public WhenClause when() {
        // Execute all product creations
        for (var product : products) {
            app.erp().createProduct()
                    .sku(product.getSku())
                    .unitPrice(product.getUnitPrice())
                    .execute()
                    .shouldSucceed();
        }
        return new WhenClause(app);
    }
}
