package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.response.OrderResponse;
import com.swd.team5.wypbackend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "orderDetailList", ignore = true)
    Order toEntity(OrderCreateRequest request);

    @Mapping(target = "userId", source = "user.id")
    OrderResponse toResponse(Order order);
}