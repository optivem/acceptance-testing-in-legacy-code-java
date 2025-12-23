package com.optivem.eshop.systemtest;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.gherkin.ScenarioDsl;
import com.optivem.lang.Closer;
import com.optivem.testing.channels.ChannelExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ChannelExtension.class)
public class BaseSystemTest {
    protected SystemDsl app;
    protected ScenarioDsl scenario;

    @BeforeEach
    void setUp() {
        app = SystemDslFactory.create();
        scenario = new ScenarioDsl(app);
    }

    @AfterEach
    void tearDown() {
        Closer.close(app);
    }
}