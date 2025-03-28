package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.OrderDetailCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderDetailUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.OrderDetailResponse;
import com.swd.team5.wypbackend.entity.OrderDetailStatus;
import com.swd.team5.wypbackend.service.OrderDetailService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/create")
    @Operation(description = "tao order details http://localhost:8080/order-details/create")
    public ApiResponse<OrderDetailResponse> create(@RequestBody OrderDetailCreateRequest request) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.create(request))
                .build();
    }

    @PutMapping("/update/{orderDetailId}")
    @Operation(description = "update order details http://localhost:8080/order-details/update/{orderDetailId}")
    public ApiResponse<OrderDetailResponse> update(@PathVariable String orderDetailId,
                                                   @RequestBody OrderDetailUpdateRequest request) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.update(orderDetailId, request))
                .build();
    }

    @DeleteMapping("/delete/{orderDetailId}")
    @Operation(description = "xoa order details http://localhost:8080/order-details/delete/{orderDetailId}")
    public ApiResponse<Void> delete(@PathVariable String orderDetailId) {
        orderDetailService.delete(orderDetailId);
        return ApiResponse.<Void>builder()
                .message("Deleted successfully")
                .build();
    }

    @GetMapping("/order/{orderId}")
    @Operation(description = "lay tat ca order details tu order id " +
            "http://localhost:8080/order-details/order/{orderId}")
    public ApiResponse<List<OrderDetailResponse>> getAllByOrderId(@PathVariable String orderId) {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getAllByOrderId(orderId))
                .build();
    }
    @PutMapping("/confirm/{orderDetailId}")
    @Operation(description = "xac nhan order details http://localhost:8080/order-details/confirm/{orderDetailId}")
    public ApiResponse<OrderDetailResponse> confirmOrderDetail(@PathVariable String orderDetailId) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.updateStatus(orderDetailId, OrderDetailStatus.CONFIRMED))
                .build();
    }
    
    @PutMapping("/cancel/{orderDetailId}")
    @Operation(description = "huy order details http://localhost:8080/order-details/cancel/{orderDetailId}")
    public ApiResponse<OrderDetailResponse> cancelOrderDetail(@PathVariable String orderDetailId) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.updateStatus(orderDetailId, OrderDetailStatus.CANCELLED))
                .build();
    }
//    @PutMapping("/status/{orderDetailId}")
//    @Operation(description = "cap nhat trang thai order details http://localhost:8080/order-details/status/{orderDetailId}")
//    public ApiResponse<OrderDetailResponse> updateOrderDetailStatus(
//            @PathVariable String orderDetailId,
//            @RequestParam OrderDetailStatus status) {
//        return ApiResponse.<OrderDetailResponse>builder()
//                .result(orderDetailService.updateStatus(orderDetailId, status))
//                .build();
//    }

}
