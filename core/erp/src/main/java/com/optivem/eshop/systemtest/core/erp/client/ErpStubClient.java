package com.optivem.eshop.systemtest.core.erp.client;


import com.github.tomakehurst.wiremock.client.WireMock;
import com.optivem.eshop.systemtest.core.commons.error.Error;
import com.optivem.eshop.systemtest.core.erp.client.dtos.ExtProductDetailsResponse;
import com.optivem.eshop.systemtest.core.erp.driver.dtos.ReturnsProductRequest;
import com.optivem.lang.Result;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

/**
 * Stub ERP client for making HTTP calls to the WireMock stub.
 */
public class ErpStubClient extends BaseErpClient {

    private final WireMock wireMock;

    public ErpStubClient(String baseUrl) {
        super(baseUrl);

        // Parse the base URL to extract host and port for WireMock admin API
        var url = java.net.URI.create(baseUrl);
        this.wireMock = new WireMock(url.getHost(), url.getPort());
    }

    public void configureGetProduct(ExtProductDetailsResponse response) {
        var sku = response.getId();
        var price = response.getPrice();

        // TODO: VJ: Use object mapper, well use the new class from libs

        wireMock.register(get(urlPathEqualTo("/erp/api/products/" + sku))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(String.format(
                                "{\"id\":\"%s\",\"price\":\"%s\",\"title\":\"Test Product\",\"description\":\"Test Description\",\"category\":\"Test Category\",\"brand\":\"Test Brand\"}",
                                sku, price))));

    }
}

