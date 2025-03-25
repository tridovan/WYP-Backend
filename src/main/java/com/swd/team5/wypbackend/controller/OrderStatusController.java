package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.OrderStatusCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderStatusUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.OrderStatusResponse;
import com.swd.team5.wypbackend.service.OrderStatusService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-statuses")
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    @PostMapping("/create")
    @Operation(description = "tao order status http://localhost:8080/order-statuses/create")
    public ApiResponse<OrderStatusResponse> create(@RequestBody OrderStatusCreateRequest request) {
        return ApiResponse.<OrderStatusResponse>builder()
                .result(orderStatusService.create(request))
                .build();
    }

    @PutMapping("/update/{orderStatusId}")
    @Operation(description = "update order status http://localhost:8080/order-statuses//update/{orderStatusId}")
    public ApiResponse<OrderStatusResponse> update(@PathVariable String orderStatusId,
                                                   @RequestBody OrderStatusUpdateRequest request) {
        return ApiResponse.<OrderStatusResponse>builder()
                .result(orderStatusService.update(orderStatusId, request))
                .build();
    }

    @DeleteMapping("/delete/{orderStatusId}")
    @Operation(description = "xoa order status http://localhost:8080/order-statuses/delete/{orderStatusId}")
    public ApiResponse<Void> delete(@PathVariable String orderStatusId) {
        orderStatusService.delete(orderStatusId);
        return ApiResponse.<Void>builder()
                .message("Deleted successfully")
                .build();
    }

    @GetMapping
    @Operation(description = "lay tat ca order status http://localhost:8080/order-statuses")
    public ApiResponse<List<OrderStatusResponse>> getAll() {
        return ApiResponse.<List<OrderStatusResponse>>builder()
                .result(orderStatusService.getAll())
                .build();
    }
}
