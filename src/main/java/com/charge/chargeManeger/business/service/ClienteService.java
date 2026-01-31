package com.charge.chargeManeger.business.service;

import com.charge.chargeManeger.business.ports.ClienteRepository;
import com.charge.chargeManeger.api.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
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

    public void atualizarCliente(ClienteDTO dto) {
        clienteRepository.atualizar(dto);
    }

    public void excluirCliente(Long id) {
        clienteRepository.deletar(id);
    }
}