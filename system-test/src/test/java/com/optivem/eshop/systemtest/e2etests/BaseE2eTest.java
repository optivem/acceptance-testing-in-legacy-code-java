package com.optivem.eshop.systemtest.e2etests;

import com.optivem.eshop.systemtest.core.drivers.DriverCloser;
import com.optivem.eshop.systemtest.core.drivers.DriverFactory;
import com.optivem.eshop.systemtest.core.drivers.external.ErpApiDriver;
import com.optivem.eshop.systemtest.core.drivers.external.TaxApiDriver;
import com.optivem.eshop.systemtest.core.drivers.system.ShopDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class BaseE2eTest {

    private ShopDriver shopDriver;
    private ErpApiDriver erpApiDriver;
    private TaxApiDriver taxApiDriver;

    @BeforeEach
    void setUp() {
        var driverFactory = new DriverFactory();
        shopDriver = createDriver(driverFactory);
        erpApiDriver = driverFactory.createErpApiDriver();
        taxApiDriver = driverFactory.createTaxApiDriver();
    }

    protected abstract ShopDriver createDriver(DriverFactory driverFactory);

    @AfterEach
    void tearDown() {
        DriverCloser.close(shopDriver);
        DriverCloser.close(erpApiDriver);
        DriverCloser.close(taxApiDriver);
    }

    @Test
    void shouldPlaceOrderAndCalculateOriginalPrice() {
        var sku = "ABC-" + UUID.randomUUID();
        erpApiDriver.createProduct(sku, "20.00");
        var result = shopDriver.placeOrder(sku, "5", "US");
        assertTrue(result.isSuccess());

        var orderNumber = result.getValue();
        shopDriver.confirmOrderNumberGeneratedWithPrefix(orderNumber, "ORD-");
        shopDriver.confirmOrderDetails(orderNumber, Optional.of(sku), Optional.of("5"), Optional.of("US"),
                Optional.of("20.00"), Optional.of("100.00"), Optional.of("PLACED"));

        shopDriver.confirmSubtotalPricePositive(orderNumber);
        shopDriver.confirmTotalPricePositive(orderNumber);
    }

    @Test
    void shouldCancelOrder() {
        var sku = "XYZ-" + UUID.randomUUID();
        erpApiDriver.createProduct(sku, "50.00");
        var result = shopDriver.placeOrder(sku, "2", "US");
        assertTrue(result.isSuccess());

        var orderNumber = result.getValue();
        shopDriver.confirmOrderDetails(orderNumber, Optional.of(sku), Optional.of("2"), Optional.of("US"),
                Optional.of("50.00"), Optional.of("100.00"), Optional.of("PLACED"));

        shopDriver.cancelOrder(orderNumber);
        shopDriver.confirmOrderCancelled(orderNumber);

        shopDriver.confirmOrderDetails(orderNumber, Optional.of(sku), Optional.of("2"), Optional.of("US"),
                Optional.of("50.00"), Optional.of("100.00"), Optional.of("CANCELLED"));
    }

    @Test
    void shouldRejectOrderWithNonExistentSku() {
        var result = shopDriver.placeOrder("NON-EXISTENT-SKU-12345", "5", "US");
        assertTrue(result.isFailure());
        assertThat(result.getErrors()).contains("Product does not exist for SKU: NON-EXISTENT-SKU-12345");
    }

    @Test
    void shouldRejectOrderWithNegativeQuantity() {
        var sku = "DEF-" + UUID.randomUUID();
        erpApiDriver.createProduct(sku, "30.00");
        var result = shopDriver.placeOrder(sku, "-3", "US");
        assertTrue(result.isFailure());
        assertThat(result.getErrors()).contains("Quantity must be positive");
    }


    private static Stream<Arguments> provideEmptySkuValues() {
        return Stream.of(
                Arguments.of(""),      // Empty string
                Arguments.of("   ")    // Whitespace string
        );
    }

    @ParameterizedTest
    @MethodSource("provideEmptySkuValues")
    void shouldRejectOrderWithEmptySku(String sku) {
        var result = shopDriver.placeOrder(sku, "5", "US");
        assertTrue(result.isFailure());
        assertThat(result.getErrors()).contains("SKU must not be empty");
    }

    private static Stream<Arguments> provideEmptyQuantityValues() {
        return Stream.of(
                Arguments.of(""),      // Empty string
                Arguments.of("   ")    // Whitespace string
        );
    }

    @ParameterizedTest
    @MethodSource("provideEmptyQuantityValues")
    void shouldRejectOrderWithEmptyQuantity(String emptyQuantity) {
        var result = shopDriver.placeOrder("some-sku", emptyQuantity, "US");
        assertTrue(result.isFailure());
        assertThat(result.getErrors()).contains("Quantity must not be empty");
    }

    private static Stream<Arguments> provideNonIntegerQuantityValues() {
        return Stream.of(
                Arguments.of("3.5"),   // Decimal value
                Arguments.of("lala")   // String value
        );
    }

    @ParameterizedTest
    @MethodSource("provideNonIntegerQuantityValues")
    void shouldRejectOrderWithNonIntegerQuantity(String nonIntegerQuantity) {
        var result = shopDriver.placeOrder("some-sku", nonIntegerQuantity, "US");
        assertTrue(result.isFailure());
        assertThat(result.getErrors()).contains("Quantity must be an integer");
    }

    private static Stream<Arguments> provideEmptyCountryValues() {
        return Stream.of(
                Arguments.of(""),      // Empty string
                Arguments.of("   ")    // Whitespace string
        );
    }

    @ParameterizedTest
    @MethodSource("provideEmptyCountryValues")
    void shouldRejectOrderWithEmptyCountry(String emptyCountry) {
        var result = shopDriver.placeOrder("some-sku", "5", emptyCountry);
        assertTrue(result.isFailure());
        assertThat(result.getErrors()).contains("Country must not be empty");
    }

}

