package br.com.eguide.subgenero;

import br.com.eguide.origem.Origem;
import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubgeneroDAOMysql implements SubgeneroDAO {

    private Connection connection;

    @Override
    public void salvar(Subgenero subgenero) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `subgenero` (`nome`, `genero_id_genero`) VALUES (?, ?)";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, subgenero.getNome());
            cadastro.setInt(2, subgenero.getGenero().getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao salvar subgenero. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Subgenero subgenero) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `subgenero` SET `nome` = ?, `genero_id_genero` = ? WHERE `id_subgenero` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, subgenero.getNome());
            cadastro.setInt(2, subgenero.getGenero().getId());
            cadastro.setInt(3, subgenero.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar subgenero. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Subgenero subgenero) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `subgenero` WHERE id_subgenero = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, subgenero.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir subgenero. Erro: " + e.getMessage());
        }
    }

    @Override
    public Subgenero buscar(Integer subgeneroID) {
        Subgenero subgenero = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM subgenero WHERE id_subgenero = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, subgeneroID);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                subgenero = new Subgenero(resultado.getInt(1), resultado.getString(2));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar subgenero. Erro: " + e.getMessage());
        }
        return subgenero;
    }

    @Override
    public List<Subgenero> listar() {
        List<Subgenero> lista = new ArrayList<Subgenero>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM subgenero";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new Subgenero(resultado.getInt(1), resultado.getString(2)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar subgeneros. Erro: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Subgenero> listarSubgenerosGenero(Integer genero) {
        return null;
    }
}