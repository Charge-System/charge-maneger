package com.charge.chargeManeger.api.controller;

import com.charge.chargeManeger.api.controller.api.ApiResponse;
import com.charge.chargeManeger.api.dto.CobrancaCreatedDTO;
import com.charge.chargeManeger.api.dto.CobrancaDTO;
import com.charge.chargeManeger.business.service.CobrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cobranca")
public class CobrancaController {

    private final CobrancaService cobrancaService;

    public CobrancaController(CobrancaService cobrancaService) {
        this.cobrancaService = cobrancaService;
    }

    /*
     * Objetivo: Mapear e encaminhar a solicitação de cadastro
     * de cliente
     * */
    @PostMapping
    public ResponseEntity<ApiResponse<CobrancaCreatedDTO>> cadastrarCobranca(@RequestBody CobrancaDTO dto) throws Exception {
        String idCobrancaAsaas = cobrancaService.gerarCobranca(dto);

        return ResponseEntity.ok(
                ApiResponse.sucesso("Cobrança efetuada com sucesso", new CobrancaCreatedDTO(idCobrancaAsaas)));
    }

    @GetMapping()
    public ResponseEntity<List<CobrancaDTO>> listarCobrancas() {
        return ResponseEntity.ok(cobrancaService.listarCobrancas());
    }
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CobrancaDTO>> buscarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(cobrancaService.listarCobrancasPorCliente(clienteId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCobranca(@PathVariable Long id) {
        cobrancaService.removerCobranca(id);
        return ResponseEntity.ok("Cobranca deletada com sucesso!");
    }
    @PutMapping()
    public ResponseEntity<String> atualizarCobranca(@RequestBody CobrancaDTO dto) {
        cobrancaService.editarCobranca(dto);
        return ResponseEntity.ok("Cobranca atualizada com sucesso!");
    }
}