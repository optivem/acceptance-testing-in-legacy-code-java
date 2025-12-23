package com.optivem.eshop.systemtest.contracttests.erp;

import com.optivem.eshop.systemtest.BaseSystemTest;
import com.optivem.eshop.systemtest.contracttests.base.BaseExternalSystemContractTest;
import org.junit.jupiter.api.Test;

public abstract class BaseErpContractTest extends BaseExternalSystemContractTest {

    @Test
    void shouldBeAbleToGetProduct() {
        app.erp().returnsProduct()
                .sku("SKU-123")
                .unitPrice(12.0)
                .execute()
                .shouldSucceed();

        app.erp().getProduct()
                .sku("SKU-123")
                .execute()
                .shouldSucceed()
                .sku("SKU-123")
                .price(12.0);
    }
}
