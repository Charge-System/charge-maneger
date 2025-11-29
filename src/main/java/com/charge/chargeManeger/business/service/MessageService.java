package com.charge.chargeManeger.business.service;


import com.charge.chargeManeger.api.dto.MessageDTO;
import com.charge.chargeManeger.infra.datasource.DataBaseManager;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    //instancia que ira chamar os métdos do banco
    private final DataBaseManager dbManager = new DataBaseManager();

    public MessageDTO getMessage(){
        return dbManager.getMessageFromDb();
    }
}
