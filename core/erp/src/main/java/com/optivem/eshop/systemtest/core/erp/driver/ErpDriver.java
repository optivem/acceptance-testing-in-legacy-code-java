package com.optivem.eshop.systemtest.core.erp.driver;

import com.optivem.eshop.systemtest.core.commons.error.Error;
import com.optivem.eshop.systemtest.core.erp.driver.dtos.requests.ReturnsProductRequest;
import com.optivem.lang.Result;

public interface ErpDriver extends AutoCloseable {
    Result<Void, Error> goToErp();
    Result<Void, Error> returnsProduct(ReturnsProductRequest request);
}
