package com.charge.chargeManeger.api.dto;

/*
* Objetivo: Representar um cliente criado na base de dados
* */
public class ClientCreatedDTO {

    private String idClienteAsaas;

    public ClientCreatedDTO(String idClienteAsaas) {
        this.idClienteAsaas = idClienteAsaas;
    }

    public String getIdClienteAsaas() {
        return idClienteAsaas;
    }

    public void setIdClienteAsaas(String idClienteAsaas) {
        this.idClienteAsaas = idClienteAsaas;
    }
}
