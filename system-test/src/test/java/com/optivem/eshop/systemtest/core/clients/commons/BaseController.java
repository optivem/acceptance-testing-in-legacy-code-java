package com.optivem.eshop.systemtest.core.clients.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BaseController {

    protected static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    protected final HttpClient httpClient;
    private final String baseUrl;
    private final String controllerEndpoint;

    public BaseController(HttpClient httpClient, String baseUrl, String controllerEndpoint) {
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        this.controllerEndpoint = controllerEndpoint;
    }

    protected HttpResponse<String> get(String endpoint) {
        var uri = getUri(endpoint);
        var request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        return sendRequest(request);
    }

    protected HttpResponse<String> get() {
        return get(null);
    }

    protected HttpResponse<String> post(String endpoint, Object requestBody) {
        var uri = getUri(endpoint);
        var jsonBody = serializeRequest(requestBody);

        var request = HttpRequest.newBuilder()
                .uri(uri)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return sendRequest(request);
    }

    protected  HttpResponse<String> post(Object requestBody) {
        return post(null, requestBody);
    }

    protected HttpResponse<String> post(String endpoint) {
        var uri = getUri(endpoint);

        var request = HttpRequest.newBuilder()
                .uri(uri)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        return sendRequest(request);
    }

    protected void assertOk(HttpResponse<String> httpResponse) {
        assertStatus(httpResponse, HttpStatus.OK);
    }

    protected void assertCreated(HttpResponse<String> httpResponse) {
        assertStatus(httpResponse, HttpStatus.CREATED);
    }

    protected void assertNoContent(HttpResponse<String> httpResponse) {
        assertStatus(httpResponse, HttpStatus.NO_CONTENT);
    }

    protected void assertUnprocessableEntity(HttpResponse<String> httpResponse) {
        assertStatus(httpResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private void assertStatus(HttpResponse<String> httpResponse, HttpStatus expectedStatus) {
        assertEquals(expectedStatus.value(), httpResponse.statusCode(),
            "Expected status " + expectedStatus.value() + " but got " + httpResponse.statusCode() +
            ". Response body: " + httpResponse.body());
    }

    private URI getUri(String path) {
        try {
            var uriString = getUriString(path);
            return new URI(uriString);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to get uri for " + path, ex);
        }
    }

    private String getUriString(String path) {
        if(path == null) {
            return baseUrl + "/" + controllerEndpoint;
        }

        return baseUrl + "/" + controllerEndpoint + "/" + path;
    }

    protected <T> T readBody(HttpResponse<String> httpResponse, Class<T> responseType) {
        try {
            var responseBody = httpResponse.body();
            return objectMapper.readValue(responseBody, responseType);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to deserialize response to " + responseType.getSimpleName(), ex);
        }
    }

    private String serializeRequest(Object request) {
        try {
            return objectMapper.writeValueAsString(request);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to serialize request object", ex);
        }
    }

    private HttpResponse<String> sendRequest(HttpRequest httpRequest) {
        try {
            return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to send HTTP request", ex);
        }
    }
}
