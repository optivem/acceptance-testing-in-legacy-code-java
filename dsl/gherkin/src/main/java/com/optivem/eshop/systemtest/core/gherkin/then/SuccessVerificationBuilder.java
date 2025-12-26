package com.optivem.eshop.systemtest.core.gherkin.then;

import com.optivem.testing.dsl.ResponseVerification;
import com.optivem.testing.dsl.UseCaseContext;

import java.util.function.Consumer;

public class SuccessVerificationBuilder<TVerification extends ResponseVerification<?, UseCaseContext>> {
    private final ThenClause thenClause;
    private final TVerification successVerification;

    public SuccessVerificationBuilder(ThenClause thenClause, TVerification successVerification) {
        this.thenClause = thenClause;
        this.successVerification = successVerification;
    }

    public SuccessVerificationBuilder<TVerification> verify(Consumer<TVerification> verifier) {
        verifier.accept(successVerification);
        return this;
    }

    public <T extends ResponseVerification<?, UseCaseContext>> SuccessVerificationBuilder<TVerification> verify(Class<T> type, Consumer<T> verifier) {
        verifier.accept(type.cast(successVerification));
        return this;
    }

    public ThenClause and() {
        return thenClause;
    }
}
