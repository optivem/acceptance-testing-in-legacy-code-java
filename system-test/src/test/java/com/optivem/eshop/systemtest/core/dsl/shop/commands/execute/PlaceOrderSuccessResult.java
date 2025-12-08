package com.optivem.eshop.systemtest.core.dsl.shop.commands.execute;

import com.optivem.eshop.systemtest.core.drivers.system.commons.dtos.PlaceOrderResponse;
import com.optivem.eshop.systemtest.core.dsl.commons.DslContext;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaceOrderSuccessResult {
    private final PlaceOrderResponse response;
    private final DslContext context;

    public PlaceOrderSuccessResult(PlaceOrderResponse response, DslContext context) {
        this.response = response;
        this.context = context;
    }

    public PlaceOrderSuccessResult expectOrderNumber(String orderNumberResultAlias) {
        var expectedOrderNumber = context.results().getAliasValue(orderNumberResultAlias);
        assertThat(response.getOrderNumber()).isEqualTo(expectedOrderNumber);
        return this;
    }

    public PlaceOrderSuccessResult expectOrderNumberStartsWith(String prefix) {
        assertThat(response.getOrderNumber()).startsWith(prefix);
        return this;
    }
}

