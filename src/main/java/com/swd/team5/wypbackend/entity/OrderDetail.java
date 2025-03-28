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
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Chuyển sang UUID cho kiểu String
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private Boolean isCustomization;
    private Boolean isDeposit;
    private Integer price;
    @Enumerated(EnumType.STRING) // Lưu dưới dạng chuỗi trong DB
    private OrderDetailStatus status;
    private LocalDateTime createdAt = LocalDateTime.now();
}
