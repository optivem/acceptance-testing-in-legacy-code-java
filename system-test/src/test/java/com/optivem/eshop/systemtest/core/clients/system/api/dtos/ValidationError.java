package com.optivem.eshop.systemtest.core.clients.system.api.dtos;

import lombok.Data;

@Data
public class ValidationError {
    private String field;
    private String message;
    private String code;
    private Object rejectedValue;
}

