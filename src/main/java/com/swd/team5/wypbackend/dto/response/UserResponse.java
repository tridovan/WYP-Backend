package com.swd.team5.wypbackend.dto.response;

import com.swd.team5.wypbackend.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private Role role;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate createAt = LocalDate.now();
}
