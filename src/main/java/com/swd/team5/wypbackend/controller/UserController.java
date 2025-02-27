package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.UserCreateRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.UserResponse;
import com.swd.team5.wypbackend.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserSerivce userSerivce;

    @PostMapping
    public ApiResponse<UserResponse> create(@RequestBody UserCreateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userSerivce.create(request))
                .build();
    }


}
