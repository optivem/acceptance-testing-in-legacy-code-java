package com.optivem.eshop.systemtest.core.clients.ui.pages;

import com.microsoft.playwright.Page;

public class HomePage extends BasePage {

    public HomePage(Page page, String baseUrl) {
        super(page, baseUrl);
    }

    public NewOrderPage clickNewOrder() {
        click("a[href='/shop.html']");
        return new NewOrderPage(page, getBaseUrl());
    }

    public OrderHistoryPage clickOrderHistory() {
        click("a[href='/order-history.html']");
        return new OrderHistoryPage(page, getBaseUrl());
    }
}

