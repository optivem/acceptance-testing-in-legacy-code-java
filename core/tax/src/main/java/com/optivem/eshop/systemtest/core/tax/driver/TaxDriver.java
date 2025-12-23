package com.optivem.eshop.systemtest.core.tax.driver;

import com.optivem.eshop.systemtest.core.tax.commons.TaxError;
import com.optivem.lang.Result;

public interface TaxDriver extends AutoCloseable {
    Result<Void, TaxError> goToTax();
}

