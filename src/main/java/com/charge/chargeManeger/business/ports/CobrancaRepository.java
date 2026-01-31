package com.charge.chargeManeger.business.ports;

import com.charge.chargeManeger.api.dto.CobrancaDTO;

import java.util.List;

public interface CobrancaRepository {
    void criarCobranca(CobrancaDTO msg);
    List<CobrancaDTO> buscarPorCliente(Long clienteId);
    List<CobrancaDTO> listarCobrancas();
    void removerCobranca(Long idCobranca);

    void atualizarCobranca(CobrancaDTO cobrancaDTO);
}
