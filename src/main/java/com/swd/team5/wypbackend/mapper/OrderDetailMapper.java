package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.OrderDetailCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderDetailUpdateRequest;
import com.swd.team5.wypbackend.dto.response.OrderDetailResponse;
import com.swd.team5.wypbackend.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    OrderDetail toEntity(OrderDetailCreateRequest request);

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    OrderDetailResponse toResponse(OrderDetail orderDetail);

    // Chỉ định rõ ràng nguồn cho từng thuộc tính
    @Mapping(target = "quantity", source = "request.quantity")
    @Mapping(target = "isCustomization", source = "request.isCustomization")
    @Mapping(target = "isDeposit", source = "request.isDeposit")
    @Mapping(target = "price", source = "request.price")
    @Mapping(target = "status", source = "request.status")
    OrderDetail update(OrderDetail orderDetail, OrderDetailUpdateRequest request);
}
