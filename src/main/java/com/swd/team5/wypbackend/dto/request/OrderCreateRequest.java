package com.swd.team5.wypbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.swd.team5.wypbackend.entity.Address;
import com.swd.team5.wypbackend.entity.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderCreateRequest {
    private Address address;
    private List<OrderDetailCreateRequest> orderList;
}
