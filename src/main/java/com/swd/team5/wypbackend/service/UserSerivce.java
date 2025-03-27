package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.dto.request.ResetPasswordRequest;
import com.swd.team5.wypbackend.dto.request.UserCreateRequest;
import com.swd.team5.wypbackend.dto.request.UserUpdateRequest;
import com.swd.team5.wypbackend.dto.response.PageResponse;
import com.swd.team5.wypbackend.dto.response.UserResponse;
import com.swd.team5.wypbackend.entity.ForgetPasswordToken;
import com.swd.team5.wypbackend.entity.Role;
import com.swd.team5.wypbackend.entity.User;
import com.swd.team5.wypbackend.enums.ErrorCode;
import com.swd.team5.wypbackend.exception.AppException;
import com.swd.team5.wypbackend.mapper.UserMapper;
import com.swd.team5.wypbackend.repository.ForgotPasswordRepository;
import com.swd.team5.wypbackend.repository.RoleRepository;
import com.swd.team5.wypbackend.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    @Autowired
    private PageService pageService;

    public UserResponse create(UserCreateRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
            System.err.println("Existed Username");
            throw new AppException(ErrorCode.EXISTED_USERNAME);

        }
        User user = userMapper.toUser(request);
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setActive(true);
        user.setRole(roleRepository.findById("USER")
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ROLE_AT_SERVER)));
        return userMapper.toResponse(userRepository.save(user));
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponse> getAll() {

        return userRepository.findUsersByActive(true).stream().map(userMapper::toResponse).toList();
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponse> getAllInactive() {

        return userRepository.findUsersByActive(false).stream().map(userMapper::toResponse).toList();
    }

    public UserResponse update(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        System.err.println(user);
        System.err.println(request);
        userMapper.updateUser(user, request);
        if(request.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        }
        User user1 = userRepository.save(user);
        System.err.println(user1);
        return userMapper.toResponse(userRepository.save(user));

    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.setActive(false);
        userRepository.save(user);
        return "delete successfully";
    }

    public UserResponse getUserByUsername(String username) {
        return userMapper.toResponse(userRepository.findByUsername(username)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));

    }

    //nhớ phải enable method security
//    @PostAuthorize("returnObject.username.equals(authentication.name)")
    public UserResponse getUser(String userId) {
        return userMapper.toResponse(userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));

    }

    public String resetPassword(String email, ResetPasswordRequest request) {
        ForgetPasswordToken forgetPasswordToken = forgotPasswordRepository.findByIdAndEmail(request.getToken(), email)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_TOKEN));
        if(!forgetPasswordToken.getExpireTime().isBefore(LocalDateTime.now())){
            User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.INVALID_EMAIL));
            user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            userRepository.save(user);
            return "Update password successfully";
        }
        return "Token has expired";
    }

    public PageResponse<List<UserResponse>> getAllUserSortBy(int pageNo, int pageSize, String... sorts){
//        if(pageNo > 0){
//            pageNo--;
//        }
//        List<Sort.Order> orders = new ArrayList<>();
//        for (String sort : sorts) {
//            if (Strings.isNotBlank(sort)) {
//                // field:acs|desc
//                Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
//                Matcher matcher = pattern.matcher(sort);
//                if (matcher.find()) {
//                    if (matcher.group(3).equalsIgnoreCase("asc")) {
//                        orders.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
//                    } else {
//                        orders.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
//                    }
//                }
//            }
//        }
//        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orders));
        Pageable pageable = pageService.pageEngine(pageNo, pageSize, sorts);
        Page<User> page = userRepository.findAll(pageable);
        List<UserResponse> userResponses = page.getContent()
                .stream().map(userMapper::toResponse).toList();

        return PageResponse.<List<UserResponse>>builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .totalElement(userResponses.size())
                .sortBy(sorts)
                .items(userResponses)
                .build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponse updateRole(String userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Role role = roleRepository.findById(roleName).orElseThrow(() -> new AppException(ErrorCode.INVALID_ROLE));
        user.setRole(role);
        return userMapper.toResponse(user);
    }
}
