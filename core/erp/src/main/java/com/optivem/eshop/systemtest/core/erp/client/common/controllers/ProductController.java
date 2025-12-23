package com.optivem.eshop.systemtest.core.erp.client.common.controllers;

import com.optivem.eshop.systemtest.core.commons.error.Error;
import com.optivem.eshop.systemtest.core.commons.error.ProblemDetailConverter;
import com.optivem.eshop.systemtest.core.commons.error.ProblemDetailResponse;
import com.optivem.eshop.systemtest.core.commons.clients.api.controllers.BaseController;
import com.optivem.eshop.systemtest.core.erp.client.common.dtos.ExtProductDetailsRequest;
import com.optivem.eshop.systemtest.core.erp.client.common.dtos.ExtProductDetailsResponse;
import com.optivem.http.JsonHttpClient;
import com.optivem.lang.Result;

/**
 * Product controller for retrieving product details from ERP.
 * Shared between real and stub implementations.
 */
public class ProductController extends BaseController<ProblemDetailResponse> {

    private static final String ENDPOINT = "/api/products";

    public ProductController(JsonHttpClient<ProblemDetailResponse> httpClient) {
        super(httpClient);
    }

    public Result<ExtProductDetailsResponse, Error> getProduct(String sku) {
        return httpClient.get(ENDPOINT + "/" + sku, ExtProductDetailsResponse.class)
                .mapError(ProblemDetailConverter::toError);
    }
}

