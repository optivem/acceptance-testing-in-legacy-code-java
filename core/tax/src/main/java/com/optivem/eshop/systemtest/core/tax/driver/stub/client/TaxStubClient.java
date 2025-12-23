package com.optivem.eshop.systemtest.core.tax.driver.stub.client;

import com.optivem.eshop.systemtest.core.tax.driver.client.BaseTaxClient;
import com.optivem.eshop.systemtest.core.tax.driver.client.commons.TaxHttpClient;

public class TaxStubClient extends BaseTaxClient {

    public TaxStubClient(TaxHttpClient httpClient) {
        super(httpClient);
    }
}

