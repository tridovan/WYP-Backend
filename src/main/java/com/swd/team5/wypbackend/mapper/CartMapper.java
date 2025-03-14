package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.CartCreateRequest;
import com.swd.team5.wypbackend.dto.response.CartResponse;
import com.swd.team5.wypbackend.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Cart toEntity(CartCreateRequest request);

    @Mapping(target = "userId", source = "user.id")
    CartResponse toResponse(Cart cart);
}
