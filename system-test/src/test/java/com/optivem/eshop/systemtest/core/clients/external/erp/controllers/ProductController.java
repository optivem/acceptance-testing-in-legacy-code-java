package com.optivem.eshop.systemtest.core.clients.external.erp.controllers;

import com.optivem.eshop.systemtest.core.clients.commons.BaseController;
import com.optivem.eshop.systemtest.core.clients.external.erp.dtos.ErpProduct;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductController extends BaseController {
    public ProductController(HttpClient httpClient, String baseUrl) {
        super(httpClient, baseUrl, "products");
    }

    public String create(String baseSku, String title, BigDecimal price) {
        try {
            // Add UUID suffix to avoid duplicate IDs across test runs
            String uniqueSku = baseSku + "-" + java.util.UUID.randomUUID().toString().substring(0, 8);

            var product = new ErpProduct();
            product.setId(uniqueSku);
            product.setTitle(title);
            product.setDescription("Test product for " + uniqueSku);
            product.setPrice(price);
            product.setCategory("Test Category");
            product.setBrand("Test Brand");

            var response = post(product);

            assertEquals(HttpStatus.CREATED.value(), response.statusCode(), "ERP product setup should succeed. Status: " + response.statusCode() + ", Body: " + response.body());

            return uniqueSku;
        } catch (Exception e) {
            throw new RuntimeException("Failed to set up ERP product", e);
        }
    }
}
