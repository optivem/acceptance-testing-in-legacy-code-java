package com.optivem.testing.dsl;

import com.optivem.lang.Result;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("UnusedReturnValue")
public class UseCaseFailureVerification<TFailureResponse, TContext> {
    private final Result<?, TFailureResponse> result;
    private final TContext context;

    public UseCaseFailureVerification(Result<?, TFailureResponse> result, TContext context) {
        this.result = result;
        this.context = context;
    }

    public TFailureResponse getError() {
        return result.getError();
    }

    public TContext getContext() {
        return context;
    }

    @SuppressWarnings("unchecked")
    public <T extends UseCaseFailureVerification<TFailureResponse, TContext>> T assertError(TFailureResponse expectedError) {
        TFailureResponse actualError = result.getError();
        assertThat(actualError)
                .withFailMessage("Expected error: '%s', but got: '%s'", expectedError, actualError)
                .isEqualTo(expectedError);
        return (T) this;
    }
}

