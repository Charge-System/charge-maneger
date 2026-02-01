package com.charge.chargeManeger.business.ports;

import com.charge.chargeManeger.api.dto.CobrancaDTO;
import com.charge.chargeManeger.api.dto.enums.StatusCobranca;

import java.util.List;

public interface CobrancaRepository {
    void criarCobranca(CobrancaDTO cobrancaDTO);
    List<CobrancaDTO> buscarPorCliente(Long clienteId);
    List<CobrancaDTO> listarCobrancas();
    void removerCobranca(Long idCobranca);
    void atualizarStatusCobranca(String idCobrancaAsaas, StatusCobranca statusCobranca);
    void atualizarCobranca(CobrancaDTO cobrancaDTO);
}
