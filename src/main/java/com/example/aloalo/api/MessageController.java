package com.example.aloalo.api;

import com.example.aloalo.dto.MessageRequest;
import com.example.aloalo.entity.Message;
import com.example.aloalo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@CrossOrigin("*")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestBody MessageRequest messageRequest){
        return new ResponseEntity<>(messageService.findAll(messageRequest.getSenderId(), messageRequest.getReceiverId()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Message message){
        messageService.save(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
