package com.optivem.eshop.systemtest.core.context;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Context {

    private final ParamContext paramContext;
    private final ResultContext resultContext;

    public Context() {
        this.paramContext = new ParamContext();
        this.resultContext = new ResultContext();
    }

    public ParamContext params() {
        return paramContext;
    }

    public ResultContext results() {
        return resultContext;
    }
}
