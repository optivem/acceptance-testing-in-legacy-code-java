package com.optivem.testing.dsl;

public abstract class BaseUseCaseSuccessVerification<TSuccessResponse, TContext> {
    protected final TSuccessResponse response;
    protected final TContext context;

    protected BaseUseCaseSuccessVerification(TSuccessResponse response, TContext context) {
        this.response = response;
        this.context = context;
    }
}

