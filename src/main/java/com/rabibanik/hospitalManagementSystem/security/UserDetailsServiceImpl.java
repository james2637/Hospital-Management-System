package com.rabibanik.hospitalManagementSystem.security;

import com.rabibanik.hospitalManagementSystem.entity.User;
import com.rabibanik.hospitalManagementSystem.repository.UserRepo;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
