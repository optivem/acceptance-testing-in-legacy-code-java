package com.optivem.eshop.systemtest.core.erp.dsl.commands;

import com.optivem.eshop.systemtest.core.erp.driver.ErpDriver;
import com.optivem.eshop.systemtest.core.erp.dsl.commands.base.BaseErpCommand;
import com.optivem.eshop.systemtest.core.erp.dsl.commands.base.ErpUseCaseResult;
import com.optivem.testing.dsl.UseCaseContext;
import com.optivem.testing.dsl.VoidResponseVerification;

public class GoToErp extends BaseErpCommand<Void, VoidResponseVerification<UseCaseContext>> {
    public GoToErp(ErpDriver driver, UseCaseContext context) {
        super(driver, context);
    }

    @Override
    public ErpUseCaseResult<Void, VoidResponseVerification<UseCaseContext>> execute() {
        var result = driver.goToErp();
        return new ErpUseCaseResult<>(result, context, VoidResponseVerification::new);
    }
}

