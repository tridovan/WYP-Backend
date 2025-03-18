package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.OrderDetailCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderDetailUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.OrderDetailResponse;
import com.swd.team5.wypbackend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public ApiResponse<OrderDetailResponse> create(@RequestBody OrderDetailCreateRequest request) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.create(request))
                .build();
    }

    @PutMapping("/{orderDetailId}")
    public ApiResponse<OrderDetailResponse> update(@PathVariable String orderDetailId,
                                                   @RequestBody OrderDetailUpdateRequest request) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.update(orderDetailId, request))
                .build();
    }

    @DeleteMapping("/{orderDetailId}")
    public ApiResponse<Void> delete(@PathVariable String orderDetailId) {
        orderDetailService.delete(orderDetailId);
        return ApiResponse.<Void>builder()
                .message("Deleted successfully")
                .build();
    }

    @GetMapping("/order/{orderId}")
    public ApiResponse<List<OrderDetailResponse>> getAllByOrderId(@PathVariable String orderId) {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getAllByOrderId(orderId))
                .build();
    }
}
