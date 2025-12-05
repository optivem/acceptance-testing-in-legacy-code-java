package com.optivem.eshop.systemtest.core.dsl.system.commands.confirm;

import com.optivem.eshop.systemtest.core.drivers.system.ShopDriver;
import com.optivem.eshop.systemtest.core.drivers.system.commons.dtos.PlaceOrderResponse;
import com.optivem.eshop.systemtest.core.dsl.commons.DslContext;
import com.optivem.eshop.systemtest.core.dsl.system.commands.BaseShopCommand;
import com.optivem.eshop.systemtest.core.dsl.system.commands.execute.PlaceOrderCommand;

import static com.optivem.testing.assertions.ResultAssert.assertThatResult;

public class ConfirmOrderPlacedCommand extends BaseShopCommand {
    private String orderNumberResultAlias;

    protected ConfirmOrderPlacedCommand(ShopDriver driver, DslContext context) {
        super(driver, context);
    }

    public ConfirmOrderPlacedCommand orderNumber(String orderNumberResultAlias) {
        this.orderNumberResultAlias = orderNumberResultAlias;
        return this;
    }

    @Override
    public void execute() {
        var result = context.results().get("placeOrder", orderNumberResultAlias, PlaceOrderResponse.class);
        assertThatResult(result).isSuccess();
    }
}
