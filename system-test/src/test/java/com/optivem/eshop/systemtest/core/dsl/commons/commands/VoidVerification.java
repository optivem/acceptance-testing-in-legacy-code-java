package com.optivem.eshop.systemtest.core.dsl.commons.commands;

import com.optivem.eshop.systemtest.core.dsl.commons.context.Context;

@SuppressWarnings("UnusedReturnValue")
public class VoidVerification extends BaseSuccessResult<Void> {

    public VoidVerification(Void response, Context context) {
        super(response, context);
    }
}

