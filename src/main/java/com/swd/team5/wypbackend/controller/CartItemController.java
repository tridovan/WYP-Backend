package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.CartItemCreateRequest;
import com.swd.team5.wypbackend.dto.request.CartItemUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.CartItemResponse;
import com.swd.team5.wypbackend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public ApiResponse<CartItemResponse> create(@RequestBody CartItemCreateRequest request) {
        return ApiResponse.<CartItemResponse>builder()
                .result(cartItemService.create(request))
                .build();
    }

    @PutMapping("/update/{cartItemId}")
    public ApiResponse<CartItemResponse> update(@PathVariable String cartItemId,
                                                @RequestBody CartItemUpdateRequest request) {
        return ApiResponse.<CartItemResponse>builder()
                .result(cartItemService.update(cartItemId, request))
                .build();
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ApiResponse<Void> delete(@PathVariable String cartItemId) {
        cartItemService.delete(cartItemId);
        return ApiResponse.<Void>builder()
                .message("Deleted successfully")
                .build();
    }

    @GetMapping("/cart/{cartId}")
    public ApiResponse<List<CartItemResponse>> getAllByCartId(@PathVariable String cartId) {
        return ApiResponse.<List<CartItemResponse>>builder()
                .result(cartItemService.getAllByCartId(cartId))
                .build();
    }
}
