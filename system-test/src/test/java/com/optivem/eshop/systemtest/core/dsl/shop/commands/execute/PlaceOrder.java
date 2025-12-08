package com.optivem.eshop.systemtest.core.dsl.shop.commands.execute;

import com.optivem.eshop.systemtest.core.drivers.system.ShopDriver;
import com.optivem.eshop.systemtest.core.drivers.system.commons.dtos.PlaceOrderRequest;
import com.optivem.eshop.systemtest.core.dsl.commons.DslContext;
import com.optivem.eshop.systemtest.core.dsl.shop.commands.BaseShopCommand;

import static com.optivem.testing.assertions.ResultAssert.assertThatResult;
import static org.assertj.core.api.Assertions.assertThat;

public class PlaceOrder extends BaseShopCommand<Void> {
    public static final String COMMAND_NAME = "PlaceOrder";

    private String orderNumberResultAlias;
    private String skuParamAlias;
    private String quantity;
    private String country;
    private String expectedOrderNumberPrefix;

    public PlaceOrder(ShopDriver driver, DslContext context) {
        super(driver, context);
    }

    public PlaceOrder orderNumber(String orderNumberResultAlias) {
        this.orderNumberResultAlias = orderNumberResultAlias;
        return this;
    }

    public PlaceOrder sku(String skuParamAlias) {
        this.skuParamAlias = skuParamAlias;
        return this;
    }

    public PlaceOrder quantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public PlaceOrder country(String country) {
        this.country = country;
        return this;
    }

    public PlaceOrder expectOrderNumberStartsWith(String expectedOrderNumberPrefix) {
        this.expectedOrderNumberPrefix = expectedOrderNumberPrefix;
        return this;
    }

    @Override
    public Void execute() {
        var sku = context.params().getAliasValue(skuParamAlias);

        var request = PlaceOrderRequest.builder()
                .sku(sku)
                .quantity(quantity)
                .country(country)
                .build();
        var result = driver.placeOrder(request);

        // Store the result for confirmation
        context.results().registerResult(COMMAND_NAME, orderNumberResultAlias, result);

        // Assert that the order was placed successfully
        assertThatResult(result).isSuccess();

        // If successful, extract and store the order number as an alias
        var orderNumber = result.getValue().getOrderNumber();
        context.results().setAliasValue(orderNumberResultAlias, orderNumber);

        // Verify order number prefix if specified
        if (expectedOrderNumberPrefix != null) {
            assertThat(orderNumber).startsWith(expectedOrderNumberPrefix);
        }

        return null;
    }
}
