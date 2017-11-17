/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.avaliacao;

import br.com.eguide.autor.Autor;
import br.com.eguide.editora.Editora;
import br.com.eguide.idioma.Idioma;
import br.com.eguide.livro.Livro;
import br.com.eguide.origem.Origem;
import br.com.eguide.usuario.Usuario;
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
public class AvaliacaoDAOMysql implements AvaliacaoDAO {

    private Connection connection;

    @Override
    public void salvar(Avaliacao avaliacao) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `avaliacao` (`comentario`, `nota`, `id_usuario`, `id_livro`) VALUES (?,?,?,?)";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, avaliacao.getComentario());
            cadastro.setDouble(2, avaliacao.getNota());
            cadastro.setInt(3, avaliacao.getUsuario().getId());
            cadastro.setInt(4, avaliacao.getLivro().getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao salvar avaliação. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Avaliacao avaliacao) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `avaliacao` SET `comentario` = ?, `nota` = ?, `id_usuario` = ?, `id_livro` = ? WHERE `id_avaliacao` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, avaliacao.getComentario());
            cadastro.setDouble(2, avaliacao.getNota());
            cadastro.setInt(3, avaliacao.getId());
            cadastro.setInt(4, avaliacao.getLivro().getId());
            cadastro.setInt(5, avaliacao.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar avaliação. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Avaliacao avaliacao) {
         try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `avaliacao` WHERE id_avaliacao = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, avaliacao.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir avaliacao. Erro: " + e.getMessage());
        }
    }

    @Override
    public Avaliacao buscar(Integer avaliacaoID) {
        Avaliacao avaliacao = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "select distinct AVALIACAO.*, USUARIO.*, LIVRO.* FROM avaliacao AVALIACAO\n" +
"inner join  usuario USUARIO on AVALIACAO.id_usuario = USUARIO.id_usuario\n" +
"INNER JOIN livro LIVRO on AVALIACAO.id_livro = LIVRO.id_livro and AVALIACAO.id_avaliacao = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, avaliacaoID);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                Usuario usuario = new Usuario(
                        resultado.getInt(6),
                        resultado.getString(10),
                        resultado.getString(12),
                        resultado.getString(7),
                        resultado.getString(8),
                        resultado.getString(11),
                        resultado.getDate(9), null);
                
                Livro livro = new Livro(
                            resultado.getInt(13),
                            resultado.getString(19),
                            resultado.getString(15),
                            resultado.getInt(14),
                            resultado.getInt(17),
                            resultado.getLong(18),
                            resultado.getInt(20),
                            resultado.getInt(16),
                            null, null, null, null, null);
                avaliacao = new Avaliacao(resultado.getInt(1), resultado.getString(2), resultado.getDouble(3), usuario, livro);
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar avaliacao. Erro: " + e.getMessage());
        }
        return avaliacao;
    }

    @Override
    public List<Avaliacao> listar(Livro livro) {
        ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "select distinct AVALIACAO.*, USUARIO.*, LIVRO.* FROM avaliacao AVALIACAO\n" +
"inner join  usuario USUARIO on AVALIACAO.id_usuario = USUARIO.id_usuario\n" +
"INNER JOIN livro LIVRO on AVALIACAO.id_livro = LIVRO.id_livro and LIVRO.id_livro = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, livro.getId());
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario(
                        resultado.getInt(6),
                        resultado.getString(10),
                        resultado.getString(12),
                        resultado.getString(7),
                        resultado.getString(8),
                        resultado.getString(11),
                        resultado.getDate(9), null);
                
                Livro livroNovo = new Livro(
                            resultado.getInt(13),
                            resultado.getString(19),
                            resultado.getString(15),
                            resultado.getInt(14),
                            resultado.getInt(17),
                            resultado.getLong(18),
                            resultado.getInt(20),
                            resultado.getInt(16),
                            null, null, null, null, null);
                avaliacoes.add(new Avaliacao(resultado.getInt(1), resultado.getString(2), resultado.getDouble(3), usuario, livroNovo));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar avaliações do livro. Erro: " + e.getMessage());
        }
        return avaliacoes;
    }

}
