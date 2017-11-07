package br.com.eguide.editora;

import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAOMysql implements EditoraDAO {

    private Connection connection;

    @Override
    public void salvar(Editora editora) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `editora` (`nome`) VALUES (?)";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, editora.getNome());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao salvar editora. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Editora editora) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `editora` SET `nome` = ? WHERE `id_editora` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, editora.getNome());
            cadastro.setInt(2, editora.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar editora. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Editora editora) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `editora` WHERE id_editora = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, editora.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir editora. Erro: " + e.getMessage());
        }
    }

    @Override
    public Editora buscar(Integer editoraID) {
        Editora editora = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM editora WHERE id_editora = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, editoraID);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                editora = new Editora(resultado.getInt(1), resultado.getString(2));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar editora. Erro: " + e.getMessage());
        }
        return editora;
    }

    @Override
    public List<Editora> listar() {
        List<Editora> lista = new ArrayList<Editora>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM editora";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new Editora(resultado.getInt(1), resultado.getString(2)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar editoras. Erro: " + e.getMessage());
        }
        return lista;
    }
}
