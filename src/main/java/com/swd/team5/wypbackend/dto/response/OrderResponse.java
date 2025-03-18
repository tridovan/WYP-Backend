package com.swd.team5.wypbackend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponse {
    private String id;
    private String userId;
    private String status;
    private String note;
    private Integer totalPrice;
    private String addressId;
    private LocalDateTime createdAt;
}
