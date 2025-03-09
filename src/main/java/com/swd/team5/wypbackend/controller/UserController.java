package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.UserCreateRequest;
import com.swd.team5.wypbackend.dto.request.UserUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.UserResponse;
import com.swd.team5.wypbackend.service.UserSerivce;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserSerivce userSerivce;

    @PostMapping
    public ApiResponse<UserResponse> create(@Valid @RequestBody UserCreateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userSerivce.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAll(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userSerivce.getAll())
                .build();
    }

    @GetMapping("/inactive")
    public ApiResponse<List<UserResponse>> getAllInactive(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userSerivce.getAllInactive())
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> update(@Valid @PathVariable String userId, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userSerivce.update(userId, request))
                .build();
    }
    @DeleteMapping("/{userId}")
    public ApiResponse<Void> delete(@PathVariable String userId){
        return ApiResponse.<Void>builder()
                .message(userSerivce.delete(userId))
                .build();
    }
    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser (@PathVariable String userId){
        return ApiResponse.<UserResponse>builder()
                .result(userSerivce.getUser(userId))
                .build();
    }

    @GetMapping("/myinfor")
    ApiResponse<UserResponse>getMyInfor(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.err.println(username);
        return ApiResponse.<UserResponse>builder()
                .result(userSerivce.getUserByUsername(username))
                .build();

    }
}
