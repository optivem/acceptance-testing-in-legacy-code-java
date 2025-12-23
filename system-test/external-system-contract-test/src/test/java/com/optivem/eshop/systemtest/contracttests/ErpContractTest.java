package com.optivem.eshop.systemtest.contracttests;

import com.optivem.eshop.systemtest.BaseSystemTest;
import org.junit.jupiter.api.Test;

public class ErpContractTest extends BaseSystemTest {

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
