package com.charge.chargeManeger.api.controller;

import com.charge.chargeManeger.api.dto.MessageDTO;
import com.charge.chargeManeger.business.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
/*
* Objetivo: Interceptar as requisições HTTP à aplicação referente as mensagens
* */
public class MessageController {

    //chama a camada de serviço para obter os dados
    private final MessageService messageService;

    //injetando service via contructor
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getMessages() {
        List<MessageDTO> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/post")
    public ResponseEntity<MessageDTO> save(@RequestBody MessageDTO messageDTO) {
        MessageDTO savedMessage = messageService.saveMessage(messageDTO);
        return ResponseEntity.ok(savedMessage);
    }
}

