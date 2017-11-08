package br.com.eguide.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MysqlUtil {

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/eguide?useTimezone&serverTimezone=UTC&useSSL=false";
    private static String USUARIO = "root";
    private static String SENHA = "root";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MysqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void closeConnection(Connection conexao) {

        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar conexao", ex);
        }

    }

    public static void closeConnection(Connection conexao, PreparedStatement statement) {
        MysqlUtil.closeConnection(conexao);
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar PreparedStatement", ex);
        }
    }

    public static void closeConnection(Connection conexao, PreparedStatement statement, ResultSet result) {
        MysqlUtil.closeConnection(conexao, statement);
        try {
            if (result != null) {
                result.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar ResultSet", ex);
        }
    }

}
