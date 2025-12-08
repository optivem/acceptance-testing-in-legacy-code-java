package com.optivem.eshop.systemtest.core.dsl.shop.commands.execute;

import com.optivem.eshop.systemtest.core.dsl.commons.DslContext;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseFailureResult<T extends BaseFailureResult<T>> {
    protected final DslContext context;

    protected BaseFailureResult(DslContext context) {
        this.context = context;
    }

    protected abstract Collection<String> getErrors();

    @SuppressWarnings("unchecked")
    public T expectErrorMessage(String expectedMessage) {
        // Replace all aliases in the expected message with their actual generated values
        var expandedExpectedMessage = expectedMessage;
        var aliases = context.params().getAllAliases();
        for (var entry : aliases.entrySet()) {
            var alias = entry.getKey();
            var actualValue = entry.getValue();
            expandedExpectedMessage = expandedExpectedMessage.replace(alias, actualValue);
        }

        var errors = getErrors();
        var finalExpectedMessage = expandedExpectedMessage;
        assertThat(errors)
                .withFailMessage("Expected error message: '%s', but got: %s", finalExpectedMessage, errors)
                .contains(finalExpectedMessage);
        return (T) this;
    }
}

