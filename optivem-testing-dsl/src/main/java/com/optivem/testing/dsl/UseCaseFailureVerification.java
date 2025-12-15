package com.optivem.testing.dsl;

import com.optivem.lang.Result;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("UnusedReturnValue")
public class UseCaseFailureVerification<TFailureResponse> {
    private final Result<?, TFailureResponse> result;
    private final UseCaseContext context;

    public UseCaseFailureVerification(Result<?, TFailureResponse> result, UseCaseContext context) {
        this.result = result;
        this.context = context;
    }

    public TFailureResponse getError() {
        return result.getError();
    }

    public UseCaseContext getContext() {
        return context;
    }

    @SuppressWarnings("unchecked")
    public <T extends UseCaseFailureVerification<TFailureResponse>> T assertError(TFailureResponse expectedError) {
        TFailureResponse actualError = result.getError();
        assertThat(actualError)
                .withFailMessage("Expected error: '%s', but got: '%s'", expectedError, actualError)
                .isEqualTo(expectedError);
        return (T) this;
    }
}

