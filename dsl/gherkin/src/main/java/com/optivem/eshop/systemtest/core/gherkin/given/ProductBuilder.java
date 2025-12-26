package com.optivem.eshop.systemtest.core.gherkin.given;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.erp.dsl.commands.ReturnsProduct;
import com.optivem.eshop.systemtest.core.gherkin.when.WhenClause;

public class ProductBuilder {
    private final GivenClause givenClause;
    private final ReturnsProduct returnsProduct;

    public ProductBuilder(GivenClause givenClause, SystemDsl app) {
        this.givenClause = givenClause;
        this.returnsProduct = app.erp().returnsProduct();
    }

    public ProductBuilder withSku(String sku) {
        returnsProduct.sku(sku);
        return this;
    }

    public ProductBuilder withUnitPrice(double unitPrice) {
        returnsProduct.unitPrice(unitPrice);
        return this;
    }

    public ProductBuilder withUnitPrice(String unitPrice) {
        returnsProduct.unitPrice(unitPrice);
        return this;
    }

    public GivenClause and() {
        return givenClause;
    }

    public WhenClause when() {
        return givenClause.when();
    }

    void execute(SystemDsl app) {
        returnsProduct.execute().shouldSucceed();
    }
}
