package br.com.eguide.subgenero;

import br.com.eguide.genero.Genero;
import br.com.eguide.origem.Origem;
import br.com.eguide.util.DAOFactory;
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
            cadastro.setString(1, subgenero.getNomeSubgenero());
            cadastro.setInt(2, subgenero.getId());
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
            cadastro.setString(1, subgenero.getNomeSubgenero());
            cadastro.setInt(2, subgenero.getId());
            cadastro.setInt(3, subgenero.getIdSub());
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
            cadastro.setInt(1, subgenero.getIdSub());
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
                subgenero = new Subgenero(resultado.getInt(1), resultado.getString(2), DAOFactory.criaGeneroDAO().buscar(resultado.getInt(1), Genero.SUBGENERO));
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
            String sql = "SELECT distinct * FROM subgenero SUB \n"
                    + "inner join genero GEN on GEN.id_genero = SUB.genero_id_genero ORDER BY GEN.nome";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new Subgenero(resultado.getInt(1), resultado.getString(2), new Genero(resultado.getInt(4), resultado.getString(5))));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar subgeneros. Erro: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Subgenero> listarSubgenerosGenero(Integer genero) {
        List<Subgenero> lista = new ArrayList<Subgenero>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT distinct SUBGENERO.* FROM subgenero SUBGENERO "
                    + "inner join genero GENERO on GENERO.id_genero = SUBGENERO.genero_id_genero and GENERO.id_genero = ? ORDER BY nome";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, genero);
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
}
