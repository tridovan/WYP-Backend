package com.swd.team5.wypbackend.dto.response;

import lombok.*;

@ToString
@Getter
@Builder
public class ApiResponse<T> {
    private int code = 1000;
    private String message;
    private T result;
}
