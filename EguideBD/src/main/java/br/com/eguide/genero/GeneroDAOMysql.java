package br.com.eguide.genero;

import br.com.eguide.subgenero.Subgenero;
import br.com.eguide.util.DAOFactory;
import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GeneroDAOMysql implements GeneroDAO {

    private Connection connection;

    @Override
    public void salvar(Genero genero) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `genero` (`nome`) VALUES (?)";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, genero.getNomeGenero());
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
            cadastro.setString(1, genero.getNomeGenero());
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
    public Genero buscar(Integer id, boolean criterio) {
        Genero genero = null;
        String sql = "";
        try {
            connection = MysqlUtil.getConnection();
            if (criterio) {
                sql = "SELECT g.* FROM genero g, subgenero s where s.genero_id_genero = g.id_genero and s.id_subgenero=?";
            }else{
                sql = "SELECT * FROM genero WHERE id_genero = ?";
            }
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, id);
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
    public Genero buscar(Integer generoID) {
        return buscar(generoID, Genero.GENERO);
    }

    @Override
    public List<Genero> listar() {
        ArrayList<Genero> listaSet = new ArrayList<Genero>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM genero";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                Genero genero = new Genero(resultado.getInt(1), resultado.getString(2));
                listaSet.add(genero);
                genero.setSubgeneros(DAOFactory.criaSubgeneroDAO().listarSubgenerosGenero(genero.getId()));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar generos. Erro: " + e.getMessage());
        }
        return listaSet;
    }
}
