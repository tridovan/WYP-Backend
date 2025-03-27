package com.swd.team5.wypbackend.entity;

import com.swd.team5.wypbackend.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Chuyển sang UUID cho kiểu String
    private String id;

    @ManyToOne
    private Product product;

    private Integer quantity;

    private Integer price;

    private LocalDateTime createdAt = LocalDateTime.now();

    public OrderDetail(Product product, Integer quantity, Integer price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
