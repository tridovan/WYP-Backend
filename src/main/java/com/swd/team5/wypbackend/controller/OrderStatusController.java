package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.OrderStatusCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderStatusUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.OrderStatusResponse;
import com.swd.team5.wypbackend.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-statuses")
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    @PostMapping
    public ApiResponse<OrderStatusResponse> create(@RequestBody OrderStatusCreateRequest request) {
        return ApiResponse.<OrderStatusResponse>builder()
                .result(orderStatusService.create(request))
                .build();
    }

    @PutMapping("/{orderStatusId}")
    public ApiResponse<OrderStatusResponse> update(@PathVariable String orderStatusId,
                                                   @RequestBody OrderStatusUpdateRequest request) {
        return ApiResponse.<OrderStatusResponse>builder()
                .result(orderStatusService.update(orderStatusId, request))
                .build();
    }

    @DeleteMapping("/{orderStatusId}")
    public ApiResponse<Void> delete(@PathVariable String orderStatusId) {
        orderStatusService.delete(orderStatusId);
        return ApiResponse.<Void>builder()
                .message("Deleted successfully")
                .build();
    }

    @GetMapping
    public ApiResponse<List<OrderStatusResponse>> getAll() {
        return ApiResponse.<List<OrderStatusResponse>>builder()
                .result(orderStatusService.getAll())
                .build();
    }
}
