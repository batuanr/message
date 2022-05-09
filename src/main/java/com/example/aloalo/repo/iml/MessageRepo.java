package com.example.aloalo.repo.iml;

import com.example.aloalo.entity.Message;

import java.util.List;

public interface MessageRepo {
    List<Message> findAll(Long senderId, Long receiverId);
    void save(Message message);
}
