package com.optivem.eshop.systemtest.dsl;

import com.optivem.eshop.systemtest.TestConfiguration;
import com.optivem.testing.channels.ChannelContext;
import com.optivem.eshop.systemtest.dsl.external.erp.driver.ErpApiDriver;
import com.optivem.eshop.systemtest.dsl.external.tax.driver.TaxApiDriver;
import com.optivem.eshop.systemtest.dsl.shop.driver.ShopDriver;
import com.optivem.eshop.systemtest.dsl.shop.driver.api.ShopApiDriver;
import com.optivem.eshop.systemtest.dsl.shop.driver.ui.ShopUiDriver;

public class DriverFactory {

    public static ShopDriver createShopDriver() {
        String channel = ChannelContext.get();
        if ("UI".equals(channel)) {
            return new ShopUiDriver(TestConfiguration.getShopUiBaseUrl());
        } else if ("API".equals(channel)) {
            return new ShopApiDriver(TestConfiguration.getShopApiBaseUrl());
        } else {
            throw new IllegalStateException("Unknown channel: " + channel);
        }
    }

    public static ErpApiDriver createErpApiDriver() {
        return new ErpApiDriver(TestConfiguration.getErpApiBaseUrl());
    }

    public static TaxApiDriver createTaxApiDriver() {
        return new TaxApiDriver(TestConfiguration.getTaxApiBaseUrl());
    }
}

