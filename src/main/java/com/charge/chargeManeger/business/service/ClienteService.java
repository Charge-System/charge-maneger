package com.charge.chargeManeger.business.service;

import com.charge.chargeManeger.CriarClienteRequest;
import com.charge.chargeManeger.CriarClienteResponse;
import com.charge.chargeManeger.business.ports.ClienteRepository;
import com.charge.chargeManeger.api.dto.ClienteDTO;
import com.charge.chargeManeger.business.util.ValidadorUtil;
import com.charge.chargeManeger.infra.webservice.ClienteSoapClient;
import org.apache.cxf.endpoint.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    // Serviços auxiliares
    private final ClienteRepository clienteRepository;

    private final ClienteSoapClient clienteSoapClient;

    private final EmailService emailService;

    public ClienteService(ClienteRepository clienteRepository, ClienteSoapClient clienteSoapClient, EmailService emailService) {
        this.clienteRepository = clienteRepository;
        this.clienteSoapClient = clienteSoapClient;
        this.emailService = emailService;
    }

    /*
    * Objetivo: Validar os dados e cadastrar um
    * novo cliente tanto no ASAAS como no sistema
    * do charge maneger
    * */
    public String registrarNovoCliente(ClienteDTO dto) throws Exception {
        // Validando entrada fornecida ao serviço
        validarEntradaCadastroCliente(dto);

        // Efetuando cadastro do cliente no ASAAS
        String idClienteASAAS = cadastrarClienteASAAS(dto);

        // Criando cliente que será persistido na base do sistema
        ClienteDTO clientePersistir =
                new ClienteDTO(null, dto.nome(), dto.email(), dto.cpfCnpj(), dto.telefone(), idClienteASAAS);

        clienteRepository.salvar(clientePersistir);

        emailService.enviarEmail(
                dto.email(), "Cadastro no Charger Maneger", "Você foi registrado com sucesso!");

        return idClienteASAAS;
    }

    /**
     * Objetivo: Montar a requisição e chamar o serviço
     * SOAP (proxy) responsável pela integração com o
     * ASAAS
     *
     * @param dto contendo as informações do cliente a
     *            ser cadastrado na base da API e no
     *           ASAAS
     *
     * @return String com o id do cliente cadastrado
     * no ASAAS
     * */
    private String cadastrarClienteASAAS(ClienteDTO dto) throws Exception {
        // Obtendo objeto com as informações para o request
        CriarClienteRequest soapRequest = criarRequestSoapNovoCliente(dto);

        // EXECUTANDO CHAMADA AO PROXY VIA SOAP
        CriarClienteResponse response = clienteSoapClient.criarCliente(soapRequest);

        if (response == null) {
            throw new Exception("Ocorreu um erro ao cadastrar o cliente no ASAAS");
        }

        return response.getId();
    }

    /*
    * Objetivo: Montar o objeto de envio para realizar
    * chamanda do proxy (SOAP)
    * */
    private CriarClienteRequest criarRequestSoapNovoCliente(ClienteDTO dto) {
        CriarClienteRequest soapRequest = new CriarClienteRequest();

        // Montando request com os dados básicos esperados
        soapRequest.setName(dto.nome());
        soapRequest.setCpfCnpj(dto.cpfCnpj());
        soapRequest.setEmail(dto.email());

        return soapRequest;
    }

    /*
    * Objetivo: validar os campos enviados
    * para o cadastro de um novo cliente
    * */
    private void validarEntradaCadastroCliente(ClienteDTO dto) throws Exception {
        if (dto.email() == null || dto.cpfCnpj() == null || dto.nome() == null) {
            throw new Exception("Dados insuficientes para cadastrar um novo cliente");
        }

        if (!ValidadorUtil.validarEmail(dto.email())) {
            throw new Exception("Email inválido");
        }

        // Validando se existem cliente com esse email
        ClienteDTO clienteExistente = clienteRepository.consultarClientePorEmail(dto.email());

        if (clienteExistente != null) {
            throw new Exception("Já existe um cliente com esse e-mail");
        }
    }

    /*
    * Objetivo: Consultar um dado cliente pelo seu id ASAAS
    * */
    public ClienteDTO consultarClientePorIdAsaas(String idClienteAsaas) throws Exception {
        return clienteRepository.consultarClientePorIdAsaas(idClienteAsaas);
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