package com.optivem.eshop.systemtest.smoketests;

import com.optivem.eshop.systemtest.core.drivers.DriverCloser;
import com.optivem.eshop.systemtest.core.drivers.DriverFactory;
import com.optivem.eshop.systemtest.core.drivers.system.ShopUiDriver;
import org.junit.jupiter.api.*;

public class UiSmokeTest {

    private ShopUiDriver shopUiDriver;

    @BeforeEach
    void setUp() {
        this.shopUiDriver = DriverFactory.createShopUiDriver();
    }

    @AfterEach
    void tearDown() {
        DriverCloser.close(shopUiDriver);
    }

    @Test
    void home_shouldReturnHtmlContent() {
        shopUiDriver.assertHomePageLoaded();
    }
}
