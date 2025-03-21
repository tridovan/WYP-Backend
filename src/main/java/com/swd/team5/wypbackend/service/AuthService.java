package com.swd.team5.wypbackend.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.swd.team5.wypbackend.dto.request.AuthRequest;
import com.swd.team5.wypbackend.dto.request.KeyValidationRequest;
import com.swd.team5.wypbackend.dto.request.LogoutRequest;
import com.swd.team5.wypbackend.dto.request.RefreshTokenRequest;
import com.swd.team5.wypbackend.dto.response.AuthResponse;
import com.swd.team5.wypbackend.dto.response.AuthResponseDTO;
import com.swd.team5.wypbackend.dto.response.KeyValidationResponse;
import com.swd.team5.wypbackend.entity.ForgetPasswordToken;
import com.swd.team5.wypbackend.entity.InvalidatedToken;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.repository.ForgotPasswordRepository;
import com.swd.team5.wypbackend.repository.InvalidateTokenRepository;
import com.swd.team5.wypbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private InvalidateTokenRepository invalidateTokenRepository;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    @Autowired
    private EmailService emailService;

    public AuthResponse authendicate(AuthRequest request){

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        AuthResponse response = new AuthResponse();
        response.setAuth(false);

        if(bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())){
            response.setAuth(true);
            response.setToken(jwtService.generateToken(user));
            response.setRole(user.getRole().getName());
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


    public String logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            //true bởi vì nếu để false có nghĩa là token chưa được lưu xuống blacklist mặc dù đã logout nhưng vẫn còn hiệu lực
            //và có thể lấy token đó để refresh token mới
            SignedJWT signedJWT = jwtService.validateToken(request.getToken(), true);
            String jit = signedJWT.getJWTClaimsSet().getJWTID();
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            InvalidatedToken invalidatedToken = new InvalidatedToken();
            invalidatedToken.setId(jit);
            invalidatedToken.setExpiryTime(expiryTime);
            invalidateTokenRepository.save(invalidatedToken);
            return "Logout successfully";
        }catch (AppException appException){
            System.err.println("Token already expired form AUTHSERVICE");
            return "Token already expired";
        }
    }

    public AuthResponseDTO refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        //xem cái token này còn hiệu lực không
        SignedJWT signedJWT = jwtService.validateToken(request.getToken(), true);
        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        //logout
        InvalidatedToken invalidatedToken = new InvalidatedToken();
        invalidatedToken.setId(jit);
        invalidatedToken.setExpiryTime(expiryTime);

        AuthResponseDTO response = new AuthResponseDTO();
        User user = userRepository.findByUsername(signedJWT.getJWTClaimsSet().getSubject())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        response.setAutheticated(true);
        response.setToken(jwtService.generateToken(user));
        return response;



    }

    @Transactional
    public String createForgotPasswordToken(String email) {
        userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.INVALID_EMAIL));
        forgotPasswordRepository.removeAllByEmail(email);
        ForgetPasswordToken forgetPasswordToken = new ForgetPasswordToken();
        forgetPasswordToken.setEmail(email);
        String id = forgotPasswordRepository.save(forgetPasswordToken).getId();

        emailService.sendPasswordResetEmail(email, id);
        return "Password reset instructions have been sent to " + email;
    }
}
