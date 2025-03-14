package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.OrderStatusCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderStatusUpdateRequest;
import com.swd.team5.wypbackend.dto.response.OrderStatusResponse;
import com.swd.team5.wypbackend.entity.OrderStatus;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.OrderStatusMapper;
import com.swd.team5.wypbackend.repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapper orderStatusMapper;

    public OrderStatusService(OrderStatusRepository orderStatusRepository,
                              OrderStatusMapper orderStatusMapper) {
        this.orderStatusRepository = orderStatusRepository;
        this.orderStatusMapper = orderStatusMapper;
    }

    public OrderStatusResponse create(OrderStatusCreateRequest request) {
        if (orderStatusRepository.existsByStatusName(request.getStatusName())) {
            throw new AppException(ErrorCode.ORDER_STATUS_ALREADY_EXISTED);
        }

        OrderStatus orderStatus = orderStatusMapper.toEntity(request);
        orderStatus = orderStatusRepository.save(orderStatus);

        return orderStatusMapper.toResponse(orderStatus);
    }

    public OrderStatusResponse update(String orderStatusId, OrderStatusUpdateRequest request) {
        OrderStatus orderStatus = orderStatusRepository.findById(orderStatusId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_STATUS_NOT_EXISTED));

        orderStatus = orderStatusMapper.update(orderStatus, request);
        orderStatus = orderStatusRepository.save(orderStatus);

        return orderStatusMapper.toResponse(orderStatus);
    }


    public void delete(String orderStatusId) {
        OrderStatus orderStatus = orderStatusRepository.findById(orderStatusId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_STATUS_NOT_EXISTED));
        orderStatusRepository.delete(orderStatus);
    }

    public List<OrderStatusResponse> getAll() {
        return orderStatusRepository.findAll()
                .stream()
                .map(orderStatusMapper::toResponse)
                .toList();
    }
}
