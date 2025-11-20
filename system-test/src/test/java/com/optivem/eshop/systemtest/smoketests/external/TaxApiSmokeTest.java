package com.optivem.eshop.systemtest.smoketests.external;

import com.optivem.eshop.systemtest.core.drivers.DriverCloser;
import com.optivem.eshop.systemtest.core.drivers.DriverFactory;
import com.optivem.eshop.systemtest.core.drivers.external.TaxApiDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaxApiSmokeTest {

    private TaxApiDriver taxApiDriver;

    @BeforeEach
    void setUp() {
        var driverFactory = new DriverFactory();
        this.taxApiDriver = driverFactory.createTaxApiDriver();
    }

    @AfterEach
    void tearDown() {
        DriverCloser.close(taxApiDriver);
    }

    @Test
    void home_shouldReturn200OK() {
        taxApiDriver.goToTaxation();
    }
}

