package com.example.aloalo.service;

import com.example.aloalo.entity.Message;
import com.example.aloalo.repo.iml.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceIml implements MessageService{
    @Autowired
    MessageRepo messageRepo;

    @Override
    public List<Message> findAll(Long senderId, Long receiverId) {
        return messageRepo.findAll(senderId, receiverId);
    }

    @Override
    public void save(Message message) {
        messageRepo.save(message);
    }
}
