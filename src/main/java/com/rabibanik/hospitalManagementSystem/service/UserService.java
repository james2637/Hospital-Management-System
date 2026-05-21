package com.rabibanik.hospitalManagementSystem.service;

import com.rabibanik.hospitalManagementSystem.entity.User;
import com.rabibanik.hospitalManagementSystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepo userRepo;

    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);
    }
}
