package com.optivem.testing.dsl;

@SuppressWarnings("UnusedReturnValue")
public class UseCaseVoidSuccessVerification<TContext> extends BaseUseCaseSuccessVerification<Void, TContext> {

    public UseCaseVoidSuccessVerification(Void response, TContext context) {
        super(response, context);
    }
}

