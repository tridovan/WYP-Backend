package com.swd.team5.wypbackend.dto.response;

import com.swd.team5.wypbackend.entity.Order;
import com.swd.team5.wypbackend.entity.Product;
import com.swd.team5.wypbackend.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDetailResponse {
    private Long productId;
    private Integer quantity;
    private Double price;
}
