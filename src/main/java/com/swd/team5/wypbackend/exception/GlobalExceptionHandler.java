package com.swd.team5.wypbackend.exception;

import com.swd.team5.wypbackend.dto.response.ApiResponse;
import com.swd.team5.wypbackend.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        System.err.println(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatus())
                            .body(ApiResponse
                                    .builder()
                                    .code(errorCode.getCode())
                                    .message(errorCode.getMessage())
                                    .build());

    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
        ErrorCode errorCode = ErrorCode.UNCATEGOZIZED;

        System.err.println(exception.getMessage());
        
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse
                        .builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handlingValidateException(MethodArgumentNotValidException exception){
        String errorKey = exception.getFieldError().getDefaultMessage();

        System.err.println(ErrorCode.valueOf(errorKey).getMessage());

        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try{
            errorCode = ErrorCode.valueOf(errorKey);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid error key");
        }

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse
                        .builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());

    }
}
