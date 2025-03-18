package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.OrderStatusCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderStatusUpdateRequest;
import com.swd.team5.wypbackend.dto.response.OrderStatusResponse;
import com.swd.team5.wypbackend.entity.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderStatusMapper {

    @Mapping(target = "id", ignore = true)
    OrderStatus toEntity(OrderStatusCreateRequest request);

    OrderStatusResponse toResponse(OrderStatus orderStatus);

    // Chỉ định rõ ràng nguồn cho statusName
    @Mapping(target = "statusName", source = "request.statusName")
    OrderStatus update(OrderStatus orderStatus, OrderStatusUpdateRequest request);
}
