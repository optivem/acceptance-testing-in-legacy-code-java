package com.optivem.eshop.systemtest.core.clients.ui.pages;

import com.microsoft.playwright.Page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewOrderPage extends BasePage {

    public NewOrderPage(Page page, String baseUrl) {
        super(page, baseUrl);
    }

    public void inputProductId(String productId) {
        fill("[aria-label=\"Product ID\"]", productId);
    }

    public void inputQuantity(String quantity) {
        fill("[aria-label=\"Quantity\"]", quantity);
    }

    public void inputCountry(String country) {
        fill("[aria-label=\"Country\"]", country);
    }

    public void clickPlaceOrder() {
        click("[aria-label=\"Place Order\"]");
    }

    public String readConfirmationMessageText() {
        return readTextContent("[role='alert']");
    }

    public String extractOrderNumber() {
        var confirmationMessageText = readConfirmationMessageText();
        var pattern = Pattern.compile("Success! Order has been created with Order Number ([\\w-]+)");
        var matcher = pattern.matcher(confirmationMessageText);
        assertTrue(matcher.find(), "Should extract order number from confirmation message: " + confirmationMessageText);
        return matcher.group(1);
    }

    public double extractOriginalPrice() {
        var confirmationMessageText = readConfirmationMessageText();
        var pattern = Pattern.compile("Original Price \\$(\\d+(?:\\.\\d{2})?)");
        var matcher = pattern.matcher(confirmationMessageText);
        assertTrue(matcher.find(), "Should extract original price from confirmation message: " + confirmationMessageText);
        return Double.parseDouble(matcher.group(1));
    }
}

