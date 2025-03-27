package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.response.OrderResponse;
import com.swd.team5.wypbackend.entity.Order;
import com.swd.team5.wypbackend.enums.OrderStatus;
import com.swd.team5.wypbackend.mapper.OrderDetailMapper;
import com.swd.team5.wypbackend.mapper.OrderMapper;
import com.swd.team5.wypbackend.repository.AddressRepository;
import com.swd.team5.wypbackend.repository.OrderDetailRepository;
import com.swd.team5.wypbackend.repository.OrderRepository;
import com.swd.team5.wypbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    private final OrderDetailMapper orderDetailMapper;

    private final UserSerivce userSerivce;


    public OrderResponse create(OrderCreateRequest request) {
        Order order = orderMapper.toEntity(request);
        order.setOrderDetailList(orderDetailMapper.toListEntity(request.getOrderDetailList()));
        order.setUser(userSerivce.findUserById(request.getUserId()));
        order.setStatus(OrderStatus.PENDING);
        return orderMapper.toResponse(orderRepository.save(order));
    }
}
