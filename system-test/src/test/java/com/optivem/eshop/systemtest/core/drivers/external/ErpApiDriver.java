package com.optivem.eshop.systemtest.core.drivers.external;

import com.optivem.eshop.systemtest.core.clients.external.erp.ErpApiClient;

public class ErpApiDriver implements AutoCloseable {

    private final ErpApiClient erpApiClient;

    public ErpApiDriver(String baseUrl) {
        this.erpApiClient = new ErpApiClient(baseUrl);
    }

    public void toToErp() {
        var httpResponse = erpApiClient.home().home();
        erpApiClient.home().assertHomeSuccessful(httpResponse);
    }

    public void createProduct(String sku, String unitPrice) {
        erpApiClient.products().createProduct(sku, unitPrice);
        // TODO: VJ: Assert successful creation
    }

    @Override
    public void close() {
        erpApiClient.close();
    }
}

