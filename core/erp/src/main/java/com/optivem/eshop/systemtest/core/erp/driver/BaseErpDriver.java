package com.optivem.eshop.systemtest.core.erp.driver;

import com.optivem.eshop.systemtest.core.commons.error.Error;
import com.optivem.eshop.systemtest.core.erp.client.BaseErpClient;
import com.optivem.eshop.systemtest.core.erp.driver.dtos.GetProductRequest;
import com.optivem.eshop.systemtest.core.erp.driver.dtos.ReturnsProductRequest;
import com.optivem.eshop.systemtest.core.erp.driver.dtos.GetProductResponse;
import com.optivem.lang.Closer;
import com.optivem.lang.Result;

public abstract class BaseErpDriver<TClient extends BaseErpClient> implements ErpDriver {

    protected final TClient client;

    protected BaseErpDriver(TClient client) {
        this.client = client;
    }

    @Override
    public Result<Void, Error> goToErp() {
        return client.checkHealth();
    }

    public abstract Result<Void, Error> returnsProduct(ReturnsProductRequest request);

    @Override
    public Result<GetProductResponse, Error> getProduct(GetProductRequest request) {
        return client.getProduct(request.getSku())
                .map(productDetails -> GetProductResponse.builder()
                        .sku(productDetails.getId())
                        .price(productDetails.getPrice())
                        .build());
    }

    @Override
    public void close() throws Exception {
        Closer.close(client);
    }
}
