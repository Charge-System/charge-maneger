package com.charge.chargeManeger.infra.datasource;

import com.charge.chargeManeger.api.dto.MessageDTO;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseManager {

    private final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/charge_db";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "postgres";

    public Connection getConnection() {

        Connection conx = null;

        try {
            conx = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //Criando instrução
            Statement statemente = conx.createStatement();

            //Cria tabela se ela não existir
            String createTableSQL = "CREATE TABLE IF NOT EXISTS message(id INT PRIMARY KEY, message VARCHAR(50))";
            statemente.execute(createTableSQL);
            System.out.println("Tabela 'message' criada!");


        } catch (Exception ex) {
            if (ex instanceof GeneralSecurityException) {
                // Tratar erro
            }
        }

        return conx;
    }

    public MessageDTO getMessageFromDb() {
        String sql = "SELECT id, message FROM message LIMIT 1";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return new MessageDTO(rs.getInt("id"), rs.getString("message"));
            }

        } catch (Exception e) {
           //tratar
        }

        return new MessageDTO(0, "Nenhuma mensagem encontrada");
    }
}
