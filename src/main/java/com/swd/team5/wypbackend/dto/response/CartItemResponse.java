package com.swd.team5.wypbackend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CartItemResponse {
    private String id;
    private String cartId;
    private String productId;
    private Boolean isCustomization;
    private String customizationId;
    private Integer quantity;
    private LocalDateTime addedAt;
}
