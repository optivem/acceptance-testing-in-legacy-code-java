package com.optivem.eshop.systemtest.core.dsl.shop.commands.execute;

import com.optivem.eshop.systemtest.core.drivers.system.commons.dtos.GetOrderResponse;
import com.optivem.eshop.systemtest.core.dsl.commons.DslContext;
import com.optivem.results.Result;

import static com.optivem.testing.assertions.ResultAssert.assertThatResult;

public class ViewOrderResult {
    private final Result<GetOrderResponse> result;
    private final DslContext context;

    public ViewOrderResult(Result<GetOrderResponse> result, DslContext context) {
        this.result = result;
        this.context = context;
    }

    public ViewOrderSuccessResult expectSuccess() {
        assertThatResult(result).isSuccess();
        return new ViewOrderSuccessResult(result.getValue(), context);
    }

    public FailureResult expectFailure() {
        assertThatResult(result).isFailure();
        return new FailureResult(result, context);
    }
}

