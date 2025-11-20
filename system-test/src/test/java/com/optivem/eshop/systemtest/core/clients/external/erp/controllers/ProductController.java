package com.optivem.eshop.systemtest.core.clients.external.erp.controllers;

import com.optivem.eshop.systemtest.core.clients.commons.TestHttpClient;
import com.optivem.eshop.systemtest.core.clients.external.erp.dtos.CreateProductRequest;

import java.math.BigDecimal;
import java.net.http.HttpResponse;

public class ProductController {

    private static final String ENDPOINT = "/products";

    private final TestHttpClient httpClient;

    public ProductController(TestHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void createProduct(String sku, String price) {
        var product = new CreateProductRequest();
        product.setId(sku);
        product.setTitle("Test product title for " + sku);
        product.setDescription("Test product description for " + sku);
        product.setPrice(price);
        product.setCategory("Test Category");
        product.setBrand("Test Brand");

        var httpResponse = httpClient.post(ENDPOINT, product);
        httpClient.assertCreated(httpResponse);
    }
}
