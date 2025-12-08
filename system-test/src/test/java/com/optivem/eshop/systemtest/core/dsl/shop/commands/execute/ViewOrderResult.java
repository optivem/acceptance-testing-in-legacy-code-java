package com.optivem.eshop.systemtest.core.dsl.shop.commands.execute;

import com.optivem.eshop.systemtest.core.drivers.system.commons.dtos.GetOrderResponse;
import com.optivem.eshop.systemtest.core.drivers.system.commons.enums.OrderStatus;
import com.optivem.eshop.systemtest.core.dsl.commons.DslContext;
import com.optivem.results.Result;

import java.math.BigDecimal;

import static com.optivem.testing.assertions.ResultAssert.assertThatResult;
import static org.assertj.core.api.Assertions.assertThat;

public class ViewOrderResult {
    private final Result<GetOrderResponse> result;
    private final DslContext context;
    private final GetOrderResponse response;

    public ViewOrderResult(Result<GetOrderResponse> result, DslContext context) {
        this.result = result;
        this.context = context;
        this.response = result.isSuccess() ? result.getValue() : null;
    }

    public ViewOrderResult expectSuccess() {
        assertThatResult(result).isSuccess();
        return this;
    }

    public ViewOrderResult expectFailure() {
        assertThatResult(result).isFailure();
        return this;
    }

    public ViewOrderResult expectSku(String skuParamAlias) {
        var expectedSku = context.params().getOrGenerateAliasValue(skuParamAlias);
        assertThat(response.getSku()).isEqualTo(expectedSku);
        return this;
    }

    public ViewOrderResult expectQuantity(int expectedQuantity) {
        assertThat(response.getQuantity()).isEqualTo(expectedQuantity);
        return this;
    }

    public ViewOrderResult expectCountry(String expectedCountry) {
        assertThat(response.getCountry()).isEqualTo(expectedCountry);
        return this;
    }

    public ViewOrderResult expectUnitPrice(String expectedUnitPrice) {
        assertThat(response.getUnitPrice()).isEqualTo(new BigDecimal(expectedUnitPrice));
        return this;
    }

    public ViewOrderResult expectOriginalPrice(String expectedOriginalPrice) {
        assertThat(response.getOriginalPrice()).isEqualTo(new BigDecimal(expectedOriginalPrice));
        return this;
    }

    public ViewOrderResult expectStatus(OrderStatus expectedStatus) {
        assertThat(response.getStatus()).isEqualTo(expectedStatus);
        return this;
    }

    public ViewOrderResult expectDiscountRateGreaterThanOrEqualToZero() {
        var discountRate = response.getDiscountRate();
        assertThat(discountRate)
                .withFailMessage("Discount rate should be non-negative, but was: %s", discountRate)
                .isGreaterThanOrEqualTo(BigDecimal.ZERO);
        return this;
    }

    public ViewOrderResult expectDiscountAmountGreaterThanOrEqualToZero() {
        var discountAmount = response.getDiscountAmount();
        assertThat(discountAmount)
                .withFailMessage("Discount amount should be non-negative, but was: %s", discountAmount)
                .isGreaterThanOrEqualTo(BigDecimal.ZERO);
        return this;
    }

    public ViewOrderResult expectSubtotalPriceGreaterThanZero() {
        var subtotalPrice = response.getSubtotalPrice();
        assertThat(subtotalPrice)
                .withFailMessage("Subtotal price should be positive, but was: %s", subtotalPrice)
                .isGreaterThan(BigDecimal.ZERO);
        return this;
    }

    public ViewOrderResult expectTaxRateGreaterThanOrEqualToZero() {
        var taxRate = response.getTaxRate();
        assertThat(taxRate)
                .withFailMessage("Tax rate should be non-negative, but was: %s", taxRate)
                .isGreaterThanOrEqualTo(BigDecimal.ZERO);
        return this;
    }

    public ViewOrderResult expectTaxAmountGreaterThanOrEqualToZero() {
        var taxAmount = response.getTaxAmount();
        assertThat(taxAmount)
                .withFailMessage("Tax amount should be non-negative, but was: %s", taxAmount)
                .isGreaterThanOrEqualTo(BigDecimal.ZERO);
        return this;
    }

    public ViewOrderResult expectTotalPriceGreaterThanZero() {
        var totalPrice = response.getTotalPrice();
        assertThat(totalPrice)
                .withFailMessage("Total price should be positive, but was: %s", totalPrice)
                .isGreaterThan(BigDecimal.ZERO);
        return this;
    }

    public GetOrderResponse getResponse() {
        return response;
    }

    public Result<GetOrderResponse> getResult() {
        return result;
    }
}

