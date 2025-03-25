package com.swd.team5.wypbackend.controller;

import com.nimbusds.jose.JOSEException;
import com.swd.team5.wypbackend.dto.request.*;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.AuthResponse;
import com.swd.team5.wypbackend.dto.response.AuthResponseDTO;
import com.swd.team5.wypbackend.dto.response.KeyValidationResponse;
import com.swd.team5.wypbackend.service.AuthService;
import com.swd.team5.wypbackend.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserSerivce userSerivce;

    @PostMapping("/token")
    public ApiResponse<AuthResponse> token(@RequestBody AuthRequest request){
        return ApiResponse.<AuthResponse>builder()
                .result(authService.authenticate(request))
                .build();

    }
    @PostMapping("/introspect")
    public ApiResponse<KeyValidationResponse> authenticate(@RequestBody KeyValidationRequest token) throws ParseException, JOSEException {

        return ApiResponse.<KeyValidationResponse>builder()
                .result(authService.verifyToken(token))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        return ApiResponse.<Void>builder()
                .message(authService.logout(request))
                .build();
    }

    @PostMapping("/refresh-token")
    public ApiResponse<AuthResponseDTO> refreshToken(@RequestBody RefreshTokenRequest request) throws ParseException, JOSEException {
        return ApiResponse.<AuthResponseDTO>builder()
                .result(authService.refreshToken(request))
                .build();
    }

    @GetMapping("/forgot-password")
    public ApiResponse<Void> forgotPassword(@RequestParam String email){
        return ApiResponse.<Void>builder()
                .message(authService.createForgotPasswordToken(email))
                .build();
    }

    @PostMapping("/reset-password/{email}")
    ApiResponse<Void> resetPassword(@PathVariable String email,@RequestBody ResetPasswordRequest request){
        return ApiResponse.<Void>builder()
                .message(userSerivce.resetPassword(email, request))
                .build();
    }


}
