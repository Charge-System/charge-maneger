package com.charge.chargeManeger.infra.test;

import com.charge.chargeManeger.infra.datasource.DataBaseManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Statement;

@Component
public class TesteConexao implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("OBTENDO CONEXÃO COM A BASE DE DADOS - START");

        DataBaseManager dbm = new DataBaseManager();
        Connection conx = dbm.getConnection();

        //usado para enviar instruções SQL
        Statement statement = conx.createStatement();

        try(conx; statement){
            String message1 = "INSERT INTO message(id, message) " +
                            "VALUES (1, 'Olá, mundo') " +
                            "ON CONFLICT (id) DO NOTHING";
            statement.executeUpdate(message1);
            System.out.println("Mensagem registrada no banco"+ message1);

            String message2 = "INSERT INTO message(id, message) " +
                    "VALUES (2, 'Divamos') " +
                    "ON CONFLICT (id) DO NOTHING";
            statement.executeUpdate(message2);
            System.out.println("Mensagem registrada no banco"+ message2);
        }

        catch (Exception e){
            //tratar
        }
        System.out.println("INICIANDO TESTE DE CONEXÃO - FIM");
    }
}


