package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.AuthResponse;
import com.swd.team5.wypbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ApiResponse<?> create(@RequestBody OrderCreateRequest request){
        return ApiResponse.builder()
                .result(orderService.create(request))
                .build();

    }
}
