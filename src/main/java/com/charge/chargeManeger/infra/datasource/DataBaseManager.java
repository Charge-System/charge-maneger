package com.charge.chargeManeger.infra.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DataBaseManager {

    private static DataSource dataSource = null;

    @Autowired
    public DataBaseManager(DataSource dataSource) {
        this.dataSource = dataSource;
        initDatabase(); // inicializa a tabela
    }

    private void initDatabase() {
        try (Connection conx = dataSource.getConnection();
             Statement statement = conx.createStatement()) {

            // 1. Tabela de Cliente (Pai)
            String createClienteTable = "CREATE TABLE IF NOT EXISTS cliente (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL" +
                    ")";
            statement.execute(createClienteTable);
            System.out.println("Tabela 'cliente' verificada.");

            // 2. Tabela de Cobranca (Filha - Depende de cliente)
            String createCobrancaTable = "CREATE TABLE IF NOT EXISTS cobranca (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(20), " +
                    "value DECIMAL(10,2), " +
                    "billing_type VARCHAR(20), " +
                    "charge_type VARCHAR(20), " +
                    "cliente_id INTEGER, " +
                    "CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE" +
                    ")";
            statement.execute(createCobrancaTable);
            System.out.println("Tabela 'cobranca' verificada.");

            // 3. Tabela de Message (Opcional/Antiga)
            String createMessageTable = "CREATE TABLE IF NOT EXISTS message(" +
                    "id SERIAL PRIMARY KEY, " +
                    "message VARCHAR(50)" +
                    ")";
            statement.execute(createMessageTable);

        } catch (SQLException ex) {
            System.err.println("ERRO NA INICIALIZAÇÃO DO BANCO: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
