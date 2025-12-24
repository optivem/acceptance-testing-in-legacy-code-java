package com.optivem.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;
import wiremock.com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

public class JsonWireMockClient {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private final ObjectMapper objectMapper;

    private final WireMock wireMock;

    public JsonWireMockClient(WireMock wireMock, ObjectMapper objectMapper) {
        this.wireMock = wireMock;
        this.objectMapper = objectMapper;
    }

    public JsonWireMockClient(WireMock wireMock) {
        this(wireMock, createDefaultObjectMapper());
    }

    private static ObjectMapper createDefaultObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public <T> void configureGet(String path, int statusCode, T response) {
        var responseBody = serialize(response);

        wireMock.register(WireMock.get(urlPathEqualTo(path))
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody(responseBody)));
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to serialize object", ex);
        }
    }
}
