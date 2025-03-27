package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.OrderResponse;
import com.swd.team5.wypbackend.entity.OrderDetailStatus;
import com.swd.team5.wypbackend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    @Operation(description = "tao 1 order http://localhost:8080/orders/create")
    public ApiResponse<OrderResponse> create(@RequestBody OrderCreateRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.create(request))
                .build();
    }
    @PutMapping("/{orderId}/status")
    @Operation(description = "Cập nhật trạng thái order http://localhost:8080/orders/{orderId}/status")
    public ApiResponse<OrderResponse> updateStatus(
            @PathVariable String orderId,
            @RequestBody OrderUpdateRequest request) {

        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateStatus(orderId, request))
                .build();
    }
}
