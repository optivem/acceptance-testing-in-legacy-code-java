package com.optivem.eshop.systemtest.core.dsl.shop.commands.execute;

import com.optivem.eshop.systemtest.core.drivers.system.commons.dtos.PlaceOrderResponse;
import com.optivem.eshop.systemtest.core.dsl.commons.DslContext;
import com.optivem.results.Result;

import static com.optivem.testing.assertions.ResultAssert.assertThatResult;

public class PlaceOrderResult {
    private final Result<PlaceOrderResponse> result;
    private final DslContext context;

    public PlaceOrderResult(Result<PlaceOrderResponse> result, DslContext context) {
        this.result = result;
        this.context = context;
    }

    public PlaceOrderSuccessResult expectSuccess() {
        assertThatResult(result).isSuccess();
        return new PlaceOrderSuccessResult(result.getValue(), context);
    }

    public FailureResult expectFailure() {
        assertThatResult(result).isFailure();
        return new FailureResult(result, context);
    }
}


