package com.swd.team5.wypbackend.mapper;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderDetailCreateRequest;
import com.swd.team5.wypbackend.dto.response.OrderDetailResponse;
import com.swd.team5.wypbackend.dto.response.OrderResponse;
import com.swd.team5.wypbackend.entity.Order;
import com.swd.team5.wypbackend.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {
    OrderDetail toEntity(OrderDetailCreateRequest request);

    OrderDetailResponse toResponse(OrderDetail orderDetail);

    List<OrderDetailResponse> toListResponse(List<OrderDetail> orderDetailList);

    List<OrderDetail> toListEntity(List<OrderDetailCreateRequest> orderDetailCreateRequests);
}
