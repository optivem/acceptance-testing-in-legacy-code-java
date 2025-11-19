package com.optivem.eshop.systemtest.smoketests;

import com.optivem.eshop.systemtest.core.drivers.DriverCloser;
import com.optivem.eshop.systemtest.core.drivers.DriverFactory;
import com.optivem.eshop.systemtest.core.drivers.system.ShopApiDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApiSmokeTest {

    private ShopApiDriver shopApiDriver;

    @BeforeEach
    void setUp() {
        this.shopApiDriver = DriverFactory.createShopApiDriver();
    }

    @AfterEach
    void tearDown() {
        DriverCloser.close(shopApiDriver);
    }

    @Test
    void echo_shouldReturn200OK() {
        shopApiDriver.assertEchoSuccessful();
    }
}
