package com.optivem.eshop.systemtest.core.gherkin.given;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.when.WhenClause;
import com.optivem.eshop.systemtest.core.tax.dsl.commands.ReturnsTaxRate;

public class TaxRateBuilder {
    private final GivenClause givenClause;
    private final ReturnsTaxRate returnsTaxRate;

    public TaxRateBuilder(GivenClause givenClause, SystemDsl app) {
        this.givenClause = givenClause;
        this.returnsTaxRate = app.tax().returnsTaxRate();
    }

    public TaxRateBuilder withCountry(String country) {
        returnsTaxRate.country(country);
        return this;
    }

    public TaxRateBuilder withTaxRate(double taxRate) {
        returnsTaxRate.taxRate(taxRate);
        return this;
    }

    public GivenClause and() {
        return givenClause;
    }

    public WhenClause when() {
        return givenClause.when();
    }

    void execute(SystemDsl app) {
        returnsTaxRate.execute().shouldSucceed();
    }
}

