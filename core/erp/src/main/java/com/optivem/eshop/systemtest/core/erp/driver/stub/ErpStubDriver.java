package com.optivem.eshop.systemtest.core.erp.driver.stub;

import com.optivem.eshop.systemtest.core.commons.error.Error;
import com.optivem.eshop.systemtest.core.erp.driver.ErpDriver;
import com.optivem.eshop.systemtest.core.erp.driver.dtos.requests.ReturnsProductRequest;
import com.optivem.lang.Result;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * ErpStubDriver uses WireMock to stub ERP API responses.
 * This allows tests to run without a real ERP system.
 */
public class ErpStubDriver implements ErpDriver {

    private final WireMock wireMock;

    public ErpStubDriver(String wireMockBaseUrl) {
        // Parse the WireMock base URL to extract host and port
        var url = java.net.URI.create(wireMockBaseUrl);
        this.wireMock = new WireMock(url.getHost(), url.getPort());
    }

    @Override
    public Result<Void, Error> goToErp() {
        // Health check is already preconfigured in WireMock
        // No need to set up a stub dynamically
        return Result.success();
    }

    @Override
    public Result<Void, Error> returnsProduct(ReturnsProductRequest request) {
        // Configure WireMock stub for creating a product in ERP
        var sku = request.getSku();
        var price = request.getPrice();

        // Create a stub that matches the product creation request
        wireMock.register(post(urlPathEqualTo("/erp/api/products"))
                .withRequestBody(matchingJsonPath("$.id", equalTo(sku)))
                .withRequestBody(matchingJsonPath("$.price", equalTo(price)))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{}")));

        // Also stub GET request for the product
        wireMock.register(get(urlPathEqualTo("/erp/api/products/" + sku))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.format(
                                "{\"id\":\"%s\",\"price\":\"%s\",\"title\":\"Test Product\",\"description\":\"Test Description\",\"category\":\"Test Category\",\"brand\":\"Test Brand\"}",
                                sku, price))));

        return Result.success();
    }

    @Override
    public void close() throws Exception {
        // Optionally reset stubs between tests
        // Uncomment if you want to clean up stubs after each test
        // if (wireMock != null) {
        //     wireMock.resetMappings();
        // }
    }
}
