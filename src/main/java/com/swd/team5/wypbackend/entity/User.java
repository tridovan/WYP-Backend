package com.swd.team5.wypbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "name")
    private Role role;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isActive;
    private LocalDate createAt = LocalDate.now();

}
