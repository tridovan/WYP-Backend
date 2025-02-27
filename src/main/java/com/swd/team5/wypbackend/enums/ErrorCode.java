package com.swd.team5.wypbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_ROLE(999, "Invalid role", HttpStatus.BAD_REQUEST),
    INVALID_ROLE_AT_SERVER(999, "Invalid role", HttpStatus.INTERNAL_SERVER_ERROR)
;
    private int code;
    private String message;
    private HttpStatus status;
}
