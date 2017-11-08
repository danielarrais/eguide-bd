package br.com.eguide.genero;

import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAOMysql implements GeneroDAO {

    private Connection connection;

    @Override
    public void salvar(Genero genero) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `genero` (`nome`) VALUES (?)";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, genero.getNome());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao salvar genero. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Genero genero) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `genero` SET `nome` = ? WHERE `id_genero` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, genero.getNome());
            cadastro.setInt(2, genero.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar genero. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Genero genero) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `genero` WHERE id_genero = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, genero.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir genero. Erro: " + e.getMessage());
        }
    }

    @Override
    public Genero buscar(Integer generoID) {
        Genero genero = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM genero WHERE id_genero = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, generoID);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                genero = new Genero(resultado.getInt(1), resultado.getString(2));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar genero. Erro: " + e.getMessage());
        }
        return genero;
    }

    @Override
    public List<Genero> listar() {
        List<Genero> lista = new ArrayList<Genero>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM genero";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new Genero(resultado.getInt(1), resultado.getString(2)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar generos. Erro: " + e.getMessage());
        }
        return lista;
    }
}
