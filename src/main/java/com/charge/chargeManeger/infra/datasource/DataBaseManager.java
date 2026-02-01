package com.charge.chargeManeger.infra.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
* Objetivo: Gerenciar as conexões com o banco de
* dados do sistema
* */
@Component
public class DataBaseManager {

    private static DataSource dataSource = null;

    @Autowired
    public DataBaseManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
    * Objtivo: Obter uma conexão com o banco de dados
    * */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /*
     * Objtivo: Fechar uma conexão com o banco de dados
     * */
    public static void fecharConexão(Connection connection) throws SQLException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new SQLException("Não foi possível fechar a conexão: " + ex.getMessage());
        }

    }
}
