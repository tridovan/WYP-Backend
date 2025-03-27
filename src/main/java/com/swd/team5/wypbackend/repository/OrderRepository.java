package com.swd.team5.wypbackend.repository;

import com.swd.team5.wypbackend.entity.Order;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUser(User user);

    List<Order> findAllByStatus(OrderStatus status);
}
