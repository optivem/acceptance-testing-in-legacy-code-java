package com.optivem.eshop.systemtest.core.erp.client.real;

import com.optivem.eshop.systemtest.core.erp.client.common.BaseErpClient;
import com.optivem.eshop.systemtest.core.erp.client.real.controllers.RealProductController;

/**
 * Real ERP client for making actual HTTP calls to the ERP API.
 */
public class ErpRealClient extends BaseErpClient<RealProductController> {

    public ErpRealClient(String baseUrl) {
        super(baseUrl, RealProductController::new);
    }
}

