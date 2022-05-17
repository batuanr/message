package com.example.aloalo.service;

import com.example.aloalo.dto.RegisterFormDto;
import com.example.aloalo.entity.User;

import java.util.List;

public interface UserService {
    void save(RegisterFormDto registerFormDto);
    List<User> findAll();
}
