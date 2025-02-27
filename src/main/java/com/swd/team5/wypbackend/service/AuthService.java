package com.swd.team5.wypbackend.service;

import com.nimbusds.jose.JOSEException;
import com.swd.team5.wypbackend.dto.request.AuthRequest;
import com.swd.team5.wypbackend.dto.request.KeyValidationRequest;
import com.swd.team5.wypbackend.dto.response.AuthResponse;
import com.swd.team5.wypbackend.dto.response.KeyValidationResponse;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse authendicate(AuthRequest request){

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        AuthResponse response = new AuthResponse();
        response.setAuth(false);

        if(bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())){
            response.setAuth(true);
            response.setToken(jwtService.generateToken(user));
        }else{
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return response;
    }

    public KeyValidationResponse verifyToken(KeyValidationRequest request) throws ParseException, JOSEException {
        String token = request.getToken();
        //nếu token không hợp lệ thì đã quăng exception bên kia
        //có nghĩa qua được bên này tức là nó hợp lệ
        boolean isValid = true;
        try {
            jwtService.validateToken(token, false);
        }
        catch (AppException e){
            isValid = false;
        }
        return KeyValidationResponse.builder()
                .valid(isValid)
                .build();
    }


}
