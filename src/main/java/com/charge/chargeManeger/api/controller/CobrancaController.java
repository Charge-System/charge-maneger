package com.charge.chargeManeger.api.controller;

import com.charge.chargeManeger.api.dto.CobrancaDTO;
import com.charge.chargeManeger.business.service.CobrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cobrancas")
public class CobrancaController {

    private final CobrancaService cobrancaService;

    @Autowired
    public CobrancaController(CobrancaService cobrancaService) {
        this.cobrancaService = cobrancaService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrarCobranca(@RequestBody CobrancaDTO dto) {
        cobrancaService.gerarCobranca(dto);
        return ResponseEntity.ok("Cobrança registrada para o cliente " + dto.clienteId());
    }

    @GetMapping()
    public ResponseEntity<List<CobrancaDTO>> listarCobrancas() {
        return ResponseEntity.ok(cobrancaService.listarCobrancas());
    }
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CobrancaDTO>> buscarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(cobrancaService.listarCobrancasPorCliente(clienteId));
    }
}