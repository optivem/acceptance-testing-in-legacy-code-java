package com.optivem.eshop.systemtest.core.drivers;

import com.optivem.eshop.systemtest.TestConfiguration;
import com.optivem.eshop.systemtest.core.clients.ClientFactory;
import com.optivem.eshop.systemtest.core.drivers.external.ErpApiDriver;
import com.optivem.eshop.systemtest.core.drivers.external.TaxApiDriver;
import com.optivem.eshop.systemtest.core.drivers.system.ShopApiDriver;
import com.optivem.eshop.systemtest.core.drivers.system.ShopUiDriver;

public class DriverFactory {

    public static ShopUiDriver createShopUiDriver() {
        return new ShopUiDriver(TestConfiguration.getShopUiBaseUrl());
    }

    public static ShopApiDriver createShopApiDriver() {
        return new ShopApiDriver(TestConfiguration.getShopApiBaseUrl());
    }

    public static ErpApiDriver createErpApiDriver() {
        var client = ClientFactory.createErpApiClient();
        return new ErpApiDriver(client);
    }

    public static TaxApiDriver createTaxApiDriver() {
        var client = ClientFactory.createTaxApiClient();
        return new TaxApiDriver(client);
    }
}

