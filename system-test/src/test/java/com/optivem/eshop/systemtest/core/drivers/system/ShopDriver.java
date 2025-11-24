package com.optivem.eshop.systemtest.core.drivers.system;

import java.util.Optional;

public interface ShopDriver extends AutoCloseable {
    void goToShop();

    Result<String> placeOrder(String sku, String quantity, String country);

    void confirmOrderDetails(String orderNumber, Optional<String> sku, Optional<String> quantity, Optional<String> status);

    void confirmOrderNumberGeneratedWithPrefix(String orderNumber, String expectedPrefix);


    void confirmOrderPlaced(String orderNumber, String prefix);

    void cancelOrder(String orderNumber);

    void confirmOrderCancelled(String orderNumber);

    void confirmOrderStatusIsCancelled(String orderNumber);





//    @Test
//    void shouldRetrieveOrderHistory() {
//        var orderNumber = shopUiDriver.placeOrder("SAM-2020", "3", "US");
//
//        var orderHistoryPage = shopUiDriver.viewOrderDetails(orderNumber);
//
//        var displayOrderNumber = orderHistoryPage.getOrderNumber();
//        var displayProductId = orderHistoryPage.getProductId();
//        var displayCountry = orderHistoryPage.getCountry();
//        var displayQuantity = orderHistoryPage.getQuantity();
//        var displayUnitPrice = orderHistoryPage.getUnitPrice();
//        var displayOriginalPrice = orderHistoryPage.getOriginalPrice();
//        var displayDiscountRate = orderHistoryPage.getDiscountRate();
//        var displayDiscountAmount = orderHistoryPage.getDiscountAmount();
//        var displaySubtotalPrice = orderHistoryPage.getSubtotalPrice();
//        var displayTaxRate = orderHistoryPage.getTaxRate();
//        var displayTaxAmount = orderHistoryPage.getTaxAmount();
//        var displayTotalPrice = orderHistoryPage.getTotalPrice();
//
//        assertEquals(orderNumber, displayOrderNumber, "Should display the order number: " + orderNumber);
//        assertEquals("SAM-2020", displayProductId, "Should display SKU SAM-2020");
//        assertEquals("US", displayCountry, "Should display country US");
//        assertEquals("3", displayQuantity, "Should display quantity 3");
//        assertEquals("$499.99", displayUnitPrice, "Should display unit price $499.99");
//        assertEquals("$1499.97", displayOriginalPrice, "Should display original price $1499.97 (3 × $499.99)");
//
//        assertTrue(displayDiscountRate.endsWith("%"), "Should display discount rate with % symbol");
//        assertTrue(displayDiscountAmount.startsWith("$"), "Should display discount amount with $ symbol");
//        assertTrue(displaySubtotalPrice.startsWith("$"), "Should display subtotal price with $ symbol");
//        assertTrue(displayTaxRate.endsWith("%"), "Should display tax rate with % symbol");
//        assertTrue(displayTaxAmount.startsWith("$"), "Should display tax amount with $ symbol");
//        assertTrue(displayTotalPrice.startsWith("$"), "Should display total price with $ symbol");
//    }
}
