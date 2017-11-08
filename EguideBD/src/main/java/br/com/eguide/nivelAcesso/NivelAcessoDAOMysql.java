package br.com.eguide.nivelAcesso;

import br.com.eguide.usuario.Usuario;
import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NivelAcessoDAOMysql implements NivelAcessoDAO {

    private Connection connection;

    @Override
    public void salvar(NivelAcesso nivelAcesso) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `nivelacesso` (`nivel`, `descricao`) VALUES (?, ?); ";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, nivelAcesso.getNivel());
            cadastro.setString(2, nivelAcesso.getDescricao());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao salvar nivel de acesso. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(NivelAcesso nivelAcesso) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `nivelacesso` SET `nivel` = ?, `descricao` = ? WHERE `id_nivel` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, nivelAcesso.getNivel());
            cadastro.setString(2, nivelAcesso.getDescricao());
            cadastro.setInt(3, nivelAcesso.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar nivel de acesso. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(NivelAcesso nivelAcesso) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `nivelacesso` WHERE id_nivel = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, nivelAcesso.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir nivel de acesso. Erro: " + e.getMessage());
        }
    }

    @Override
    public NivelAcesso buscar(Integer nivelID) {
        NivelAcesso nivelAcesso = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM nivelacesso WHERE id_nivel = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, nivelID);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                nivelAcesso = new NivelAcesso(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar nivel de acesso. Erro: " + e.getMessage());
        }
        return nivelAcesso;
    }

    @Override
    public List<NivelAcesso> listar() {
        List<NivelAcesso> lista = new ArrayList<NivelAcesso>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM nivelacesso";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new NivelAcesso(resultado.getInt(1), resultado.getString(2), resultado.getString(3)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar niveis de acesso. Erro: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Set<NivelAcesso> listar(Integer usuario) {
        Set<NivelAcesso> lista = new HashSet<NivelAcesso>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "select na1.* from usuario u "
                    + "inner join nivel_usuario nu1 on u.id_usuario = nu1.id_usuario "
                    + "inner join nivelacesso na1 on nu1.id_nivel = na1.id_nivel and u.id_usuario = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, usuario);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new NivelAcesso(resultado.getInt(1), resultado.getString(2), resultado.getString(3)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar niveis de acesso do usuario. Erro: " + e.getMessage());
        }
        return lista;
    }

    public void salvarNiveis(Usuario usuario) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `nivel_usuario` (`id_usuario`, `id_nivel`) VALUES (?, ?)";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            for (NivelAcesso nivelAcesso : usuario.getNivelAcesso()) {
                cadastro.setInt(1, usuario.getId());
                cadastro.setInt(2, nivelAcesso.getId());
                cadastro.addBatch();
            }
            cadastro.executeBatch();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao salvar niveis de acesso. Erro: " + e.getMessage());
        }
    }
    @Override
    public void exluirNiveis(Usuario usuario) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `nivel_usuario` WHERE id_usuario = ? and id_nivel = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            for (NivelAcesso nivelAcesso : usuario.getNivelAcesso()) {
                cadastro.setInt(1, usuario.getId());
                cadastro.setInt(2, nivelAcesso.getId());
                cadastro.addBatch();
            }
            cadastro.executeBatch();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao deletar niveis de acesso. Erro: " + e.getMessage());
        }
    }
    
    @Override
    public void atualizarNiveis(Usuario usuario){
        exluirNiveis(usuario);
        salvarNiveis(usuario);
    }

    @Override
    public NivelAcesso buscar(String nivelAcesso) {
        NivelAcesso nivel = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM nivelacesso WHERE nivel = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setString(1, nivelAcesso);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                nivel = new NivelAcesso(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar nivel de acesso. Erro: " + e.getMessage());
        }
        return nivel;
    }
}
