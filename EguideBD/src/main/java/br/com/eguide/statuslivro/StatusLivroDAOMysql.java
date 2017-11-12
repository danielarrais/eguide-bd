/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.statuslivro;

import br.com.eguide.genero.Genero;
import br.com.eguide.util.DAOFactory;
import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class StatusLivroDAOMysql implements StatusLivroDAO {

    private Connection connection;

    @Override
    public void salvar(StatusLivro status) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `statuslivro` (`status`) VALUES (?)";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, status.getNome());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao salvar status. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(StatusLivro status) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `statuslivro` SET `nome` = ? WHERE `id_status` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, status.getNome());
            cadastro.setInt(2, status.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar status. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(StatusLivro status) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `statuslivro` WHERE id_status = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, status.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir status. Erro: " + e.getMessage());
        }
    }

    @Override
    public StatusLivro buscar(Integer statusID) {
        StatusLivro status = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM statuslivro WHERE id_status = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, statusID);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                status = new StatusLivro(resultado.getInt(1), resultado.getString(2));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar status. Erro: " + e.getMessage());
        }
        return status;
    }

    @Override
    public List<StatusLivro> listar() {
        List<StatusLivro> lista = new ArrayList<StatusLivro>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM statuslivro";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new StatusLivro(resultado.getInt(1), resultado.getString(2)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar status. Erro: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<StatusLivro> listarPorUsuario(Integer usuario) {
        List<StatusLivro> lista = new ArrayList<StatusLivro>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT distinct STATUSLIVRO.*, ESTANTE.*, USUARIO.* FROM estante ESTANTE\n"
                    + "INNER JOIN statuslivro STATUSLIVRO on ESTANTE.id_status = STATUSLIVRO.id_status\n"
                    + "INNER JOIN usuario USUARIO on ESTANTE.id_usuario = USUARIO.id_usuario\n"
                    + "INNER JOIN livro LIVRO on ESTANTE.id_livro = LIVRO.id_livro AND USUARIO.id_usuario = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new StatusLivro(resultado.getInt(1), resultado.getString(2)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar status de livros do usuario. Erro: " + e.getMessage());
        }
        return lista;
    }

}
