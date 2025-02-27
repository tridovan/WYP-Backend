package com.swd.team5.wypbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.swd.team5.wypbackend.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String id;
    private String username;
    private String role;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate createAt = LocalDate.now();
}
