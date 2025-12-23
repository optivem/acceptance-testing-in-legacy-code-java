package com.optivem.eshop.systemtest.core.tax.driver.real;

import com.optivem.eshop.systemtest.core.tax.commons.TaxError;
import com.optivem.eshop.systemtest.core.tax.driver.TaxDriver;
import com.optivem.eshop.systemtest.core.tax.driver.real.client.TaxRealClient;
import com.optivem.eshop.systemtest.core.tax.driver.client.commons.TaxHttpClient;
import com.optivem.lang.Closer;
import com.optivem.lang.Result;

import java.net.http.HttpClient;

public class TaxRealDriver implements TaxDriver {

    private final HttpClient httpClient;
    private final TaxRealClient taxClient;

    public TaxRealDriver(String baseUrl) {
        this.httpClient = HttpClient.newHttpClient();
        var taxHttpClient = new TaxHttpClient(httpClient, baseUrl);
        this.taxClient = new TaxRealClient(taxHttpClient);
    }

    @Override
    public void close() {
        Closer.close(httpClient);
    }

    @Override
    public Result<Void, TaxError> goToTax() {
        return taxClient.health().checkHealth();
    }
}

