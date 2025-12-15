package com.optivem.eshop.systemtest.core.tax.dsl.commands.base;

import com.optivem.eshop.systemtest.core.common.dsl.ErrorFailureVerification;
import com.optivem.lang.Error;
import com.optivem.lang.Result;
import com.optivem.testing.dsl.UseCaseContext;
import com.optivem.testing.dsl.UseCaseResult;
import com.optivem.testing.dsl.BaseUseCaseSuccessVerification;

import java.util.function.BiFunction;

public class TaxUseCaseResult<TResponse, TVerification extends BaseUseCaseSuccessVerification<TResponse>>
        extends UseCaseResult<TResponse, TVerification, Error, ErrorFailureVerification> {

    public TaxUseCaseResult(
            Result<TResponse, Error> result,
            UseCaseContext context,
            BiFunction<TResponse, UseCaseContext, TVerification> verificationFactory) {
        super(result, context, verificationFactory, ErrorFailureVerification::new);
    }
}
