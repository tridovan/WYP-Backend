package com.swd.team5.wypbackend.controller;

import com.nimbusds.jose.JOSEException;
import com.swd.team5.wypbackend.dto.request.AuthRequest;
import com.swd.team5.wypbackend.dto.request.KeyValidationRequest;
import com.swd.team5.wypbackend.dto.request.LogoutRequest;
import com.swd.team5.wypbackend.dto.request.RefreshTokenRequest;
import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.dto.response.AuthResponse;
import com.swd.team5.wypbackend.dto.response.AuthResponseDTO;
import com.swd.team5.wypbackend.dto.response.KeyValidationResponse;
import com.swd.team5.wypbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

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


}
