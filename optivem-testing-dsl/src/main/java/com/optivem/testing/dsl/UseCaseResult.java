package com.optivem.testing.dsl;

import com.optivem.lang.Result;

import java.util.function.BiFunction;

import static com.optivem.testing.assertions.ResultAssert.assertThatResult;

public class UseCaseResult<TSuccessResponse, TSuccessVerification, TFailureResponse, TFailureVerification extends UseCaseFailureVerification<TFailureResponse>> {
    private final Result<TSuccessResponse, TFailureResponse> result;
    private final UseCaseContext context;
    private final BiFunction<TSuccessResponse, UseCaseContext, TSuccessVerification> verificationFactory;
    private final BiFunction<Result<?, TFailureResponse>, UseCaseContext, TFailureVerification> failureVerificationFactory;

    public UseCaseResult(
            Result<TSuccessResponse, TFailureResponse> result,
            UseCaseContext context,
            BiFunction<TSuccessResponse, UseCaseContext, TSuccessVerification> verificationFactory,
            BiFunction<Result<?, TFailureResponse>, UseCaseContext, TFailureVerification> failureVerificationFactory) {
        this.result = result;
        this.context = context;
        this.verificationFactory = verificationFactory;
        this.failureVerificationFactory = failureVerificationFactory;
    }

    public TSuccessVerification shouldSucceed() {
        assertThatResult(result).isSuccess();
        return verificationFactory.apply(result.getValue(), context);
    }

    public TFailureVerification shouldFail() {
        assertThatResult(result).isFailure();
        return failureVerificationFactory.apply(result, context);
    }
}

