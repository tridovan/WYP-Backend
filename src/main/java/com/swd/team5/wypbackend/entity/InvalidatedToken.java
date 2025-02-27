package com.swd.team5.wypbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class InvalidatedToken {
    @Id
    private String id;
    private Date expiryTime;
}
