package com.example.aloalo.util;

import com.example.aloalo.security.UserPrinciple;

import org.springframework.security.core.context.SecurityContextHolder;


public class Util {
    public static UserPrinciple getUser(){
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
