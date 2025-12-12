package com.optivem.eshop.systemtest;

import com.optivem.eshop.systemtest.core.AppDsl;

public class AppDslFactory {
    public static AppDsl create() {
        var configuration = AppConfigurationReader.readConfiguration();
        return new AppDsl(configuration);
    }
}
