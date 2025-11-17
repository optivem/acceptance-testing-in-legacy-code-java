package com.optivem.eshop.systemtest.core.clients.ui.pages;

import com.microsoft.playwright.Page;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderHistoryPage extends BasePage {

    public OrderHistoryPage(Page page, String baseUrl) {
        super(page, baseUrl);
    }

    public void inputOrderNumber(String orderNumber) {
        fill("[aria-label='Order Number']", orderNumber);
    }

    public void clickSearch() {
        click("[aria-label='Search']");
    }

    public void waitForOrderDetails() {
        var orderDetailsText = readTextContent("[role='alert']");
        assertTrue(orderDetailsText.contains("Order Details"), "Should display order details heading");
    }

    public String getOrderNumber() {
        return readInputValue("[aria-label='Display Order Number']");
    }

    public String getProductId() {
        return readInputValue("[aria-label='Display Product ID']");
    }

    public String getCountry() {
        return readInputValue("[aria-label='Display Country']");
    }

    public String getQuantity() {
        return readInputValue("[aria-label='Display Quantity']");
    }

    public String getUnitPrice() {
        return readInputValue("[aria-label='Display Unit Price']");
    }

    public String getOriginalPrice() {
        return readInputValue("[aria-label='Display Original Price']");
    }

    public String getDiscountRate() {
        return readInputValue("[aria-label='Display Discount Rate']");
    }

    public String getDiscountAmount() {
        return readInputValue("[aria-label='Display Discount Amount']");
    }

    public String getSubtotalPrice() {
        return readInputValue("[aria-label='Display Subtotal Price']");
    }

    public String getTaxRate() {
        return readInputValue("[aria-label='Display Tax Rate']");
    }

    public String getTaxAmount() {
        return readInputValue("[aria-label='Display Tax Amount']");
    }

    public String getTotalPrice() {
        return readInputValue("[aria-label='Display Total Price']");
    }

    public String getStatus() {
        return readInputValue("[aria-label='Display Status']");
    }

    public void clickCancelOrder() {
        page.onDialog(dialog -> dialog.accept()); // Auto-accept the alert
        click("[aria-label='Cancel Order']");

        // Wait a moment for the order to be cancelled and details refreshed
        page.waitForTimeout(1000);
    }

    public void confirmCancelButtonNotVisible() {
        assertTrue(isHidden("[aria-label='Cancel Order']"), "Cancel Order button should not be visible");
    }
}

