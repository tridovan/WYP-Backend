package com.swd.team5.wypbackend.dto.response;

import com.swd.team5.wypbackend.entity.Address;

import com.swd.team5.wypbackend.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;


@Getter
@Setter
public class OrderResponse {
    private String id;
    private String userId;
    private OrderStatus status;
    private String note;
    private Double totalPrice;
    private Address address;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
