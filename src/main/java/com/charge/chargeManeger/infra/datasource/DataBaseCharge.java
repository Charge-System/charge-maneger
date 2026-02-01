package com.charge.chargeManeger.infra.datasource;

import com.charge.chargeManeger.api.dto.enums.StatusCobranca;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DataBaseCharge implements CommandLineRunner {

    // Efetua a criação das tabelas caso não existam (DESENVOLVIMENTO)
    @Override
    public void run(String... args) throws Exception {
        try (Connection conx = DataBaseManager.getConnection();
             Statement statement = conx.createStatement()) {

            // 1. Tabela de Cliente (Pai)
            String createClienteTable = "CREATE TABLE IF NOT EXISTS cliente (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL, " +
                    "cpfCnpj VARCHAR(100) NOT NULL, " +
                    "telefone VARCHAR(100), " +
                    "idASAAS VARCHAR(100) UNIQUE NOT NULL " +
                    ")";
            statement.execute(createClienteTable);
            System.out.println("Tabela 'cliente' verificada.");

            // 2. Tabela de Cobranca (Filha - Depende de cliente)
            String createCobrancaTable = "CREATE TABLE IF NOT EXISTS cobranca (" +
                    "id SERIAL PRIMARY KEY, " +
                    "valor DECIMAL(10,2), " +
                    "tipoCobranca VARCHAR(20), " +
                    "dataVencimento DATE, " +
                    "status VARCHAR(100) DEFAULT 'PENDING',  " +
                    "idCobrancaAsaas VARCHAR(100), " +
                    "idClienteAsaas VARCHAR(100) NOT NULL, " +
                    "CONSTRAINT fk_cliente FOREIGN KEY (idClienteAsaas) REFERENCES cliente(idASAAS) ON DELETE CASCADE" +
                    ")";
            statement.execute(createCobrancaTable);
            System.out.println("Tabela 'cobranca' verificada.");

        } catch (SQLException ex) {
            System.err.println("ERRO NA INICIALIZAÇÃO DO BANCO: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
