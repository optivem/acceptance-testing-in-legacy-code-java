package com.optivem.eshop.systemtest.core.drivers.system;

import com.optivem.eshop.systemtest.core.clients.system.ui.ShopUiClient;
import com.optivem.eshop.systemtest.core.clients.system.ui.pages.HomePage;
import com.optivem.eshop.systemtest.core.clients.system.ui.pages.NewOrderPage;
import com.optivem.eshop.systemtest.core.clients.system.ui.pages.OrderHistoryPage;

public class ShopUiDriver implements AutoCloseable {

    private final ShopUiClient shopUiClient;

    public ShopUiDriver(ShopUiClient shopUiClient) {
        this.shopUiClient = shopUiClient;
    }

    public HomePage openHomePage() {
        return shopUiClient.openHomePage();
    }

    public String placeOrder(String sku, String quantity, String country) {
        var homePage = shopUiClient.openHomePage();
        var newOrderPage = homePage.clickNewOrder();

        newOrderPage.inputProductId(sku);
        newOrderPage.inputQuantity(quantity);
        newOrderPage.inputCountry(country);
        newOrderPage.clickPlaceOrder();

        return newOrderPage.extractOrderNumber();
    }

    public OrderHistoryPage viewOrderDetails(String orderNumber) {
        var homePage = shopUiClient.openHomePage();
        var orderHistoryPage = homePage.clickOrderHistory();

        orderHistoryPage.inputOrderNumber(orderNumber);
        orderHistoryPage.clickSearch();
        orderHistoryPage.waitForOrderDetails();

        return orderHistoryPage;
    }

    public void cancelOrder(String orderNumber) {
        var orderHistoryPage = viewOrderDetails(orderNumber);
        orderHistoryPage.clickCancelOrder();
    }

    public NewOrderPage attemptPlaceOrder(String sku, String quantity, String country) {
        var homePage = shopUiClient.openHomePage();
        var newOrderPage = homePage.clickNewOrder();

        newOrderPage.inputProductId(sku);
        newOrderPage.inputQuantity(quantity);
        newOrderPage.inputCountry(country);
        newOrderPage.clickPlaceOrder();

        return newOrderPage;
    }

    @Override
    public void close() {
        if (shopUiClient != null) {
            shopUiClient.close();
        }
    }
}

