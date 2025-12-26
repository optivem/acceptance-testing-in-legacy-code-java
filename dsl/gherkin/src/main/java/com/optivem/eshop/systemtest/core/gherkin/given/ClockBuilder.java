package com.optivem.eshop.systemtest.core.gherkin.given;

import com.optivem.eshop.systemtest.core.SystemDsl;
import com.optivem.eshop.systemtest.core.clock.dsl.commands.ReturnsTime;
import com.optivem.eshop.systemtest.core.gherkin.when.WhenClause;

import java.time.Instant;

public class ClockBuilder {
    private final GivenClause givenClause;
    private final ReturnsTime returnsTime;

    public ClockBuilder(GivenClause givenClause, SystemDsl app) {
        this.givenClause = givenClause;
        this.returnsTime = app.clock().returnsTime();
    }

    public ClockBuilder withTime(Instant time) {
        returnsTime.time(time);
        return this;
    }

    public GivenClause and() {
        return givenClause;
    }

    public WhenClause when() {
        return givenClause.when();
    }

    void execute(SystemDsl app) {
        returnsTime.execute().shouldSucceed();
    }
}

