package com.swd.team5.wypbackend.repository;

import com.swd.team5.wypbackend.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;



@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByIsActiveTrue();
    List<Promotion> findByEndDateAfterAndIsActiveTrue(LocalDateTime now);
    List<Promotion> findByProductId(Long productId);
}
