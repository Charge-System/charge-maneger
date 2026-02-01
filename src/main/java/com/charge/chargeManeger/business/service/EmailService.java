package com.charge.chargeManeger.business.service;

import com.charge.chargeManeger.business.util.ValidadorUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/*
* Objetivo: Realizar as regras que envolvem
* o tratamento de emails no sistema, como
* exemplo, o envio deles a um dado cliente
* */
@Service
public class EmailService {

    // Injetando serviço responsável pelo envio do email
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Objetivo: Enviar um email com o link para o site que
     * continuará com as etapas de alteração de senha
     *
     * @param emailClienteEnviar contendo o email que recebrá as
     *                    informações
     *
     * @param titulo contendo o título do email
     *
     * @param mensagem contendo a mensagem do email
     * */
    public void enviarEmail(String emailClienteEnviar, String titulo, String mensagem) {
        //Validando email
        ValidadorUtil.validarEmail(emailClienteEnviar);

        // Mensagem que será enviada
        SimpleMailMessage mensagemRecuperar = new SimpleMailMessage();

        mensagemRecuperar.setTo(emailClienteEnviar);
        mensagemRecuperar.setSubject(titulo);
        mensagemRecuperar.setText(mensagem);

        mailSender.send(mensagemRecuperar);
    }
}
