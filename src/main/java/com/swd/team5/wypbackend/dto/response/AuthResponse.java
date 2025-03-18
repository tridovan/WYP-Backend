package com.swd.team5.wypbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private boolean isAuth;
    private String token;
    private String role;
}
