package com.charge.chargeManeger.infra.webservice;

import com.charge.chargeManeger.GerarCobrancaRequest;
import com.charge.chargeManeger.GerarCobrancaResponse;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

/*
 * Objetivo: Configurar a requisição que será feita
 * no serviço SOAP de cobrança
 * */
@Component
public class ClienteSoapCobranca {

    private final WebServiceTemplate webServiceTemplate;

    public ClienteSoapCobranca(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public GerarCobrancaResponse gerarCobranca(GerarCobrancaRequest request) {
        return (GerarCobrancaResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }
}