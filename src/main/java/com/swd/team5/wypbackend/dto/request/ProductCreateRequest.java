package com.swd.team5.wypbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCreateRequest {

    @NotNull(message = "INVALID_NAME")
    @Size(min = 3, message = "INVALID_NAME")
    private String name;

    private String description;

    private String image;

    @NotNull(message = "INVALID_PRICE")
    private Double price;

    @NotNull(message = "INVALID_QUANTITY")
    private Integer quantity;

    @NotNull(message = "INVALID_BRAND")
    private Long brandId;
}
