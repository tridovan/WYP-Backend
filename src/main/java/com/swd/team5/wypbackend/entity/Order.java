package com.swd.team5.wypbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Chuyển sang UUID cho kiểu String
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING) // Lưu dưới dạng chuỗi trong DB
    private OrderDetailStatus status;
    private String note;
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    private LocalDateTime createdAt = LocalDateTime.now();
    // Thêm danh sách OrderDetail
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;
}
