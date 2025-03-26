package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.OrderDetailCreateRequest;
import com.swd.team5.wypbackend.dto.request.OrderDetailUpdateRequest;
import com.swd.team5.wypbackend.dto.response.OrderDetailResponse;
import com.swd.team5.wypbackend.entity.Order;
import com.swd.team5.wypbackend.entity.OrderDetail;
import com.swd.team5.wypbackend.entity.OrderDetailStatus;
import com.swd.team5.wypbackend.entity.Product;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.OrderDetailMapper;
import com.swd.team5.wypbackend.repository.OrderDetailRepository;
import com.swd.team5.wypbackend.repository.OrderRepository;
import com.swd.team5.wypbackend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    @Transactional
    public OrderDetailResponse create(OrderDetailCreateRequest request) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(orderRepository.findById(request.getOrderId())
               .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED)));
        orderDetail.setProduct(productRepository.findById(Long.parseLong(request.getProductId()))
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)));
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setIsCustomization(request.getIsCustomization());
        orderDetail.setIsDeposit(request.getIsDeposit());
        orderDetail.setPrice(request.getPrice());

        // ✅ Đặt trạng thái mặc định là PENDING khi tạo mới
        orderDetail.setStatus(OrderDetailStatus.PENDING);

        orderDetailRepository.save(orderDetail);

        return toOrderDetailResponse(orderDetail);
    }

//    public OrderDetailResponse create(OrderDetailCreateRequest request) {
//        Order order = orderRepository.findById(request.getOrderId())
//                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
//
//        Product product = productRepository.findById(Long.parseLong(request.getProductId()))
//                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
//
//        OrderDetail orderDetail = orderDetailMapper.toEntity(request);
//        orderDetail.setOrder(order);
//        orderDetail.setProduct(product);
//
//        orderDetail = orderDetailRepository.save(orderDetail);
//        return orderDetailMapper.toResponse(orderDetail);
//    }

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
    @Transactional
    public OrderDetailResponse updateStatus(String orderDetailId, OrderDetailStatus status) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_EXISTED));

        orderDetail.setStatus(status);
        orderDetailRepository.save(orderDetail);

        return toOrderDetailResponse(orderDetail);
    }
    private OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {
        OrderDetailResponse response = new OrderDetailResponse();
        response.setId(orderDetail.getId());
        response.setOrderId(orderDetail.getOrder().getId());
        response.setProductId(String.valueOf(orderDetail.getProduct().getId()));
        response.setQuantity(orderDetail.getQuantity());
        response.setIsCustomization(orderDetail.getIsCustomization());
        response.setIsDeposit(orderDetail.getIsDeposit());
        response.setPrice(orderDetail.getPrice());
        response.setStatus(orderDetail.getStatus()); // Trả về trực tiếp dưới dạng OrderDetailStatus
        response.setCreatedAt(orderDetail.getCreatedAt());
        return response;
    }

}
