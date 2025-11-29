package com.charge.chargeManeger.api.controller;

//Recebe as requisições

import com.charge.chargeManeger.api.dto.MessageDTO;
import com.charge.chargeManeger.business.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
//ponto de entrada da api
public class MessageController {

    //chama a camada de servicço para obter os dados
    @Autowired
    private MessageService service;

    @GetMapping
    public MessageDTO getMessage(){
        return service.getMessage();
    }

}
