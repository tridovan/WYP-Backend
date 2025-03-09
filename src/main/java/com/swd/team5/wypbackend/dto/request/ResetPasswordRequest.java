package com.swd.team5.wypbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResetPasswordRequest {
    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;
    @NotBlank
    private String token;
}
