package com.benkhanous.springbootlibrary.controller;

import com.benkhanous.springbootlibrary.entity.Message;
import com.benkhanous.springbootlibrary.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.ExtractJWT;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessagesController {
    private MessageService messageService;

    @Autowired
    public MessagesController(MessageService messagesService){
        this.messageService=messagesService;
    }
    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value = "Authorization") String token, @RequestBody Message messageRequest){
        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        messageService.postMessage(messageRequest,userEmail);

    }
}
