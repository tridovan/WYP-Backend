package com.swd.team5.wypbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductUpdateRequest {

    @Size(min = 3, message = "INVALID_NAME")
    private String name;

    private String description;

    private String image;

    private Double price;

    private Integer quantity;

    private Long brandId;
}
