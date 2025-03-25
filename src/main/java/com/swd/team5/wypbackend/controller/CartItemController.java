package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.CartItemCreateRequest;
import com.swd.team5.wypbackend.dto.request.CartItemUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.CartItemResponse;
import com.swd.team5.wypbackend.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/create")
    @Operation(description = "tao cart items http://localhost:8080/cart-items/create")
    public ApiResponse<CartItemResponse> create(@RequestBody CartItemCreateRequest request) {
        return ApiResponse.<CartItemResponse>builder()
                .result(cartItemService.create(request))
                .build();
    }

    @PutMapping("/update/{cartItemId}")
    @Operation(description = "update thong tin cua cart items http://localhost:8080/cart-items/update/{cartItemId}")
    public ApiResponse<CartItemResponse> update(@PathVariable String cartItemId,
                                                @RequestBody CartItemUpdateRequest request) {
        return ApiResponse.<CartItemResponse>builder()
                .result(cartItemService.update(cartItemId, request))
                .build();
    }

    @DeleteMapping("/delete/{cartItemId}")
    @Operation(description = "xoa cart items http://localhost:8080/cart-items/delete/{cartItemId}")
    public ApiResponse<Void> delete(@PathVariable String cartItemId) {
        cartItemService.delete(cartItemId);
        return ApiResponse.<Void>builder()
                .message("Deleted successfully")
                .build();
    }

    @GetMapping("/cart/{cartId}")
    @Operation(description = "show ra tat ca cart items bang cartId http://localhost:8080/cart-items/cart/{cartId}")
    public ApiResponse<List<CartItemResponse>> getAllByCartId(@PathVariable String cartId) {
        return ApiResponse.<List<CartItemResponse>>builder()
                .result(cartItemService.getAllByCartId(cartId))
                .build();
    }
}
