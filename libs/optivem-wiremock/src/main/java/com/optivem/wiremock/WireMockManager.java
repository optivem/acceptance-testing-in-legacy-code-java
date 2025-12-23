package com.optivem.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;

public class WireMockManager implements AutoCloseable {

    private final WireMock wireMock;

    public WireMockManager(String host, int port) {
        this.wireMock = new WireMock(host, port);
    }

    public WireMock getWireMock() {
        return wireMock;
    }

    public void resetAll() {
        wireMock.resetMappings();
        wireMock.resetRequests();
    }

    @Override
    public void close() {
        resetAll();
    }
}

