package com.optivem.eshop.systemtest.core.drivers.external.erp.api.client.controllers;

import com.optivem.eshop.systemtest.core.drivers.commons.clients.TestHttpClient;
import com.optivem.eshop.systemtest.core.drivers.commons.clients.TestHttpUtils;
import com.optivem.eshop.systemtest.core.drivers.external.erp.api.client.dtos.CreateProductRequest;
import com.optivem.eshop.systemtest.core.drivers.commons.Result;

public class ProductController {

    private static final String ENDPOINT = "/api/products";

    private final TestHttpClient httpClient;

    public ProductController(TestHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Result<Void> createProduct(String sku, String price) {
        var product = new CreateProductRequest();
        product.setId(sku);
        product.setTitle("Test product title for " + sku);
        product.setDescription("Test product description for " + sku);
        product.setPrice(price);
        product.setCategory("Test Category");
        product.setBrand("Test Brand");

        var httpResponse = httpClient.post(ENDPOINT, product);

        return TestHttpUtils.getCreatedResultOrFailure(httpResponse);
    }
}
