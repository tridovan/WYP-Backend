package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.CartCreateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.CartResponse;
import com.swd.team5.wypbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ApiResponse<CartResponse> create(@RequestBody CartCreateRequest request) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.create(request))
                .build();
    }

    @GetMapping("/{cartId}")
    public ApiResponse<CartResponse> getById(@PathVariable String cartId) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.getById(cartId))
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<CartResponse>> getAllByUserId(@PathVariable String userId) {
        return ApiResponse.<List<CartResponse>>builder()
                .result(cartService.getAllByUserId(userId))
                .build();
    }

    @DeleteMapping("/{cartId}")
    public ApiResponse<Void> delete(@PathVariable String cartId) {
        cartService.delete(cartId);
        return ApiResponse.<Void>builder()
                .message("Deleted successfully")
                .build();
    }
}
