package com.example.aloalo.security;

import com.example.aloalo.entity.User;

import com.example.aloalo.repo.iml.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetail implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User user = new User();
        try{
            user = userRepo.findByUsername(username);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return UserPrinciple.build(user);
    }
}
