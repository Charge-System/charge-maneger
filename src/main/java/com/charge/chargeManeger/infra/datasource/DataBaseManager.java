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

            String createTableSQL = "CREATE TABLE IF NOT EXISTS message(" +
                    "id SERIAL PRIMARY KEY, " +
                    "message VARCHAR(50)" +
                    ")";
            statement.execute(createTableSQL);
            System.out.println("Tabela 'message' criada ou já existia!");

            String insertDefaultMessageSQL =
                    "INSERT INTO message(message) " +
                            "SELECT 'Teste de comunicação entre camadas' " +
                            "WHERE NOT EXISTS (SELECT 1 FROM message)";
            statement.execute(insertDefaultMessageSQL);
            System.out.println("Mensagem inserida com sucesso!");


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
