
package br.com.eguide.origem;

import br.com.eguide.nivelAcesso.NivelAcesso;
import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrigemDAOMysql implements OrigemDAO{
    private Connection connection;

    @Override
    public void salvar(Origem origem) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `origem` (`origem`) VALUES (?); ";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, origem.getOrigem());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao salvar origem. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Origem origem) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `origem` SET `origem` = ? WHERE `id_origem` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, origem.getOrigem());
            cadastro.setInt(2, origem.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar origem. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Origem origem) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `origem` WHERE id_origem = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, origem.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir origem. Erro: " + e.getMessage());
        }
    }

    @Override
    public Origem buscar(Integer origemID) {
        Origem origem = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM origem WHERE id_origem = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, origemID);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                origem = new Origem(resultado.getInt(1), resultado.getString(2));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar origem. Erro: " + e.getMessage());
        }
        return origem;
    }

    @Override
    public List<Origem> listar() {
        List<Origem> lista = new ArrayList<Origem>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM origem";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new Origem(resultado.getInt(1), resultado.getString(2)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar origens. Erro: " + e.getMessage());
        }
        return lista;
    }
}
