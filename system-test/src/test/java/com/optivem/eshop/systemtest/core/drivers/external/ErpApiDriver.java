package com.optivem.eshop.systemtest.core.drivers.external;

import com.optivem.eshop.systemtest.core.clients.external.erp.ErpApiClient;

public class ErpApiDriver implements AutoCloseable {

    private final ErpApiClient erpApiClient;

    public ErpApiDriver(ErpApiClient erpApiClient) {
        this.erpApiClient = erpApiClient;
    }

    public void toToErp() {
        var httpResponse = erpApiClient.home().home();
        erpApiClient.home().assertHomeSuccessful(httpResponse);
    }

    public String createProduct(String baseSku, String unitPrice) {
        return erpApiClient.products().createProduct(baseSku, unitPrice);
    }

    @Override
    public void close() {
        if (erpApiClient != null) {
            erpApiClient.close();
        }
    }
}

