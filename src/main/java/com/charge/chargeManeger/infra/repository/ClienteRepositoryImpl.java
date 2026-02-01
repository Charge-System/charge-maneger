package com.charge.chargeManeger.infra.repository;

import com.charge.chargeManeger.business.ports.ClienteRepository;
import com.charge.chargeManeger.api.dto.ClienteDTO;
import com.charge.chargeManeger.infra.datasource.DataBaseManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
* Objetivo: Realizar a comunicação com a base de dados
* e manter os mesmos
* */
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public Integer salvar(ClienteDTO cliente) {
        String sql = "INSERT INTO cliente (nome, email, cpfCnpj, telefone, idASAAS) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.nome());
            stmt.setString(2, cliente.email());
            stmt.setString(3, cliente.cpfCnpj());
            stmt.setString(4, cliente.telefone());
            stmt.setString(5, cliente.idASAAS());

            int idCliente = stmt.executeUpdate();

            return idCliente;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar cliente" + ex.getMessage());
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
                        rs.getString("email"),
                        rs.getString("cpfCnpj"),
                        rs.getString("telefone"),
                        rs.getString("idASAAS")
                ));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar clientes" + ex.getMessage());
        }

        return lista;
    }

    @Override
    public void atualizar(ClienteDTO cliente) {
        String sql = "UPDATE cliente SET nome = ?, email = ?, cpfCnpj = ?, telefone = ? WHERE idASAAS = ?";
        try (Connection conn = DataBaseManager.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.nome());
            stmt.setString(2, cliente.email());
            stmt.setString(3, cliente.cpfCnpj());
            stmt.setString(4, cliente.telefone());
            stmt.setString(5, cliente.idASAAS());
            stmt.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar cliente");
        }
    }

    @Override
    public ClienteDTO consultarClientePorIdAsaas(String idClienteAsaas) {
        String sql = "SELECT * FROM cliente WHERE idAsaas = ?";

        ClienteDTO cliente = null;

        try(Connection conn = DataBaseManager.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idClienteAsaas);

            ResultSet rset = stmt.executeQuery();

            if (rset.next()) {
                cliente =  new ClienteDTO(
                        rset.getInt("id"),
                        rset.getString("nome"),
                        rset.getString("email"),
                        rset.getString("cpfCnpj"),
                        rset.getString("telefone"),
                        rset.getString("idASAAS")
                );
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao consultar cliente" + e.getMessage());
        }

        return cliente;
    }

    public ClienteDTO consultarClientePorEmail(String email) {
        String sql = "SELECT * FROM cliente WHERE email = ?";

        ClienteDTO cliente = null;

        try(Connection conn = DataBaseManager.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rset = stmt.executeQuery();

            if (rset.next()) {
                cliente =  new ClienteDTO(
                        rset.getInt("id"),
                        rset.getString("nome"),
                        rset.getString("email"),
                        rset.getString("cpfCnpj"),
                        rset.getString("telefone"),
                        rset.getString("idASAAS")
                );
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao consultar cliente" + e.getMessage());
        }

        return cliente;
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