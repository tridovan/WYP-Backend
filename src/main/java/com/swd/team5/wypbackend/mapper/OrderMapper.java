package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.response.OrderResponse;
import com.swd.team5.wypbackend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Order toEntity(OrderCreateRequest request);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "status", expression = "java(order.getStatus().name())") // Chuyển từ Enum thành String
    @Mapping(target = "orderDetails", source = "orderDetails") // Ánh xạ danh sách OrderDetail
    OrderResponse toResponse(Order order);
}
