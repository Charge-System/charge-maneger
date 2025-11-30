package com.charge.chargeManeger.business.service;


import com.charge.chargeManeger.api.dto.MessageDTO;
import com.charge.chargeManeger.business.ports.MessageRepository;
import org.springframework.stereotype.Service;

/*
* Objetivo: Aplicar as regras de negócio envolvendo as messages
* */
@Service
public class MessageService {

    //instancia que ira chamar os métdos do banco
    private final MessageRepository messageRepository;

    //injetando dependencia via constructor
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageDTO getMessage(){
        return messageRepository.getMessageFromDb();
    }
}
