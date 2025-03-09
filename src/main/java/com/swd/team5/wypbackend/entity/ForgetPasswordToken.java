package com.swd.team5.wypbackend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ForgetPasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String email;

    private LocalDateTime expireTime = LocalDateTime.now().plusMinutes(10);

    public void setEmail(String email) {
        this.email = email;
    }
}
