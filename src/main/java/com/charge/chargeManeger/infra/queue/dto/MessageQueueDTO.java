package com.charge.chargeManeger.infra.queue.dto;

/*
* Objetivo: Conter as mensagens que serão
* enviadas na fila
* */
public class MessageQueueDTO {

    private String idCobrancaAsaas;

    private String statusCobranca;

    public MessageQueueDTO(String idCobrancaAsaas, String statusCobranca) {
        this.idCobrancaAsaas = idCobrancaAsaas;
        this.statusCobranca = statusCobranca;
    }

    public MessageQueueDTO() {
    }

    public String getIdCobrancaAsaas() {
        return idCobrancaAsaas;
    }

    public void setIdCobrancaAsaas(String idCobrancaAsaas) {
        this.idCobrancaAsaas = idCobrancaAsaas;
    }

    public String getStatusCobranca() {
        return statusCobranca;
    }

    public void setStatusCobranca(String statusCobranca) {
        this.statusCobranca = statusCobranca;
    }
}
