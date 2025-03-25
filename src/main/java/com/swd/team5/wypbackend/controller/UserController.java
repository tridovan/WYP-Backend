package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.UserCreateRequest;
import com.swd.team5.wypbackend.dto.request.UserUpdateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.UserResponse;
import com.swd.team5.wypbackend.service.UserSerivce;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(description = "create new user account")
    @PostMapping
    public ApiResponse<UserResponse> create(@Valid @RequestBody UserCreateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userSerivce.create(request))
                .build();
    }

    @Operation(description = "get all user account (no pagination & no sort)")
    @GetMapping
    public ApiResponse<List<UserResponse>> getAll(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userSerivce.getAll())
                .build();
    }

    @Operation(description = "get all inactive account (no pagination & no sort)")
    @GetMapping("/inactive")
    public ApiResponse<List<UserResponse>> getAllInactive(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userSerivce.getAllInactive())
                .build();
    }

    @Operation(description = "update user")
    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> update(@Valid @PathVariable String userId, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userSerivce.update(userId, request))
                .build();
    }
    @Operation(description = "delete user (shallow delete)")
    @DeleteMapping("/{userId}")
    public ApiResponse<Void> delete(@PathVariable String userId){
        return ApiResponse.<Void>builder()
                .message(userSerivce.delete(userId))
                .build();
    }
    @Operation(description = "get user by userid")
    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser (@PathVariable String userId){
        return ApiResponse.<UserResponse>builder()
                .result(userSerivce.getUser(userId))
                .build();
    }

    @Operation(description = "get information of current user")
    @GetMapping("/myinfor")
    ApiResponse<UserResponse>getMyInfor(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.err.println(username);
        return ApiResponse.<UserResponse>builder()
                .result(userSerivce.getUserByUsername(username))
                .build();

    }

    @Operation(description = "get all user account sort by multiple " +
            "cấu trúc các field sort field:asc " +
            "http://localhost:8080/users/list?pageNo=1&pageSize=10&sorts=id:desc&sorts=username:asc ")
    @GetMapping("/list")  public ApiResponse<?> getAllUser(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                                             @RequestParam(defaultValue = "20", required = false) int pageSize,
                                                             @RequestParam(required = false) String... sorts){
        return ApiResponse.builder()
                .result(userSerivce.getAllUserSortBy(pageNo, pageSize, sorts))
                .build();
    }
}
