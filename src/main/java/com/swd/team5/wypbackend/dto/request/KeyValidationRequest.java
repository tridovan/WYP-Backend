package com.swd.team5.wypbackend.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KeyValidationRequest {
    private String token;
}
