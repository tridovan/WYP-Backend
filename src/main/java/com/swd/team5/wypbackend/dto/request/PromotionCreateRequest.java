package com.swd.team5.wypbackend.dto.request;

    import jakarta.validation.constraints.*;
    import lombok.*;
    import java.time.LocalDateTime;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class PromotionCreateRequest {
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 100)
        private String name;

        private String description;

        @NotNull(message = "Discount percent is required")
        @Min(0) @Max(100)
        private Double discountPercent;

        @NotNull(message = "Start date is required")
        private LocalDateTime startDate;

        @NotNull(message = "End date is required")
        private LocalDateTime endDate;

        @NotNull(message = "Product ID is required")
        private Long productId;
    }