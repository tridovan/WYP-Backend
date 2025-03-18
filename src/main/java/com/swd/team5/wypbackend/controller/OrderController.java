package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.OrderResponse;
import com.swd.team5.wypbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> create(@RequestBody OrderCreateRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.create(request))
                .build();
    }
}
