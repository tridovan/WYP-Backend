package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.OrderCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderDetailCreateRequest;
import com.swd.team5.wypbackend.dto.response.OrderResponse;
import com.swd.team5.wypbackend.entity.Address;
import com.swd.team5.wypbackend.entity.Order;
import com.swd.team5.wypbackend.entity.OrderDetail;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.enums.OrderStatus;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.OrderDetailMapper;
import com.swd.team5.wypbackend.mapper.OrderMapper;
import com.swd.team5.wypbackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    private final OrderDetailMapper orderDetailMapper;

    private final UserSerivce userSerivce;

    private final ProductService productService;


    public Order create(OrderCreateRequest orderCreateRequest, User user) {


        Order request = new Order();
        request.setUser(user);
        request.setAddress(orderCreateRequest.getAddress());
        List<OrderDetail> orderDetailList = orderCreateRequest.getOrderList()
                .stream().map(requestItem -> new OrderDetail(productService.getProductByID(requestItem.getProductId()), requestItem.getQuantity(), productService.getProductByID(requestItem.getProductId()).getPrice() * requestItem.getQuantity())).toList();
        orderDetailList.forEach(detail -> {if(detail.getQuantity() > detail.getProduct().getQuantity()){
            throw new AppException(ErrorCode.INVALID_QUANTITY);
        }else{
            detail.getProduct().setQuantity(detail.getProduct().getQuantity() - detail.getQuantity());
        }
        });


        request.setOrderDetailList(orderDetailList);
        request.setTotalPrice(orderDetailList.stream().mapToInt(detail -> detail.getPrice() * detail.getQuantity()).sum());
        request.setStatus(OrderStatus.PENDING);



        return orderRepository.save(request);
    }


    public Order update(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
        order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        return orderRepository.save(order);
    }

    public List<Order> getCartByUserId(String userId) {
        User user = userSerivce.findUserById(userId);
        return orderRepository.findByUser(user);
    }

    public List<Order> getCartByUsername(String username) {
        User user = userSerivce.findUserUsername(username);
        return orderRepository.findByUser(user);
    }

    public List<Order> getAll(String status) {
        if(Strings.isNotBlank(status)) {
            return orderRepository.findAllByStatus(OrderStatus.valueOf(status.toUpperCase()));
        }else
            return orderRepository.findAll();
    }
}
