package br.com.eguide.idioma;

import br.com.eguide.genero.Genero;
import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class IdiomaDAOMysql implements IdiomaDAO {

    private Connection connection;

    @Override
    public void salvar(Idioma idioma) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `idioma` (`nome`, `sigla`) VALUES (?, ?)";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, idioma.getNome());
            cadastro.setString(2, idioma.getSigla());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao salvar idioma. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Idioma idioma) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `idioma` SET `nome` = ?, `sigla` = ? WHERE `id_idioma` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, idioma.getNome());
            cadastro.setString(2, idioma.getSigla());
            cadastro.setInt(3, idioma.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar idioma. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Idioma idioma) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `idioma` WHERE id_idioma = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, idioma.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir idioma. Erro: " + e.getMessage());
        }
    }

    @Override
    public Idioma buscar(Integer idiomaID) {
        Idioma idioma = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM idioma WHERE id_idioma = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, idiomaID);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                idioma = new Idioma(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar idioma. Erro: " + e.getMessage());
        }
        return idioma;
    }

    @Override
    public List<Idioma> listar() {
        List<Idioma> lista = new ArrayList<Idioma>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM idioma";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new Idioma(resultado.getInt(1), resultado.getString(2), resultado.getString(3)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar idioma. Erro: " + e.getMessage());
        }
        return lista;
    }
}
