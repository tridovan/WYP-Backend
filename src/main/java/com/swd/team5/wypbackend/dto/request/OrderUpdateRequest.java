package com.swd.team5.wypbackend.dto.request;

import com.swd.team5.wypbackend.entity.OrderDetailStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpdateRequest {
    private OrderDetailStatus status; // Sử dụng kiểu String thay vì Enum để truyền vào JSON
}
