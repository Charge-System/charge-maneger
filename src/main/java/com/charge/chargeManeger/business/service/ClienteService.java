package com.charge.chargeManeger.business.service;

import com.charge.chargeManeger.business.ports.ClienteRepository;
import com.charge.chargeManeger.api.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired // O Spring injeta o ClienteRepositoryImpl automaticamente aqui
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void registrarNovoCliente(ClienteDTO dto) {
        if (!dto.email().contains("@")) {
            throw new IllegalArgumentException("E-mail inválido!");
        }
        clienteRepository.salvar(dto);
    }

    public List<ClienteDTO> listarTodos() {
        return clienteRepository.listarTodos();
    }
}