package com.example.aloalo.service;

import com.example.aloalo.dto.RegisterFormDto;
import com.example.aloalo.entity.User;
import com.example.aloalo.repo.iml.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIml implements UserService{
    @Autowired
    UserRepo userRepo;

    @Override
    public void save(RegisterFormDto registerFormDto) {
        userRepo.save(registerFormDto);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
