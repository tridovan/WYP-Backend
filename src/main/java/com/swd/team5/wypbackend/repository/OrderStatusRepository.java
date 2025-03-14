package com.swd.team5.wypbackend.repository;

import com.swd.team5.wypbackend.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, String> {
    boolean existsByStatusName(String statusName);
}
