package com.optivem.eshop.systemtest.core.dsl.commons.commands;

import com.optivem.eshop.systemtest.core.dsl.commons.context.Context;

public abstract class BaseSuccessResult<TResponse> {
    protected final TResponse response;
    protected final Context context;

    protected BaseSuccessResult(TResponse response, Context context) {
        this.response = response;
        this.context = context;
    }
}

