package com.swd.team5.wypbackend.dto.response;

import com.swd.team5.wypbackend.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDetailResponse {
    private String id;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Boolean isCustomization;
    private Boolean isDeposit;
    private Double price;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
