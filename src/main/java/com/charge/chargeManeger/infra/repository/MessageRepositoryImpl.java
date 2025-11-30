package com.charge.chargeManeger.infra.repository;

import com.charge.chargeManeger.api.dto.MessageDTO;
import com.charge.chargeManeger.business.ports.MessageRepository;
import com.charge.chargeManeger.infra.datasource.DataBaseManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private final String SQL_CONSULTAR_MESSAGE = "SELECT id, message FROM message LIMIT 1";

    @Override
    public MessageDTO getMessageFromDb() {

        try (Connection con = DataBaseManager.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_CONSULTAR_MESSAGE)) {

            if (rs.next()) {
                return new MessageDTO(rs.getInt("id"), rs.getString("message"));
            }

        } catch (Exception e) {
            //tratar
        }

        return new MessageDTO(0, "Nenhuma mensagem encontrada");
    }
}
