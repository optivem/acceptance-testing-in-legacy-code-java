package com.optivem.eshop.systemtest.smoketests.external;

import com.optivem.commons.utils.Closer;
import com.optivem.eshop.systemtest.core.drivers.DriverFactory;
import com.optivem.eshop.systemtest.core.drivers.external.erp.api.ErpApiDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.commons.assertions.ResultAssert.assertThatResult;

public class ErpApiSmokeTest {

    private ErpApiDriver erpApiDriver;

    @BeforeEach
    void setUp() {
        this.erpApiDriver = DriverFactory.createErpApiDriver();
    }

    @AfterEach
    void tearDown() {
        Closer.close(erpApiDriver);
    }

    @Test
    void shouldBeAbleToGoToErp() {
        var result = erpApiDriver.goToErp();
        assertThatResult(result).isSuccess();
    }
}

