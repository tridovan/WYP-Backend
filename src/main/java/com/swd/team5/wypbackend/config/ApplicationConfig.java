package com.swd.team5.wypbackend.config;

import com.swd.team5.wypbackend.entity.Role;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.repository.RoleRepository;
import com.swd.team5.wypbackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
@Slf4j
public class ApplicationConfig {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            if (!roleRepository.existsById("ADMIN")) {
                Role roleAdmin = new Role();
                roleAdmin.setName("ADMIN");
                roleAdmin.setDescription("Vip pro prime administer of application");
                roleRepository.save(roleAdmin);
            }
            if(!roleRepository.existsById("USER")){
                Role roleUser = new Role();
                roleUser.setName("USER");
                roleUser.setDescription("Used for customers who have registered accounts");
                roleRepository.save(roleUser);

            }
            log.warn("⚠️⚠️⚠️application start with 2 basic roles, ADMIN and USER, you can use Admin account to alter it⚠️⚠️⚠️");
            log.warn("⚠🍎🥕admin user has bean created with default value admin/admin. please change it⚠️🥔😂️");
            if(!userRepository.existsByUsername("admin")){
                User user = new User();
                user.setUsername("admin");
                user.setActive(true);
                user.setPassword(bCryptPasswordEncoder.encode("admin"));

                user.setRole(roleRepository.findById("ADMIN").orElseThrow(() -> new AppException(ErrorCode.INVALID_ROLE_AT_SERVER)));
                userRepository.save(user);
            }


        };
    }

}
