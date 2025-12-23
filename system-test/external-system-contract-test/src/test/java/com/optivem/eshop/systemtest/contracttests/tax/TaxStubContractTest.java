package com.optivem.eshop.systemtest.contracttests.tax;

import com.optivem.testing.dsl.ExternalSystemMode;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TaxStubContractTest extends BaseTaxContractTest {

    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.STUB;
    }

    @Test
    void shouldBeAbleToGetConfiguredTaxRate() {
        app.tax().returnsTaxRate()
                .country("US")
                .taxRate(0.23)
                .execute()
                .shouldSucceed();

        app.tax().getTaxRate()
                .country("US")
                .execute()
                .shouldSucceed()
                .country("US")
                .taxRate(0.23);
    }
}
