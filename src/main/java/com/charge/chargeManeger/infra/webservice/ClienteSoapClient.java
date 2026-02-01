package com.charge.chargeManeger.infra.webservice;

import com.charge.chargeManeger.CriarClienteRequest;
import com.charge.chargeManeger.CriarClienteResponse;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

/*
* Objetivo: Configurar a requisição que será feita
* no serviço SOAP de cliente
* */
@Component
public class ClienteSoapClient {

    private final WebServiceTemplate webServiceTemplate;

    public ClienteSoapClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public CriarClienteResponse criarCliente(CriarClienteRequest request) {
        return (CriarClienteResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }
}