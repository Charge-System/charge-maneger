package com.charge.chargeManeger.infra.datasource;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseManager {

    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/charge_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    public static Connection getConnection() {

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
}
