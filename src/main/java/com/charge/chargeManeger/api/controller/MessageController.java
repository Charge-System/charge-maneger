package com.charge.chargeManeger.api.controller;

import com.charge.chargeManeger.api.dto.MessageDTO;
import com.charge.chargeManeger.business.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<MessageDTO> getMessage(){
        MessageDTO messageDTO =  messageService.getMessage();

        return ResponseEntity.ok(messageDTO);
    }

}
