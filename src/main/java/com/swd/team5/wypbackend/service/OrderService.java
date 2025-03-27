package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderUpdateRequest;
import com.swd.team5.wypbackend.dto.response.OrderResponse;
import com.swd.team5.wypbackend.entity.Order;
import com.swd.team5.wypbackend.entity.OrderDetailStatus;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.OrderMapper;
import com.swd.team5.wypbackend.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderResponse create(OrderCreateRequest request) {
        Order order = orderMapper.toEntity(request);
        order.setStatus(OrderDetailStatus.PENDING); // Đặt mặc định là PENDING
        order = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }
    public OrderResponse updateStatus(String orderId, OrderUpdateRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));

        if (request.getStatus() != null) {
            order.setStatus(request.getStatus());
        }

        order = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }

//    // Hàm cập nhật trạng thái đơn hàng
//    public OrderResponse updateStatus(String orderId, OrderDetailStatus status) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
//
//        order.setStatus(status); // Cập nhật trạng thái
//        order = orderRepository.save(order); // Lưu lại vào DB
//
//        return orderMapper.toResponse(order);
//    }
}
