package com.swd.team5.wypbackend.service;

import com.swd.team5.wypbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private UserRepository userRepository;

    public void searchUser(){

    }
}
