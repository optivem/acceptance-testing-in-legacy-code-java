package com.optivem.eshop.monolith.core.services.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.optivem.eshop.monolith.core.dtos.external.ProductDetailsResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class ErpGateway {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    @Value("${erp.url}")
    private String erpUrl;

    /**
     * Get product details from ERP system
     * @param sku the product SKU
     * @return ProductDetailsResponse if product exists, null if not found (404)
     * @throws RuntimeException for other errors (network, timeout, etc.)
     */
    public ProductDetailsResponse getProductDetails(String sku) {
        try {
            var httpClient = HttpClient.newBuilder()
                    .connectTimeout(java.time.Duration.ofSeconds(10))
                    .build();
                    
            var url = erpUrl + "/products/" + sku;
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(java.time.Duration.ofSeconds(10))
                    .GET()
                    .build();
                    
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                return null;  // Product not found
            }

            if (response.statusCode() != 200) {
                throw new RuntimeException("ERP API returned status " + response.statusCode() + 
                        " for SKU: " + sku + ". URL: " + url + ". Response: " + response.body());
            }

            return OBJECT_MAPPER.readValue(response.body(), ProductDetailsResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch product details for SKU: " + sku +
                    " from URL: " + erpUrl + "/products/" + sku +
                    ". Error: " + e.getClass().getSimpleName() + ": " + e.getMessage(), e);
        }
    }

    /**
     * Get unit price for a product
     * @deprecated Use getProductDetails instead for better error handling
     */
    @Deprecated
    public BigDecimal getUnitPrice(String sku) {
        var productDetails = getProductDetails(sku);
        if (productDetails == null) {
            throw new RuntimeException("Product not found for SKU: " + sku);
        }
        return productDetails.getPrice();
    }
}
