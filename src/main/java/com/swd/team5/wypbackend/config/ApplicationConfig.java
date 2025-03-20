package com.swd.team5.wypbackend.config;

import com.cloudinary.Cloudinary;
import com.swd.team5.wypbackend.entity.Role;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.repository.RoleRepository;
import com.swd.team5.wypbackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ApplicationConfig {
    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

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

    @Bean
    public Cloudinary getCloudinary(){
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        config.put("secure", true);
        return new Cloudinary(config);
    }

}
