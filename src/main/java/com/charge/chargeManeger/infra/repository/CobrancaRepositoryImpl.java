package com.charge.chargeManeger.infra.repository;

import com.charge.chargeManeger.api.dto.CobrancaDTO;
import com.charge.chargeManeger.api.dto.enums.StatusCobranca;
import com.charge.chargeManeger.api.dto.enums.TipoCobranca;
import com.charge.chargeManeger.business.ports.CobrancaRepository;
import com.charge.chargeManeger.business.util.DataUtil;
import com.charge.chargeManeger.infra.datasource.DataBaseManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CobrancaRepositoryImpl implements CobrancaRepository {

    public void criarCobranca(CobrancaDTO dto) {
        String sql = "INSERT INTO cobranca (valor, tipoCobranca, dataVencimento, status, idCobrancaAsaas, idClienteAsaas) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, dto.valor());
            stmt.setString(2, dto.tipoCobranca().toString());

            // Alterando data para persistência
            Date dataSQL = Date.valueOf(DataUtil.converterDataString(dto.dataVencimento()));

            stmt.setDate(3, dataSQL);
            stmt.setString(4, dto.idCobrancaAsaas());
            stmt.setString(5, dto.idCobrancaAsaas());
            stmt.setString(6, dto.idClienteAsaas());

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
                            rs.getDouble("valor"),
                            rs.getDate("dataVencimento"),
                            TipoCobranca.valueOf(rs.getString("tipoCobranca")),
                            StatusCobranca.valueOf(rs.getString("status")),
                            rs.getString("idCobrancaAsaas"),
                            rs.getString("idClienteAsaas")
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
                            rs.getDouble("valor"),
                            rs.getDate("dataVencimento"),
                            TipoCobranca.valueOf(rs.getString("tipoCobranca")),
                            StatusCobranca.valueOf(rs.getString("status")),
                            rs.getString("idCobrancaAsaas"),
                            rs.getString("idClienteAsaas")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cobrancas", e);
        }
        return cobrancas;
    }
    @Override
    public void removerCobranca(Long idCobranca) {
        String sql = "DELETE FROM cobranca WHERE id = ?";
        try{Connection conn = DataBaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, idCobranca);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao remover cobrança", e);
        }
    }

    @Override
    public void atualizarStatusCobranca(String idCobrancaAsaas, StatusCobranca statusCharge) {
        String sql = "UPDATE cobranca SET status = ? WHERE idCobrancaAsaas = ?";

        try{Connection conn = DataBaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, statusCharge.toString());
            stmt.setString(2, idCobrancaAsaas);

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status da cobrança" + e.getMessage());
        }
    }

    @Override
    public void atualizarCobranca(CobrancaDTO cobrancaDTO) {

    }
}