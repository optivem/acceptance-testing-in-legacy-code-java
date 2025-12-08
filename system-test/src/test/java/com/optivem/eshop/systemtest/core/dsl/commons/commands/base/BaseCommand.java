package com.optivem.eshop.systemtest.core.dsl.commons.commands.base;

import com.optivem.eshop.systemtest.core.dsl.commons.commands.Command;
import com.optivem.eshop.systemtest.core.dsl.commons.commands.CommandResult;
import com.optivem.eshop.systemtest.core.dsl.commons.context.Context;

public abstract class BaseCommand<TDriver, TResponse, TVerification> implements Command<CommandResult<TResponse, TVerification>> {
    protected final TDriver driver;
    protected final Context context;

    protected BaseCommand(TDriver driver, Context context) {
        this.driver = driver;
        this.context = context;
    }

    public abstract CommandResult<TResponse, TVerification> execute();
}

