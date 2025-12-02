package com.charge.chargeManeger.config; // Ou em um pacote apropriado

import com.charge.chargeManeger.infra.datasource.DataBaseManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Disparando a verificação e conexão inicial do banco de dados");
        try {
            DataBaseManager.getConnection();
            System.out.println("Conexão inicial e criação da tabela concluídas");
        } catch (Exception e) {
            System.err.println("Erro durante a inicialização do banco de dados");
            e.printStackTrace();
        }
    }
}