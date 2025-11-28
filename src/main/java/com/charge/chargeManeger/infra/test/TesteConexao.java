package com.charge.chargeManeger.infra.test;

import com.charge.chargeManeger.infra.datasource.DataBaseManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class TesteConexao implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("OBTENDO CONEXÃO COM A BASE DE DADOS - START");

        DataBaseManager dbm = new DataBaseManager();
        Connection conx = dbm.getConnection();

        if (conx == null) {
            System.out.println("ERRO AO CONECTAR");
        }

        System.out.println("OBTENDO CONEXÃO COM A BASE DE DADOS - FIM");
    }
}
