package com.optivem.eshop.systemtest.smoketests.v5;

import com.optivem.eshop.systemtest.base.BaseSystemTest;
import org.junit.jupiter.api.Test;

public class ClockSmokeTest extends BaseSystemTest {

    @Test
    void shouldBeAbleToGoToClock() {
        app.clock().goToClock()
                .execute()
                .shouldSucceed();
    }
}

