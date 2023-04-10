package com.todoLb.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todoLb.Document.User;
import com.todoLb.Repository.UserRepo;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userrepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userrepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public User findById(String id){
        return userrepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User s not found"));
    }

}
