package com.swd.team5.wypbackend.exception;

import com.swd.team5.wypbackend.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AppException extends RuntimeException{
    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
