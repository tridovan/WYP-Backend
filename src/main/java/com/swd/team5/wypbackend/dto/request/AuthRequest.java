package com.swd.team5.wypbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    @NotBlank(message = "INVALID_USER_AND_PASSWORD")
    private String username;
    @NotBlank(message = "INVALID_USER_AND_PASSWORD")
    private String password;
}
