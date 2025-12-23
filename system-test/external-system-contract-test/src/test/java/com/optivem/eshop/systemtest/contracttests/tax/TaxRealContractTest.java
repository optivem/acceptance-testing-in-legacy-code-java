package com.optivem.eshop.systemtest.contracttests.tax;

import com.optivem.testing.dsl.ExternalSystemMode;

import java.util.Optional;

public class TaxRealContractTest extends BaseTaxContractTest {
    @Override
    protected ExternalSystemMode getFixedExternalSystemMode() {
        return ExternalSystemMode.REAL;
    }
}
