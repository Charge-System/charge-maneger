package com.charge.chargeManeger.infra.exceptions;

import com.charge.chargeManeger.api.controller.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Objetivo: Controlar o retorno das chamadas a API caso
 * alguma exceção seja lançada durante a execução
 *
 * @author Cauã Pires Soares
 */
@ControllerAdvice
public class TratadorExcecoes {

    /*
     * Trata qualquer exceção ococrrida na aplicação, por padrão,
     * qualquer exceção ocorrida retornará o código 500
     * */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> tratarExcecaoGenerica(
            Exception ex) {

        ApiResponse<Void> response =
                ApiResponse.erro("Ocorreu um erro de acesso ao sistema: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
