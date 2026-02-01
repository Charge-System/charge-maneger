package com.charge.chargeManeger.infra.queue.consumer;

import com.charge.chargeManeger.api.dto.CobrancaDTO;
import com.charge.chargeManeger.api.dto.enums.StatusCobranca;
import com.charge.chargeManeger.business.service.ClienteService;
import com.charge.chargeManeger.business.service.CobrancaService;
import com.charge.chargeManeger.infra.queue.config.RabbitMQConfig;
import com.charge.chargeManeger.infra.queue.dto.MessageQueueDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    // Serviço que opera o cliente
    private final CobrancaService cobrancaService;

    public QueueConsumer(CobrancaService cobrancaService) {
        this.cobrancaService = cobrancaService;
    }

    /**
    * Objetivo: receber as mensagens da fila
    *
    * @param mensagem sendo do tipo String com o formato
     *                 JSON respeitando a estrutura de exemplo:
     *
     *                {
     *                  "idCobrancaAsaas": "pay_kjfb235jkbfw",
     *                  "statusCobranca": UNKNOW
     *                }
    * */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receberMensagem(Message mensagem) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            MessageQueueDTO mensagemRecebida = objectMapper.readValue(mensagem.getBody(), MessageQueueDTO.class);

            // Após receber mensagem, montando o DTO de envio do serviço
            CobrancaDTO cobrancaDTO =
                    new CobrancaDTO(null, null, null, null,
                            StatusCobranca.valueOf(mensagemRecebida.getStatusCobranca()),
                            mensagemRecebida.getIdCobrancaAsaas(),
                            null);

            cobrancaService.atualizarStatusCobranca(cobrancaDTO);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao receber mensagem" + ex.getMessage());
        }
    }
}
