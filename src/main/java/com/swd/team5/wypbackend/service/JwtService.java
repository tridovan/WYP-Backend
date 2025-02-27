package com.swd.team5.wypbackend.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JwtService {

    private SecretKey secretKey;


    @Value("${jwt.valid-duration}")
    private long VALID_DURATION;

    @Value("${jwt.refreshable-duration}")
    private long REFRESHABLE_DURATION;


    public JwtService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            this.secretKey = keyGenerator.generateKey();
            System.out.println("👙" + Base64.getEncoder().encodeToString(secretKey.getEncoded()) + "👙");
        } catch (NoSuchAlgorithmException e) {
            throw new AppException(ErrorCode.INVALID_SECRET_KEY);
        }

    }

    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("Tri chu ai ")
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(new Date(System.currentTimeMillis() + 1000 * VALID_DURATION))
                .claim("hacker cho 🐕", "thick hack hok danh cmmh")
                .claim("scope", buiderScope(user))
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try{
            //key
            jwsObject.sign(new MACSigner(secretKey));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            System.err.println("cannot create token at JwtService");
            throw new AppException(ErrorCode.JWT_ERROR);
        }


    }

    private String buiderScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(Objects.nonNull(user.getRole())){
            stringJoiner.add("ROLE_" + user.getRole().getName());
            user.getRole().getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
        }
        return stringJoiner.toString();
    }
}
