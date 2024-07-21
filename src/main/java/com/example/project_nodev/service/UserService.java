package com.example.project_nodev.service;


import com.example.project_nodev.entity.User;
import com.example.project_nodev.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void write(User user) {
        userRepository.save(user);
    }




}
