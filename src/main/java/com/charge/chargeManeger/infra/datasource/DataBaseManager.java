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
        int maxAttempts = 10;
        long sleepTimeMs = 5000;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                System.out.println("Tentativa de conexão com o banco de dados: " + attempt);
                conx = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                Statement statemente = conx.createStatement();
                String createTableSQL = "CREATE TABLE IF NOT EXISTS message(id INT PRIMARY KEY, message VARCHAR(50))";
                statemente.execute(createTableSQL);
                System.out.println("Tabela 'message' criada com sucesso!");

                return conx;

            } catch (Exception ex) {
                System.err.println("Falha na conexão ou criação da tabela. Tentando novamente em " + sleepTimeMs/1000 + " segundos...");
                try {
                    Thread.sleep(sleepTimeMs);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        System.err.println("Falha ao conectar e inicializar o banco de dados após " + maxAttempts + " tentativas.");
        return null;
    }
}
