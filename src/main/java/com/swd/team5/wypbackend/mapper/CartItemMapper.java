package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.CartItemCreateRequest;
import com.swd.team5.wypbackend.dto.request.CartItemUpdateRequest;
import com.swd.team5.wypbackend.dto.response.CartItemResponse;
import com.swd.team5.wypbackend.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addedAt", expression = "java(java.time.LocalDateTime.now())")
    CartItem toEntity(CartItemCreateRequest request);

    @Mapping(target = "cartId", source = "cart.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "customizationId", source = "customization.id")
    CartItemResponse toResponse(CartItem cartItem);

//    @Mapping(target = "isCustomization", source = "request.isCustomization")
//    @Mapping(target = "quantity", source = "request.quantity", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL) // ✅
//    CartItem update(CartItem cartItem, CartItemUpdateRequest request);
}
