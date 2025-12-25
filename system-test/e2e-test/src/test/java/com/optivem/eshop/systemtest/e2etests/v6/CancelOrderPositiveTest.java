package com.optivem.eshop.systemtest.e2etests.v6;

import com.optivem.eshop.systemtest.base.v6.BaseScenarioDslTest;
import com.optivem.eshop.systemtest.core.shop.ChannelType;
import com.optivem.eshop.systemtest.core.shop.client.dtos.enums.OrderStatus;
import com.optivem.testing.channels.Channel;
import org.junit.jupiter.api.TestTemplate;

import static com.optivem.eshop.systemtest.e2etests.commons.constants.Defaults.ORDER_NUMBER;
import static com.optivem.eshop.systemtest.e2etests.commons.constants.Defaults.SKU;

public class CancelOrderPositiveTest extends BaseScenarioDslTest {

    @TestTemplate
    @Channel({ChannelType.UI, ChannelType.API})
    void shouldCancelOrder() {
        scenario
                .given()
                .product()
                .withSku(SKU)
                .and().order()
                .withOrderNumber(ORDER_NUMBER)
                .withSku(SKU)
                .when()
                .cancelOrder()
                .withOrderNumber(ORDER_NUMBER)
                .then()
                .shouldSucceed()
                .and()
                .order(ORDER_NUMBER)
                .hasSku(SKU)
                .hasStatus(OrderStatus.CANCELLED);
    }
}

