package com.example.aloalo.service;

import com.example.aloalo.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll(Long senderId, Long receiverId);
    void save(Message message);
}
