package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.CartCreateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.CartResponse;
import com.swd.team5.wypbackend.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    @Operation(description = "tao cart http://localhost:8080/carts/create")
    public ApiResponse<CartResponse> create(@RequestBody CartCreateRequest request) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.create(request))
                .build();
    }

    @GetMapping("/{cartId} http://localhost:8080/carts/1")
    @Operation(description = "lay gio hang bang cartId")
    public ApiResponse<CartResponse> getById(@PathVariable String cartId) {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.getById(cartId))
                .build();
    }

    @GetMapping("/user/{userId}")
    @Operation(description = "lay tat ca gio hang bang userId http://localhost:8080/carts/user/{user.id}")
    public ApiResponse<List<CartResponse>> getAllByUserId(@PathVariable String userId) {
        return ApiResponse.<List<CartResponse>>builder()
                .result(cartService.getAllByUserId(userId))
                .build();
    }

    @DeleteMapping("/delete/{cartId} http://localhost:8080/carts/delete/6")
    @Operation(description = "xoa gio hang")
    public ApiResponse<Void> delete(@PathVariable String cartId) {
        cartService.delete(cartId);
        return ApiResponse.<Void>builder()
                .message("Deleted successfully")
                .build();
    }
}
