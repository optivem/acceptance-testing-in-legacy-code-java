package com.optivem.eshop.systemtest.core.dsl.shop.commands.base;

import com.optivem.eshop.systemtest.core.drivers.system.ShopDriver;
import com.optivem.eshop.systemtest.core.dsl.commons.commands.base.BaseCommand;
import com.optivem.eshop.systemtest.core.dsl.commons.context.Context;

public abstract class BaseShopCommand<TResponse, TVerification> extends BaseCommand<ShopDriver, TResponse, TVerification> {
    protected BaseShopCommand(ShopDriver driver, Context context) {
        super(driver, context);
    }
}

