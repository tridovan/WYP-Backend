package com.swd.team5.wypbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Lob
    private String image;
    private String size;
    private Integer price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
