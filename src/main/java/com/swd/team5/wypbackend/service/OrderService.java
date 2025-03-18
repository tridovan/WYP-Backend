package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.response.OrderResponse;
import com.swd.team5.wypbackend.entity.Order;
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
        order = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }
}
