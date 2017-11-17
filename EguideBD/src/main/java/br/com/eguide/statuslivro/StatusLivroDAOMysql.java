/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.statuslivro;

import br.com.eguide.genero.Genero;
import br.com.eguide.livro.Livro;
import br.com.eguide.usuario.Usuario;
import br.com.eguide.util.DAOFactory;
import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<Integer, ArrayList<Livro>> listarPorUsuario(Integer usuario) {
        ArrayList<Livro> livrosLidos = new ArrayList<Livro>();
        ArrayList<Livro> livrosQuero = new ArrayList<Livro>();
        ArrayList<Livro> livrosLendo = new ArrayList<Livro>();
        Map<Integer, ArrayList<Livro>> statusLivros = new HashMap<Integer, ArrayList<Livro>>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT distinct STATUSLIVRO.*, LIVRO.* FROM estante ESTANTE "
                    + "INNER JOIN statuslivro STATUSLIVRO on ESTANTE.id_status = STATUSLIVRO.id_status "
                    + "INNER JOIN usuario USUARIO on ESTANTE.id_usuario = USUARIO.id_usuario "
                    + "INNER JOIN livro LIVRO on ESTANTE.id_livro = LIVRO.id_livro AND USUARIO.id_usuario = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, usuario);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Livro livro = new Livro();
                livro.setId(resultado.getInt(3));
                livro.setIsbn13(resultado.getLong(8));
                livro.setNome(resultado.getString(9));
                livro.setAutor(DAOFactory.criaAutorDAO().listar(resultado.getInt(1)));
                switch (resultado.getInt(1)) {
                    case StatusLivro.LENDO:
                        livrosLendo.add(livro);
                        break;
                    case StatusLivro.LIDO:
                        livrosLidos.add(livro);
                        break;
                    case StatusLivro.QUEROLER:
                        livrosQuero.add(livro);
                        break;
                }
            }
            statusLivros.put(StatusLivro.LENDO, livrosLendo);
            statusLivros.put(StatusLivro.LIDO, livrosLidos);
            statusLivros.put(StatusLivro.QUEROLER, livrosQuero);
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar status de livros do usuario. Erro: " + e.getMessage());
        }
        return statusLivros;
    }

    @Override
    public void alterarStatus(Integer idUsuario, Integer idStatusLivro, Integer idLivro) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO estante (id_livro, id_usuario, id_status) VALUES (?,?,?) ON DUPLICATE KEY UPDATE id_status=?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, idLivro);
            cadastro.setInt(2, idUsuario);
            cadastro.setInt(3, idStatusLivro);
            cadastro.setInt(4, idStatusLivro);
            cadastro.executeUpdate();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (SQLException e) {
            System.err.println("Erro ao alterar status. Erro: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StatusLivroRN livroRN = new StatusLivroRN();
        livroRN.alterarStatus(1, 2, 1);
//        System.out.println(livroRN.listarPorUsuario(1).get(1).get(0).getNome());
    }

    @Override
    public StatusLivro buscar(Livro livro, Usuario usuario) {
        StatusLivro status = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT distinct STATUSLIVRO.* FROM estante ESTANTE "
                    + "INNER JOIN statuslivro STATUSLIVRO on ESTANTE.id_status = STATUSLIVRO.id_status "
                    + "INNER JOIN usuario USUARIO on ESTANTE.id_usuario = USUARIO.id_usuario "
                    + "INNER JOIN LIVRO on ESTANTE.id_livro = LIVRO.id_livro AND LIVRO.id_livro = ? AND USUARIO.id_usuario = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, livro.getId());
            consulta.setInt(2, usuario.getId());
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

}
