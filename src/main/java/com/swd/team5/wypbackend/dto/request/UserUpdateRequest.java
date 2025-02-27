package com.swd.team5.wypbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateRequest {
    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;
    @Email(message = "INVALID_EMAIL")
    private String email;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^\\d{10}$", message = "INVALID_PHONE")
    private String phoneNumber;
}
