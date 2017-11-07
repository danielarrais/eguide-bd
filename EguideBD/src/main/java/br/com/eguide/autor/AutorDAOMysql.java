package br.com.eguide.autor;

import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOMysql implements AutorDAO {

    private Connection connection;

    @Override
    public void salvar(Autor autor) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `eguide`.`autor` (`nome`, `nomeAbnt`) VALUES (?, ?)";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, autor.getNome());
            cadastro.setString(2, autor.getNomeAbnt());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao salvar autor. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Autor autor) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `eguide`.`autor` SET `nome` = ?, `nomeAbnt` = ? WHERE `id_autor` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, autor.getNome());
            cadastro.setString(2, autor.getNomeAbnt());
            cadastro.setInt(3, autor.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar autor. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Autor autor) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `autor` WHERE id_autor = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, autor.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir autor. Erro: " + e.getMessage());
        }
    }

    @Override
    public Autor buscar(Integer autorID) {
        Autor autor = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM autor WHERE id_autor = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, autorID);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                autor = new Autor(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar autor. Erro: " + e.getMessage());
        }
        return autor;
    }

    @Override
    public List<Autor> listar() {
        List<Autor> lista = new ArrayList<Autor>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM autor";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new Autor(resultado.getInt(1), resultado.getString(2), resultado.getString(3)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar autores. Erro: " + e.getMessage());
        }
        return lista;
    }

}
