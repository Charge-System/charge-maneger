package com.charge.chargeManeger.api.dto;

/*
*
* Objetivo: representar um cobrança efetuada*/
public class CobrancaCreatedDTO {

    private String idCobrancaAsaas;

    public CobrancaCreatedDTO(String idCobrancaAsaas) {
        this.idCobrancaAsaas = idCobrancaAsaas;
    }

    public String getIdCobrancaAsaas() {
        return idCobrancaAsaas;
    }

    public void setIdCobrancaAsaas(String idCobrancaAsaas) {
        this.idCobrancaAsaas = idCobrancaAsaas;
    }
}
