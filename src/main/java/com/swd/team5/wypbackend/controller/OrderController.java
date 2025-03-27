package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderDetailCreateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.AuthResponse;
import com.swd.team5.wypbackend.entity.Address;
import com.swd.team5.wypbackend.entity.Order;
import com.swd.team5.wypbackend.entity.OrderDetail;
import com.swd.team5.wypbackend.service.OrderService;
import com.swd.team5.wypbackend.service.UserSerivce;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final UserSerivce userSerivce;

    @PostMapping("/create")
    public ApiResponse<?> create(@RequestBody OrderCreateRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ApiResponse.builder()
                .result(orderService.create(request, userSerivce.findUserUsername(authentication.getName())))
                .build();
    }

    @Operation(description = "lựa cái nào hợp lý thì truyền về nha, làm vội nên chỉ thế thôi     PENDING,       // Đang chờ xử lý\n" +
            "    CONFIRMED,     // Đã xác nhận\n" +
            "    PROCESSING,    // Đang xử lý\n" +
            "    SHIPPED,       // Đã vận chuyển\n" +
            "    DELIVERED,     // Đã giao hàng\n" +
            "    CANCELLED,     // Đã hủy\n" +
            "    RETURNED,      // Đã trả hàng\n" +
            "    REFUNDED,      // Đã hoàn tiền\n" +
            "    FAILED  ")
    @PutMapping("/update-status/{orderId}")
    public ApiResponse<?> update(@PathVariable String orderId, String status){
        return ApiResponse.builder()
                .result(orderService.update(orderId, status))
                .build();
    }

//    @PostMapping("/check-out/{orderId}")
//    public ApiResponse<?> checkout(@PathVariable String orderId, String status){
//        return ApiResponse.builder()
//                .result(orderService.update(orderId, status))
//                .build();
//    }
}
