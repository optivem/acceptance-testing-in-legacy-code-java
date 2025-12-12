package com.optivem.eshop.systemtest.smoketests.external;

import com.optivem.eshop.systemtest.AppDslFactory;
import com.optivem.eshop.systemtest.core.AppDsl;
import com.optivem.lang.Closer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErpSmokeTest {

    private AppDsl app;

    @BeforeEach
    void setUp() {
        app = AppDslFactory.create();
    }

    @AfterEach
    void tearDown() {
        Closer.close(app);
    }

    @Test
    void shouldBeAbleToGoToErp() {
        app.erp().goToErp()
                .execute()
                .shouldSucceed();
    }
}

