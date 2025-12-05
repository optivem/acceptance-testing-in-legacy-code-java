package com.optivem.eshop.systemtest.core.drivers.external.tax.api;

import com.optivem.commons.utils.Closer;
import com.optivem.commons.http.HttpGateway;
import com.optivem.eshop.systemtest.core.drivers.external.tax.api.client.TaxApiClient;
import com.optivem.commons.utils.Result;

import java.net.http.HttpClient;

public class TaxApiDriver implements AutoCloseable {

    private final HttpClient httpClient;
    private final TaxApiClient taxApiClient;

    public TaxApiDriver(String baseUrl) {
        this.httpClient = HttpClient.newHttpClient();
        var testHttpClient = new HttpGateway(httpClient, baseUrl);
        this.taxApiClient = new TaxApiClient(testHttpClient);
    }

    public Result<Void> goToTax() {
        return taxApiClient.health().checkHealth();
    }

    @Override
    public void close() {
        Closer.close(httpClient);
    }
}

