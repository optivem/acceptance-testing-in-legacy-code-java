package com.optivem.eshop.systemtest.core.drivers;

import com.optivem.eshop.systemtest.TestConfiguration;
import com.optivem.eshop.systemtest.core.context.Context;
import com.optivem.eshop.systemtest.core.clients.ClientFactory;
import com.optivem.eshop.systemtest.core.drivers.external.ErpApiDriver;
import com.optivem.eshop.systemtest.core.drivers.external.TaxApiDriver;
import com.optivem.eshop.systemtest.core.drivers.system.ShopApiDriver;
import com.optivem.eshop.systemtest.core.drivers.system.ShopUiDriver;

public class DriverFactory {

    private final Context context;

    public DriverFactory(Context context) {
        this.context = context;
    }

    public DriverFactory() {
        this(new Context());
    }

    public ShopUiDriver createShopUiDriver() {
        return new ShopUiDriver(TestConfiguration.getShopUiBaseUrl(), context);
    }

    public ShopApiDriver createShopApiDriver() {
        return new ShopApiDriver(TestConfiguration.getShopApiBaseUrl(), context);
    }

    public ErpApiDriver createErpApiDriver() {
        return new ErpApiDriver(TestConfiguration.getErpApiBaseUrl(), context);
    }

    public TaxApiDriver createTaxApiDriver() {
        var client = ClientFactory.createTaxApiClient();
        return new TaxApiDriver(client);
        // TODO: VJ: Make consistent with the above
    }
}

