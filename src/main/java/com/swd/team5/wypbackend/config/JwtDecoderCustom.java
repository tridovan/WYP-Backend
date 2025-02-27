package com.swd.team5.wypbackend.config;

import com.nimbusds.jose.JOSEException;
import com.swd.team5.wypbackend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.text.ParseException;
import java.util.Objects;

@Component
public class JwtDecoderCustom implements JwtDecoder {

    @Autowired
    private JwtService jwtService;

    private NimbusJwtDecoder nimbusJwtDecoder;

    private SecretKey secretKey;

    @Override
    public Jwt decode(String token) throws JwtException {
        secretKey = jwtService.getSecretKey();
        try{
            jwtService.validateToken(token, false);
        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }
        if(Objects.isNull(nimbusJwtDecoder)){
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKey)
                    .macAlgorithm(MacAlgorithm.HS256)
                    .build();
        }
        return nimbusJwtDecoder.decode(token);

    }
}
