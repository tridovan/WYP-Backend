package com.swd.team5.wypbackend.repository;

import com.swd.team5.wypbackend.entity.Customization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizationRepository extends JpaRepository<Customization, Long> {
}
