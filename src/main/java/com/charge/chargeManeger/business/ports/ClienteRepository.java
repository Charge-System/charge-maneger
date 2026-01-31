package com.charge.chargeManeger.business.ports;

import com.charge.chargeManeger.api.dto.ClienteDTO;
import java.util.List;

public interface ClienteRepository {
    void salvar(ClienteDTO cliente);
    List<ClienteDTO> listarTodos();
    void atualizar(ClienteDTO cliente);
    void deletar(Long id);
}