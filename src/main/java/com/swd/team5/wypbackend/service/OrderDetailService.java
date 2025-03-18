package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.OrderDetailCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderDetailUpdateRequest;
import com.swd.team5.wypbackend.dto.response.OrderDetailResponse;
import com.swd.team5.wypbackend.entity.Order;
import com.swd.team5.wypbackend.entity.OrderDetail;
import com.swd.team5.wypbackend.entity.Product;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.OrderDetailMapper;
import com.swd.team5.wypbackend.repository.OrderDetailRepository;
import com.swd.team5.wypbackend.repository.OrderRepository;
import com.swd.team5.wypbackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository,
                              ProductRepository productRepository, OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

    public OrderDetailResponse create(OrderDetailCreateRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));

        Product product = productRepository.findById(Long.parseLong(request.getProductId()))
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        OrderDetail orderDetail = orderDetailMapper.toEntity(request);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);

        orderDetail = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toResponse(orderDetail);
    }

    public OrderDetailResponse update(String orderDetailId, OrderDetailUpdateRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_EXISTED));

        orderDetail = orderDetailMapper.update(orderDetail, request);
        orderDetail = orderDetailRepository.save(orderDetail);

        return orderDetailMapper.toResponse(orderDetail);
    }


    public void delete(String orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_EXISTED));
        orderDetailRepository.delete(orderDetail);
    }

    public List<OrderDetailResponse> getAllByOrderId(String orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_Id(orderId);
        return orderDetails.stream()
                .map(orderDetailMapper::toResponse)
                .toList();
    }
}
