package com.charge.chargeManeger.api.controller;

import com.charge.chargeManeger.api.controller.api.ApiResponse;
import com.charge.chargeManeger.api.dto.ClientCreatedDTO;
import com.charge.chargeManeger.business.service.ClienteService;
import com.charge.chargeManeger.api.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Objetivo: Interceptar requisições destinadas
* a manipulação dos clientes do sistema
* */
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /*
    * Objetivo: Mapear e encaminhar a solicitação de cadastro
    * de cliente
    * */
    @PostMapping
    public ResponseEntity<ApiResponse<ClientCreatedDTO>> criar(@RequestBody ClienteDTO dto) throws Exception {
        String idCliente = clienteService.registrarNovoCliente(dto);

        return ResponseEntity.ok(
                ApiResponse.sucesso("Cliente cadastrado com sucesso", new ClientCreatedDTO(idCliente)));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @PutMapping()
    public ResponseEntity<String> atualizar( @RequestBody ClienteDTO dto) {
        clienteService.atualizarCliente(dto);
        return ResponseEntity.ok("Cliente atualizado com sucesso!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.ok("Cliente deletado com sucesso!");
    }
}