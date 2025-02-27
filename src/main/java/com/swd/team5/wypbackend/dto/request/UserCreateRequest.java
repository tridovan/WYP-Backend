package com.swd.team5.wypbackend.dto.request;

import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Builder
public class UserCreateRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
