package com.example.aloalo.repo.iml;

import com.example.aloalo.dto.RegisterFormDto;
import com.example.aloalo.entity.User;

import java.util.Optional;

public interface UserRepo {
    User findByUsername(String username);
    void save(RegisterFormDto registerFormDto);
}
