package com.swd.team5.wypbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private String size;

    private String image;

    private String color;

    private Double price;

    private Integer quantity;

    private String brandName;
}
