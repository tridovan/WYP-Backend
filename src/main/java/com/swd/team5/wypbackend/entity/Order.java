package com.swd.team5.wypbackend.entity;

import com.swd.team5.wypbackend.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String note;

    private Double totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;


    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;
}
