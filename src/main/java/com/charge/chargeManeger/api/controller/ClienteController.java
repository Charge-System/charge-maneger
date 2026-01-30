package com.charge.chargeManeger.api.controller;

import com.charge.chargeManeger.business.service.ClienteService;
import com.charge.chargeManeger.api.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody ClienteDTO dto) {
        clienteService.registrarNovoCliente(dto);
        return ResponseEntity.ok("Cliente criado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }
}