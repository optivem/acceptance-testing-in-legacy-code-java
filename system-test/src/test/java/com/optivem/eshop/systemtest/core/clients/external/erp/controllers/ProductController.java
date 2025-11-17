package com.optivem.eshop.systemtest.core.clients.external.erp.controllers;

import com.optivem.eshop.systemtest.core.clients.commons.BaseController;
import com.optivem.eshop.systemtest.core.clients.external.erp.dtos.CreateProductRequest;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductController extends BaseController {
    public ProductController(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl, "products");
    }

    public String createProduct(String baseSku, BigDecimal price) {
        // Add UUID suffix to avoid duplicate IDs across test runs
        String uniqueSku = baseSku + "-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        var product = new CreateProductRequest();
        product.setId(uniqueSku);
        product.setTitle("Test product title for " + uniqueSku);
        product.setDescription("Test product description for " + uniqueSku);
        product.setPrice(price);
        product.setCategory("Test Category");
        product.setBrand("Test Brand");

        var httpResponse = post(product);
        assertCreated(httpResponse);
        return uniqueSku;
    }
}
