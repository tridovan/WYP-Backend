package com.swd.team5.wypbackend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateRequest {
    private String userId;
    private String status;
    private String note;
    private Integer totalPrice;
    private String addressId;
}
