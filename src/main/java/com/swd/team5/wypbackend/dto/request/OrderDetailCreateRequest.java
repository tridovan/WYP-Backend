    package com.swd.team5.wypbackend.dto.request;

    import com.swd.team5.wypbackend.entity.OrderDetailStatus;
    import com.swd.team5.wypbackend.entity.OrderStatus;
    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    public class OrderDetailCreateRequest {
        private String orderId;
        private String productId;
        private Integer quantity;
        private Boolean isCustomization;
        private Boolean isDeposit;
        private Integer price;
    }
