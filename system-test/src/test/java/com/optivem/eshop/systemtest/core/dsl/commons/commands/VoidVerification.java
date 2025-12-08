package com.optivem.eshop.systemtest.core.dsl.commons.commands;

import com.optivem.eshop.systemtest.core.dsl.commons.context.DslContext;

@SuppressWarnings("UnusedReturnValue")
public class VoidVerification extends BaseSuccessResult<Void> {

    public VoidVerification(Void response, DslContext context) {
        super(response, context);
    }
}

