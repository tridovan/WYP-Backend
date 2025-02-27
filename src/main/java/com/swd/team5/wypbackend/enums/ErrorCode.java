package com.swd.team5.wypbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_KEY(800, "Error key is invalid", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ROLE(999, "Invalid role", HttpStatus.BAD_REQUEST),
    INVALID_ROLE_AT_SERVER(999, "Invalid role", HttpStatus.INTERNAL_SERVER_ERROR),
    EXISTED_USERNAME(998,"Existed username, choose another username 😈" ,HttpStatus.BAD_REQUEST );


    private int code;
    private String message;
    private HttpStatus status;
}
