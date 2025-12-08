package com.optivem.eshop.systemtest.core.dsl.commons.commands;

import com.optivem.eshop.systemtest.core.dsl.commons.context.DslContext;

public class VoidSuccessResult extends BaseSuccessResult<Void> {

    public VoidSuccessResult(Void response, DslContext context) {
        super(response, context);
    }
}

