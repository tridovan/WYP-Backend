package com.swd.team5.wypbackend.repository;

import com.swd.team5.wypbackend.entity.ForgetPasswordToken;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgetPasswordToken, String> {

    void removeAllByEmail(String email);


    Optional<ForgetPasswordToken> findByIdAndEmail(@NotBlank String token, String email);
}
