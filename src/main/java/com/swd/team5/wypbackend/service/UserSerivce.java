package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.UserCreateRequest;
import com.swd.team5.wypbackend.dto.response.UserResponse;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.UserMapper;
import com.swd.team5.wypbackend.repository.RoleRepository;
import com.swd.team5.wypbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSerivce {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponse create(UserCreateRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
            System.err.println("Existed Username");
            throw new AppException(ErrorCode.EXISTED_USERNAME);

        }
        User user = userMapper.toUser(request);
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRole(roleRepository.findById("USER").orElseThrow(() -> new AppException(ErrorCode.INVALID_ROLE_AT_SERVER)));
        return userMapper.toResponse(userRepository.save(user));
    }
}
