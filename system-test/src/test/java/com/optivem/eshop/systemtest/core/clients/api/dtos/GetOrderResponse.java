package com.optivem.eshop.systemtest.core.clients.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOrderResponse {
    private String orderNumber;
    private String sku;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal originalPrice;
    private String status;
    private String country;
}

