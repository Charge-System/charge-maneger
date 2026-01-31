package com.charge.chargeManeger.infra.repository;

import com.charge.chargeManeger.business.ports.ClienteRepository;
import com.charge.chargeManeger.api.dto.ClienteDTO;
import com.charge.chargeManeger.infra.datasource.DataBaseManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public void salvar(ClienteDTO cliente) {
        String sql = "INSERT INTO cliente (nome, email) VALUES (?, ?)";

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.nome());
            stmt.setString(2, cliente.email());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente", e);
        }
    }

    @Override
    public List<ClienteDTO> listarTodos() {
        String sql = "SELECT * FROM cliente";
        List<ClienteDTO> lista = new ArrayList<>();

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new ClienteDTO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes", e);
        }
        return lista;
    }
    @Override
    public void atualizar(ClienteDTO cliente) {
        String sql = "UPDATE cliente SET nome = ?, email = ? WHERE id = ?";
        try (Connection conn = DataBaseManager.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.nome());
            stmt.setString(2, cliente.email());
            stmt.setLong(3, cliente.id());
            stmt.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar cliente");
        }
    }
    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try(Connection conn = DataBaseManager.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException("Erro ao deletar cliente");
        }
    }
}