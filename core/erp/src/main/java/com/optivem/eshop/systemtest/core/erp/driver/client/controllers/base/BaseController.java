package com.optivem.eshop.systemtest.core.erp.driver.client.controllers.base;

import com.optivem.eshop.systemtest.core.commons.error.Error;
import com.optivem.eshop.systemtest.core.commons.error.ProblemDetailConverter;
import com.optivem.eshop.systemtest.core.commons.error.ProblemDetailResponse;
import com.optivem.http.JsonHttpClient;
import com.optivem.lang.Result;

public abstract class BaseController {
    protected final JsonHttpClient<ProblemDetailResponse> httpClient;

    protected BaseController(JsonHttpClient<ProblemDetailResponse> httpClient) {
        this.httpClient = httpClient;
    }
}
