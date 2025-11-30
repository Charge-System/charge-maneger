package com.charge.chargeManeger.business.ports;

import com.charge.chargeManeger.api.dto.MessageDTO;

/*
* Objetivo: definir os serviços disponíveis para as
* messages no sistema (implementação com conexão a base)
* de dados na infra
* */
public interface MessageRepository {
    public MessageDTO getMessageFromDb();
}
