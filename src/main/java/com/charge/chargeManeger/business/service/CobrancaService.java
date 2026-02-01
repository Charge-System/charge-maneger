package com.charge.chargeManeger.business.service;

import com.charge.chargeManeger.GerarCobrancaRequest;
import com.charge.chargeManeger.GerarCobrancaResponse;
import com.charge.chargeManeger.api.dto.ClienteDTO;
import com.charge.chargeManeger.api.dto.CobrancaDTO;
import com.charge.chargeManeger.api.dto.enums.StatusCobranca;
import com.charge.chargeManeger.business.constants.Global;
import com.charge.chargeManeger.business.ports.ClienteRepository;
import com.charge.chargeManeger.business.ports.CobrancaRepository;
import com.charge.chargeManeger.business.util.DataUtil;
import com.charge.chargeManeger.infra.webservice.ClienteSoapCobranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CobrancaService {

    // Servicos auxiliares
    private final CobrancaRepository cobrancaRepository;

    private final ClienteSoapCobranca clienteSoapCobranca;

    private final ClienteService clienteService;

    private final EmailService emailService;

    @Autowired
    public CobrancaService(CobrancaRepository cobrancaRepository, ClienteSoapCobranca clienteSoapCobranca, ClienteService clienteService, EmailService emailService) {
        this.cobrancaRepository = cobrancaRepository;
        this.clienteSoapCobranca = clienteSoapCobranca;
        this.clienteService = clienteService;
        this.emailService = emailService;
    }

    /*
    * Objetivo: Aplicar as regras/validações e efetuar
    * a geração de uma cobrança no sistema
    * */
    public String gerarCobranca(CobrancaDTO dto) throws Exception {
        // Validando dados para cadastro da cobranca
        validarCobrancaGerar(dto);

        // Validando o cliente que está associado a emissão
        validarClienteCobranca(dto.idClienteAsaas());

        String idCobrancaAsaas = gerarCobrancaAsass(dto);

        // Montando objeto para persisitir a cobrança no sistema
        CobrancaDTO cobrancaGravar = new CobrancaDTO(
                null,
                dto.valor(),
                dto.dataVencimento(),
                dto.tipoCobranca(),
                dto.statusCobranca(),
                idCobrancaAsaas,
                dto.idClienteAsaas());

        // Cadastrando a cobrança para um cliente
        cobrancaRepository.criarCobranca(cobrancaGravar);

        // Obtendo email do cliente para notifica-lo
        ClienteDTO clienteNotificar = clienteService.consultarClientePorIdAsaas(dto.idClienteAsaas());

        // Notificando o cliente sobre a cobrança gerada
        emailService.enviarEmail(
                clienteNotificar.email(), "Cobrança", "Uma cobrança no valor de " + dto.valor() + " foi gerada no seu nome");

        return idCobrancaAsaas;
    }

    /*
    * Objetivo: Configurar e chamar serviço SOAP
    * responsável pela geração da cobrança no
    * ASAAS
    * */
    private String gerarCobrancaAsass(CobrancaDTO dto) throws Exception {
        // Montando request para gerar cobrança
        GerarCobrancaRequest gerarCobrancaRequest = montarRequetGerarCobranca(dto);

        // Efetuando geração da cobrança no ASAAS
        GerarCobrancaResponse responseCadastroCob =
                clienteSoapCobranca.gerarCobranca(gerarCobrancaRequest);

        if (responseCadastroCob == null) {
            throw new Exception("Não foi possível efetuar o cadastro da cobrança no ASAAS");
        }

        return responseCadastroCob.getId();
    }

    /*
    * Objetivo: Atualizar o status de uma cobrança,
    * para tal, que informará as atualizações será
    * o ASAAS
    * */
    public void atualizarStatusCobranca(CobrancaDTO dto) throws Exception {
        // Validndo informações para atualização
        validarAtualizacaoStatus(dto);

        String idCobranca = dto.idCobrancaAsaas();
        StatusCobranca statusCobranca = dto.statusCobranca();

        // Efetuando alteração na base de dados
        cobrancaRepository.atualizarStatusCobranca(idCobranca, statusCobranca);
    }

    private void validarAtualizacaoStatus(CobrancaDTO dto) throws Exception {
        if (dto.idCobrancaAsaas() == null || dto.statusCobranca() == null) {
            throw new Exception("Informações insuficientes para alterar status da cobrança");
        }
    }

    /*
    * Objetivo: Validar os dodos que serão utilizados
    * no cadastro da cobrança
    * */
    private void validarCobrancaGerar(CobrancaDTO dto) throws Exception {
        if (dto.tipoCobranca() == null || dto.valor() == null
                || dto.dataVencimento() == null  || dto.idClienteAsaas() == null) {
            throw new Exception("Informações insuficientes");
        }

        if (dto.valor() <= Global.VALOR_MINIMO_GERACAO_COBRANCA) {
            throw new Exception("Valor insuficiente para efetuar cobrança");
        }
    }

    /*
    * Objetivo: Validar se, para um dada cobrança, o cliente
    * a ela associado existe na base de dados, caso contrário,
    * a geração não será permitida
    * */
    private void validarClienteCobranca(String idClienteAsass) throws Exception {
        ClienteDTO clienteCobranca = clienteService.consultarClientePorIdAsaas(idClienteAsass);

        if (clienteCobranca == null) {
            throw new Exception("Não existe cliente compatível com as informações para gerar a cobrança");
        }
    }

    /*
    * Objetivo: Montar objeto de requisição para comunicação
    * com o proxy (via SOAP)
    * */
    private GerarCobrancaRequest montarRequetGerarCobranca(CobrancaDTO dto) {
        GerarCobrancaRequest request = new GerarCobrancaRequest();

        request.setValue(dto.valor());
        request.setCustomer(dto.idClienteAsaas());
        request.setBillingType(dto.tipoCobranca().toString());
        request.setDueDate(DataUtil.converterDataString(dto.dataVencimento()));

        return request;
    }

    public List<CobrancaDTO> listarCobrancasPorCliente(Long clienteId) {
        return cobrancaRepository.buscarPorCliente(clienteId);
    }

    public List<CobrancaDTO> listarCobrancas() {
        return cobrancaRepository.listarCobrancas();
    }

    public void removerCobranca(Long id) {
        cobrancaRepository.removerCobranca(id);
    }

    public void editarCobranca(CobrancaDTO dto) {
        cobrancaRepository.atualizarCobranca(dto);
    }

}
