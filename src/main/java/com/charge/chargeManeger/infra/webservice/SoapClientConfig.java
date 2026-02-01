package com.charge.chargeManeger.infra.webservice;

import com.charge.chargeManeger.CriarClienteRequest;
import com.charge.chargeManeger.CriarClienteResponse;
import com.charge.chargeManeger.GerarCobrancaRequest;
import com.charge.chargeManeger.GerarCobrancaResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

/*
* Objetivo: Realizar as configurações mínimas necessárias para
* o consumo do serviço SOAP
* */
@Configuration
public class SoapClientConfig {

    private final String BASE_URL = "http://chargerproxy:8081/ws";

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(
                CriarClienteRequest.class,
                CriarClienteResponse.class,
                GerarCobrancaRequest.class,
                GerarCobrancaResponse.class
        );
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);
        template.setDefaultUri(BASE_URL);
        return template;
    }
}

