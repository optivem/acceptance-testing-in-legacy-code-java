package com.optivem.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.optivem.lang.Result;

import java.net.URI;
import java.net.http.HttpResponse;

public class HttpUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readResponse(HttpResponse<String> httpResponse, Class<T> responseType) {
        try {
            var responseBody = httpResponse.body();
            return objectMapper.readValue(responseBody, responseType);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to deserialize response to " + responseType.getSimpleName(), ex);
        }
    }

    public static String serializeRequest(Object request) {
        try {
            return objectMapper.writeValueAsString(request);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to serialize request object", ex);
        }
    }

    public static URI getUri(String baseUrl, String path) {
        try {
            return new URI(baseUrl + path);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create URI for path: " + path, ex);
        }
    }

    private static boolean isSuccessStatusCode(HttpResponse<String> httpResponse) {
        int statusCode = httpResponse.statusCode();
        return statusCode >= 200 && statusCode < 300;
    }

    public static <T, E> Result<T, E> getResultOrFailure(HttpResponse<String> httpResponse, Class<T> responseType, Class<E> errorType) {
        if (!isSuccessStatusCode(httpResponse)) {
            var error = readResponse(httpResponse, errorType);
            return Result.failure(error);
        }

        // For Void type or 204 No Content, don't try to parse body
        if (responseType == Void.class || httpResponse.statusCode() == 204) {
            return Result.success(null);
        }

        var response = readResponse(httpResponse, responseType);
        return Result.success(response);
    }
}

