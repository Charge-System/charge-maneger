package com.charge.chargeManeger.api.controller.api;

/*
 * Objetivo: Trafegar dados de forma padronizada entre o serviço e um
 * dado controlador
 * */
public class ApiResponse<T> {

    private boolean ehSucesso;

    private String mensagem;

    private T dados;

    // Construtor do ApiResponse
    public ApiResponse(boolean ehSucesso, String mensagem, T dados) {
        this.ehSucesso = ehSucesso;
        this.mensagem = mensagem;
        this.dados = dados;
    }

    public ApiResponse() {
    }

    /*
    * Objetivo: Montar um resposta em um cenário de operação
    * realizada com sucesso
    * */
    public static <T> ApiResponse<T> sucesso(String mensagem, T dados) {
        return new ApiResponse<T>(true, mensagem, dados);
    }

    /*
     * Objetivo: Montar um resposta em um cenário de operação
     * realizada com algum erro
     * */
    public static ApiResponse erro(String mensagem) {
        return new ApiResponse(false, mensagem, null);
    }

    public boolean isEhSucesso() {
        return ehSucesso;
    }

    public void setEhSucesso(boolean ehSucesso) {
        this.ehSucesso = ehSucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }
}
