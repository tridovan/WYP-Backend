package com.swd.team5.wypbackend.mapper;


import com.swd.team5.wypbackend.dto.request.PromotionCreateRequest;
import com.swd.team5.wypbackend.dto.response.PromotionResponse;
import com.swd.team5.wypbackend.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PromotionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "product", ignore = true)
    Promotion toEntity(PromotionCreateRequest request);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    PromotionResponse toResponse(Promotion promotion);
}
