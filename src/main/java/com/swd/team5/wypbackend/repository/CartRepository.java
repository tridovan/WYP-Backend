package com.swd.team5.wypbackend.repository;

import com.swd.team5.wypbackend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    List<Cart> findByUser_Id(String userId);
}
