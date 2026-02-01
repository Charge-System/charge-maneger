package com.charge.chargeManeger.api.dto;

public record ClienteDTO(
        Integer id,
        String nome,
        String email,
        String cpfCnpj,
        String telefone,
        String idASAAS) {}
