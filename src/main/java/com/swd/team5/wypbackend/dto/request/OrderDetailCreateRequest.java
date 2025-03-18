package com.swd.team5.wypbackend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailCreateRequest {
    private String orderId;
    private String productId;
    private Integer quantity;
    private Boolean isCustomization;
    private Boolean isDeposit;
    private Integer price;
    private String status;
}
