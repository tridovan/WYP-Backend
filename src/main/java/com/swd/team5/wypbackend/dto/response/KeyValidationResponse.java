package com.swd.team5.wypbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class KeyValidationResponse {
    private boolean valid;
}
