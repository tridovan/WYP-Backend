package com.swd.team5.wypbackend.repository;

import com.swd.team5.wypbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String s);

    Optional<User> findById(String userId);

    @Query("SELECT u FROM User u WHERE u.isActive = :isActive")
    List<User> findUsersByActive(@Param("isActive") Boolean isActive);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
