package com.optivem.eshop.systemtest.core.tax.dsl.commands;

import com.optivem.eshop.systemtest.core.tax.driver.TaxDriver;
import com.optivem.testing.dsl.UseCaseVoidSuccessVerification;
import com.optivem.testing.dsl.UseCaseContext;
import com.optivem.eshop.systemtest.core.tax.dsl.commands.base.BaseTaxCommand;
import com.optivem.eshop.systemtest.core.tax.dsl.commands.base.TaxUseCaseResult;

public class GoToTax extends BaseTaxCommand<Void, UseCaseVoidSuccessVerification<UseCaseContext>> {
    public GoToTax(TaxDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    @Override
    public TaxUseCaseResult<Void, UseCaseVoidSuccessVerification<UseCaseContext>> execute() {
        var result = driver.goToTax();
        return new TaxUseCaseResult<>(result, context, UseCaseVoidSuccessVerification::new);
    }
}

