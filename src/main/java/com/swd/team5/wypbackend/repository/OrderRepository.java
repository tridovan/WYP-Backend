package com.swd.team5.wypbackend.repository;

import com.swd.team5.wypbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
