package br.com.eguide.usuario;

import br.com.eguide.util.DAOFactory;
import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOMysql implements UsuarioDAO {

    private Connection connection;

    @Override
    public void salvar(Usuario usuario) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `usuario` (`email`, `emailSec`, `nascimento`, `nome`, `senha`, `sobrenome`) VALUES (?, ?, ?, ?, SHA1(?), ?)";
            PreparedStatement cadastro = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            cadastro.setString(1, usuario.getEmail());
            cadastro.setString(2, usuario.getEmailSec());
            cadastro.setDate(3, new Date(usuario.getNascimento().getTime()));
            cadastro.setString(4, usuario.getNome());
            cadastro.setString(5, usuario.getSenha());
            cadastro.setString(6, usuario.getSobrenome());
            cadastro.executeUpdate();
            ResultSet rs = cadastro.getGeneratedKeys();
            rs.next();
            usuario.setId(rs.getInt(1));
            DAOFactory.criaNivelAcessoDAO().salvarNiveis(usuario);
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao salvar usuario. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        atualizar(usuario, false);
    }

    @Override
    public void atualizar(Usuario usuario, Boolean sha1) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `usuario` SET `email` = ?, `emailSec` = ?, `nascimento` = ?, `nome` = ?, `senha` = "+(sha1?"SHA1(?)":"?")+", `sobrenome` = ? WHERE `id_usuario` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, usuario.getEmail());
            cadastro.setString(2, usuario.getEmailSec());
            cadastro.setDate(3, new Date(usuario.getNascimento().getTime()));
            cadastro.setString(4, usuario.getNome());
            cadastro.setString(5, usuario.getSenha());
            cadastro.setString(6, usuario.getSobrenome());
            cadastro.setInt(7, usuario.getId());
            cadastro.executeUpdate();
            DAOFactory.criaNivelAcessoDAO().atualizarNiveis(usuario);
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar usuario. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Usuario usuario) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `usuario` WHERE id_usuario = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, usuario.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir usuario. Erro: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscar(Integer usuarioId) {
        Usuario usuario = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, usuarioId);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                usuario = new Usuario(
                        resultado.getInt(1),
                        resultado.getString(5),
                        resultado.getString(7),
                        resultado.getString(2),
                        resultado.getString(3),
                        resultado.getString(6),
                        resultado.getDate(4), DAOFactory.criaNivelAcessoDAO().listar(resultado.getInt(1)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario. Erro: " + e.getMessage());
        }
        return usuario;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        Usuario usuario = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM usuario WHERE email = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setString(1, email);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                usuario = new Usuario(
                        resultado.getInt(1),
                        resultado.getString(5),
                        resultado.getString(7),
                        resultado.getString(2),
                        resultado.getString(3),
                        resultado.getString(6),
                        resultado.getDate(4), DAOFactory.criaNivelAcessoDAO().listar(resultado.getInt(1)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario por email. Erro: " + e.getMessage());
        }
        return usuario;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM usuario";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new Usuario(
                        resultado.getInt(1),
                        resultado.getString(5),
                        resultado.getString(7),
                        resultado.getString(2),
                        resultado.getString(3),
                        resultado.getString(6),
                        resultado.getDate(4), DAOFactory.criaNivelAcessoDAO().listar(resultado.getInt(1))));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar usuarios. Erro: " + e.getMessage());
        }
        return lista;
    }

}
