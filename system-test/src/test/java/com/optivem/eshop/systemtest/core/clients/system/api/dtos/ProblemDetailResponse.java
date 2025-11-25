package com.optivem.eshop.systemtest.core.clients.system.api.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ProblemDetailResponse {
    private String type;
    private String title;
    private Integer status;
    private String detail;
    private String instance;
    private String timestamp;
    private List<ValidationError> errors;
}

