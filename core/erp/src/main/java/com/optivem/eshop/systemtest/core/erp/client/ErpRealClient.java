package com.optivem.eshop.systemtest.core.erp.client;

import com.optivem.eshop.systemtest.core.commons.error.Error;
import com.optivem.eshop.systemtest.core.commons.error.ProblemDetailConverter;
import com.optivem.eshop.systemtest.core.erp.client.dtos.ExtProductDetailsRequest;
import com.optivem.lang.Result;

/**
 * Real ERP client for making actual HTTP calls to the ERP API.
 */
public class ErpRealClient extends BaseErpClient {

    public ErpRealClient(String baseUrl) {
        super(baseUrl);
    }

    public Result<Void, Error> createProduct(ExtProductDetailsRequest request) {
        return httpClient.post("/api/products", request)
                .mapError(ProblemDetailConverter::toError);
    }
}

