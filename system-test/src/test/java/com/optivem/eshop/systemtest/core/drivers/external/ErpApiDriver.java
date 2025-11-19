package com.optivem.eshop.systemtest.core.drivers.external;

import com.optivem.eshop.systemtest.core.clients.external.erp.ErpApiClient;

import java.math.BigDecimal;

public class ErpApiDriver implements AutoCloseable {

    private final ErpApiClient erpApiClient;

    public ErpApiDriver(ErpApiClient erpApiClient) {
        this.erpApiClient = erpApiClient;
    }

    public String createProduct(String baseSku, BigDecimal unitPrice) {
        return erpApiClient.products().createProduct(baseSku, unitPrice);
    }

    @Override
    public void close() {
        if (erpApiClient != null) {
            erpApiClient.close();
        }
    }
}

