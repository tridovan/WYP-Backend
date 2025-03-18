package com.swd.team5.wypbackend.repository;

import com.swd.team5.wypbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBrandId(Long brandId);

    Optional<Product> findByName(String name);

    boolean existsByName(String name);
}
