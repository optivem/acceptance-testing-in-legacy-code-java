package com.optivem.eshop.systemtest.core.erp.client.stub;

import com.optivem.eshop.systemtest.core.erp.client.common.BaseErpClient;
import com.optivem.eshop.systemtest.core.erp.client.common.controllers.ProductController;

/**
 * Stub ERP client for making HTTP calls to the WireMock stub.
 */
public class ErpStubClient extends BaseErpClient<ProductController> {

    public ErpStubClient(String baseUrl) {
        super(baseUrl, ProductController::new);
    }
}

