package com.charge.chargeManeger.infra.repository;

import com.charge.chargeManeger.api.dto.CobrancaDTO;
import com.charge.chargeManeger.api.dto.enums.Enums;
import com.charge.chargeManeger.business.ports.CobrancaRepository;
import com.charge.chargeManeger.infra.datasource.DataBaseManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CobrancaRepositoryImpl implements CobrancaRepository {

    public void criarCobranca(CobrancaDTO dto) {
        String sql = "INSERT INTO cobranca (value,name, billing_type, charge_type, cliente_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, dto.value());
            stmt.setString(2, dto.name());
            stmt.setString(3, dto.billingType().name());
            stmt.setString(4, dto.chargeType().name());
            stmt.setLong(5, dto.clienteId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CobrancaDTO> buscarPorCliente(Long clienteId) {
        String sql = "SELECT * FROM cobranca WHERE cliente_id = ?";
        List<CobrancaDTO> cobrancas = new ArrayList<>();

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cobrancas.add(new CobrancaDTO(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("value"),
                            Enums.BillingType.valueOf(rs.getString("billing_type")),
                            Enums.ChargeType.valueOf(rs.getString("charge_type")),
                            rs.getLong("cliente_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cobranças do cliente", e);
        }
        return cobrancas;
    }
    @Override
    public List<CobrancaDTO> listarCobrancas() {
        String sql = "SELECT * FROM cobranca";
        List<CobrancaDTO> cobrancas = new ArrayList<>();

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cobrancas.add(new CobrancaDTO(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getBigDecimal("value"),
                            Enums.BillingType.valueOf(rs.getString("billing_type")),
                            Enums.ChargeType.valueOf(rs.getString("charge_type")),
                            rs.getLong("cliente_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cobrancas", e);
        }
        return cobrancas;
    }
}