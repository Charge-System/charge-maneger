package com.charge.chargeManeger.business.service;

import com.charge.chargeManeger.api.dto.CobrancaDTO;
import com.charge.chargeManeger.business.ports.CobrancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CobrancaService {

    private final CobrancaRepository cobrancaRepository;

    @Autowired
    public CobrancaService(CobrancaRepository cobrancaRepository) {
        this.cobrancaRepository = cobrancaRepository;
    }

    public void gerarCobranca(CobrancaDTO dto) {
        // Regra de negocio
//        if (dto.value() <= 0) {
//            throw new RuntimeException("O valor da cobrança deve ser maior que zero.");
//        }

        cobrancaRepository.criarCobranca(dto);
    }

    public List<CobrancaDTO> listarCobrancasPorCliente(Long clienteId) {
        return cobrancaRepository.buscarPorCliente(clienteId);
    }
    public List<CobrancaDTO> listarCobrancas() {
        return cobrancaRepository.listarCobrancas();
    }
}