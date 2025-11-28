package com.charge.chargeManeger.infra.datasource;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseManager {

    private final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/charge_db";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "postgres";

    public Connection getConnection() {

        Connection conx = null;

        try {
            conx = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        } catch (Exception ex) {
            if (ex instanceof GeneralSecurityException) {
                // Tratar erro
            }
        }

        return conx;
    }
}
