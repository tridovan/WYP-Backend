package com.swd.team5.wypbackend.controller;

import com.swd.team5.wypbackend.dto.request.AuthRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.AuthResponse;
import com.swd.team5.wypbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/token")
    public ApiResponse<AuthResponse> token(@RequestBody AuthRequest request){
        return ApiResponse.<AuthResponse>builder()
                .result(authService.authendicate(request))
                .build();

    }


}
