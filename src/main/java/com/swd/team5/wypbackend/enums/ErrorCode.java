package com.swd.team5.wypbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_KEY(800, "Error key is invalid 😏😏😏", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ROLE(999, "Invalid role 😏😏😏", HttpStatus.BAD_REQUEST),
    INVALID_ROLE_AT_SERVER(999, "Invalid role 😏😏😏", HttpStatus.INTERNAL_SERVER_ERROR),
    EXISTED_USERNAME(998,"Existed username, choose another username 😏😏😏", HttpStatus.BAD_REQUEST ),
    USER_NOT_EXISTED(997, "User not found 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_USERNAME(996, "Username must be at least 3 characters 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_PASSWORD(995, "Password must be at least 8 characters 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_EMAIL(994, "Invalid email 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_PHONE(993, "The phone number must have 10 digits 😏😏😏", HttpStatus.BAD_REQUEST ),
    UNCATEGOZIZED(992, "Unexpected error in server 😏😏😏", HttpStatus.INTERNAL_SERVER_ERROR ),
    EXISTED_PERMISSION(991, "Existed permission 😏😏😏", HttpStatus.BAD_REQUEST ),
    INVALID_PERMISSION_NAME(990, "Invalid permission name 😏😏😏", HttpStatus.BAD_REQUEST )


    ;


    private int code;
    private String message;
    private HttpStatus status;
}
