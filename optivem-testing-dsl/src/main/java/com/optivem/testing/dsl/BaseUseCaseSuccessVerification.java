package com.optivem.testing.dsl;

public abstract class BaseUseCaseSuccessVerification<TSuccessResponse> {
    protected final TSuccessResponse response;
    protected final UseCaseContext context;

    protected BaseUseCaseSuccessVerification(TSuccessResponse response, UseCaseContext context) {
        this.response = response;
        this.context = context;
    }
}

