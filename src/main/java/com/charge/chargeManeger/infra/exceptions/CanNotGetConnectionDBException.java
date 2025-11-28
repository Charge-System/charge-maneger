package com.charge.chargeManeger.infra.exceptions;

public class CanNotGetConnectionDBException extends GenericDataBaseException {
    public CanNotGetConnectionDBException() {
        super("Ocorreu um erro ao obter a conexão com o banco de dados");
    }
}
