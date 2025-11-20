package com.optivem.eshop.systemtest.core.drivers.external;

import com.optivem.eshop.systemtest.core.context.Context;
import com.optivem.eshop.systemtest.core.clients.external.erp.ErpApiClient;

public class ErpApiDriver implements AutoCloseable {

    private final ErpApiClient erpApiClient;
    private final Context context;

    public ErpApiDriver(String baseUrl, Context context) {
        this.erpApiClient = new ErpApiClient(baseUrl);
        this.context = context;
    }

    public void toToErp() {
        var httpResponse = erpApiClient.home().home();
        erpApiClient.home().assertHomeSuccessful(httpResponse);
    }

    public void createProduct(String skuAlias, String unitPrice) {
        var skuValue = context.params().alias(skuAlias);
        erpApiClient.products().createProduct(skuValue, unitPrice);
        // TODO: VJ: Assert successful creation
    }

    @Override
    public void close() {
        erpApiClient.close();
    }
}

