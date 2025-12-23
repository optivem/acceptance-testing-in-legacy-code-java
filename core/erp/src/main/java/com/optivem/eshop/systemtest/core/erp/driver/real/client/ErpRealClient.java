package com.optivem.eshop.systemtest.core.erp.driver.real.client;

import com.optivem.eshop.systemtest.core.commons.error.ProblemDetailResponse;
import com.optivem.eshop.systemtest.core.erp.driver.client.BaseErpClient;
import com.optivem.eshop.systemtest.core.erp.driver.real.client.controllers.RealProductController;
import com.optivem.http.JsonHttpClient;

/**
 * Real ERP client for making actual HTTP calls to the ERP API.
 */
public class ErpRealClient extends BaseErpClient<RealProductController> {

    public ErpRealClient(JsonHttpClient<ProblemDetailResponse> httpClient) {
        super(httpClient, RealProductController::new);
    }
}

