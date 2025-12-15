package com.optivem.testing.dsl;

public abstract class BaseUseCase<TDriver, TSuccessResponse, TSuccessVerification, TFailureResponse, TFailureVerification extends UseCaseFailureVerification<TFailureResponse>> implements UseCase<UseCaseResult<TSuccessResponse, TSuccessVerification, TFailureResponse, TFailureVerification>> {
    protected final TDriver driver;
    protected final UseCaseContext context;

    protected BaseUseCase(TDriver driver, UseCaseContext context) {
        this.driver = driver;
        this.context = context;
    }

    public abstract UseCaseResult<TSuccessResponse, TSuccessVerification, TFailureResponse, TFailureVerification> execute();
}

