package com.swd.team5.wypbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double discountPercent;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
