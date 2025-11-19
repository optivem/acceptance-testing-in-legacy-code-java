package com.optivem.eshop.systemtest.core.drivers.external;

import com.optivem.eshop.systemtest.core.clients.external.tax.TaxApiClient;

public class TaxApiDriver implements AutoCloseable {

    private final TaxApiClient taxApiClient;

    public TaxApiDriver(TaxApiClient taxApiClient) {
        this.taxApiClient = taxApiClient;
    }

    public void assertHomeSuccessful() {
        var httpResponse = taxApiClient.home().home();
        taxApiClient.home().assertHomeSuccessful(httpResponse);
    }

    @Override
    public void close() {
        if (taxApiClient != null) {
            taxApiClient.close();
        }
    }
}

