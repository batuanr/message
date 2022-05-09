package com.example.aloalo.api;


import com.example.aloalo.dto.JwtResponse;
import com.example.aloalo.dto.LoginFormDTO;
import com.example.aloalo.dto.RegisterFormDto;
import com.example.aloalo.entity.User;
import com.example.aloalo.repo.iml.UserRepo;

import com.example.aloalo.security.UserPrinciple;
import com.example.aloalo.security.jwt.JwtProvider;
import com.example.aloalo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginFormDTO loginFormDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginFormDTO.getUsername(), loginFormDTO.getPassword())
        );


//
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.creatToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(new JwtResponse(userPrinciple.getId(), token, userPrinciple.getName(), loginFormDTO.getUsername()), HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterFormDto registerFormDto) {
        registerFormDto.setPassword(passwordEncoder.encode(registerFormDto.getPassword()));
        userService.save(registerFormDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ok")
    public ResponseEntity<?> ok(@RequestBody LoginFormDTO loginFormDTO) {
        User user = userRepo.findByUsername(loginFormDTO.getUsername());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
