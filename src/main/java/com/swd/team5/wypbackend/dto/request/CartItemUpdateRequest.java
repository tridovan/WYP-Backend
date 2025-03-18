package com.swd.team5.wypbackend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemUpdateRequest {
    private Boolean isCustomization;
    private String customizationId;
    private Integer quantity;
}
