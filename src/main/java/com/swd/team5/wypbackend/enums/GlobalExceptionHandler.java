package com.swd.team5.wypbackend.enums;

import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus())
                            .body(ApiResponse
                                    .builder()
                                    .code(errorCode.getCode())
                                    .message(errorCode.getMessage())
                                    .build());

    }
}
