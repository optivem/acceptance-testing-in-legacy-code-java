package com.optivem.eshop.systemtest.smoketests.v5;

import com.optivem.eshop.systemtest.base.BaseSystemTest;
import org.junit.jupiter.api.Test;

public class ErpSmokeTest extends BaseSystemTest {

    @Test
    void shouldBeAbleToGoToErp() {
        app.erp().goToErp()
                .execute()
                .shouldSucceed();
    }
}

