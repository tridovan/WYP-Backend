package com.swd.team5.wypbackend.entity;

public enum OrderDetailStatus {
    PENDING,       // Đang chờ xử lý
    CONFIRMED,     // Đã xác nhận
    PROCESSING,    // Đang xử lý
    SHIPPED,       // Đã vận chuyển
    DELIVERED,     // Đã giao hàng
    CANCELLED,     // Đã hủy
    RETURNED,      // Đã trả hàng
    REFUNDED,      // Đã hoàn tiền
    FAILED         // Thanh toán thất bại
}
