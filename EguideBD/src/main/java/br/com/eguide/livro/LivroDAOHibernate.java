package br.com.eguide.livro;

import br.com.eguide.util.DAOFactory;
import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LivroDAOHibernate implements LivroDAO {

    private Connection connection;

    @Override
    public void salvar(Livro livro) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "INSERT INTO `eguide`.`livro` (`ano`, `descricao`, `edicao`, `isbn10`, `isbn13`, `nome`, `paginas`, `editora_id_editora`, `idioma_id_idioma`, `origem_id_origem`, `subgenero_id_subgenero`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setString(1, livro.getNome());
            cadastro.setString(2, livro.getDescricao());
            cadastro.setInt(3, livro.getIsbn10());
            cadastro.setLong(4, livro.getIsbn13());
            cadastro.setInt(5, livro.getPaginas());
            cadastro.setInt(6, livro.getEditora().getId());
            cadastro.setInt(7, livro.getIdioma().getId());
            cadastro.setInt(8, livro.getOrigem().getId());
            cadastro.setInt(9, livro.getSubgenero().getIdSub());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao salvar livro. Erro: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Livro livro) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "UPDATE `eguide`.`livro` SET `ano` = ?, `descricao` = ?, `edicao` = ?, `isbn10` = ?, `isbn13` = ?, `nome` = ?, `paginas` = ?, `editora_id_editora` = ?, `idioma_id_idioma` = ?, `origem_id_origem` = ?, `subgenero_id_subgenero` = ? WHERE `id_livro` = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, livro.getIsbn10());
            cadastro.setString(2, livro.getNome());
            cadastro.setString(3, livro.getDescricao());
            cadastro.setInt(4, livro.getIsbn10());
            cadastro.setLong(5, livro.getIsbn13());
            cadastro.setInt(6, livro.getPaginas());
            cadastro.setInt(7, livro.getEditora().getId());
            cadastro.setInt(8, livro.getIdioma().getId());
            cadastro.setInt(9, livro.getOrigem().getId());
            cadastro.setInt(10, livro.getSubgenero().getIdSub());
            cadastro.setInt(11, livro.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar livro. Erro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Livro livro) {
        try {
            connection = MysqlUtil.getConnection();
            String sql = "DELETE FROM `livro` WHERE id_livro = ?";
            PreparedStatement cadastro = connection.prepareStatement(sql);
            cadastro.setInt(1, livro.getId());
            cadastro.execute();
            MysqlUtil.closeConnection(connection, cadastro);
        } catch (Exception e) {
            System.err.println("Erro ao excluir livro. Erro: " + e.getMessage());
        }
    }

    @Override
    public Livro buscar(Integer livroID) {
        Livro livro = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM livro WHERE id_livro = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setInt(1, livroID);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                livro = new Livro(
                        resultado.getInt(1),
                        resultado.getString(7),
                        resultado.getString(3),
                        resultado.getInt(2),
                        resultado.getInt(5),
                        resultado.getLong(6),
                        resultado.getInt(8),
                        resultado.getInt(4),
                        DAOFactory.criaEditoraDAO().buscar(resultado.getInt(9)),
                        DAOFactory.criaSubgeneroDAO().buscar(resultado.getInt(12)),
                        DAOFactory.criaOrigemDAO().buscar(resultado.getInt(11)),
                        DAOFactory.criaIdiomaDAO().buscar(resultado.getInt(10)),
                        DAOFactory.criaAutorDAO().listar(resultado.getInt(1)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro. Erro: " + e.getMessage());
        }
        return livro;
    }

    @Override
    public Livro buscarISBN(Integer isbn) {
        return LivroDAOHibernate.this.buscarISBN(isbn.longValue());
    }

    @Override
    public Livro buscarISBN(Long isbn) {
        Livro livro = null;
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM livro WHERE isbn" + isbn.toString().length() + " = ?";
            PreparedStatement consulta = connection.prepareStatement(sql);
            consulta.setLong(1, isbn);
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                livro = new Livro(
                        resultado.getInt(1),
                        resultado.getString(7),
                        resultado.getString(3),
                        resultado.getInt(2),
                        resultado.getInt(5),
                        resultado.getLong(6),
                        resultado.getInt(8),
                        resultado.getInt(4),
                        DAOFactory.criaEditoraDAO().buscar(resultado.getInt(9)),
                        DAOFactory.criaSubgeneroDAO().buscar(resultado.getInt(12)),
                        DAOFactory.criaOrigemDAO().buscar(resultado.getInt(11)),
                        DAOFactory.criaIdiomaDAO().buscar(resultado.getInt(10)),
                        DAOFactory.criaAutorDAO().listar(resultado.getInt(1)));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro. Erro: " + e.getMessage());
        }
        return livro;
    }

    @Override
    public List<Livro> listar() {
        List<Livro> lista = new ArrayList<Livro>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "SELECT * FROM livro";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new Livro(
                        resultado.getInt(1),
                        resultado.getString(7),
                        resultado.getString(3),
                        resultado.getInt(2),
                        resultado.getInt(5),
                        resultado.getLong(6),
                        resultado.getInt(8),
                        resultado.getInt(4),
                        DAOFactory.criaEditoraDAO().buscar(resultado.getInt(9)),
                        DAOFactory.criaSubgeneroDAO().buscar(resultado.getInt(12)),
                        DAOFactory.criaOrigemDAO().buscar(resultado.getInt(11)),
                        DAOFactory.criaIdiomaDAO().buscar(resultado.getInt(10)),
                        DAOFactory.criaAutorDAO().listar(resultado.getInt(1))));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar livros. Erro: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Livro> filtarLivros(Map<String, ArrayList<String>> objects, Map<String, ArrayList<String>> criterios) {
        List<Livro> lista = new ArrayList<Livro>();
        try {
            connection = MysqlUtil.getConnection();
            String sql = "select distinct LIVRO.* from livro LIVRO  "
                    + "inner join autor_livro AUTOR_LIVRO on LIVRO.id_livro = AUTOR_LIVRO.id_livro "
                    + "inner join autor AUTOR on AUTOR_LIVRO.id_autor = AUTOR.id_autor "
                    + "inner join editora EDITORA on LIVRO.editora_id_editora = EDITORA.id_editora "
                    + "inner join origem ORIGEM on LIVRO.origem_id_origem = ORIGEM.id_origem "
                    + "inner join idioma IDIOMA on LIVRO.idioma_id_idioma = IDIOMA.id_idioma "
                    + "inner join subgenero SUBGENERO on LIVRO.subgenero_id_subgenero = SUBGENERO.id_subgenero ";
            
            for (String key : objects.keySet()) {
                if (!objects.get(key).isEmpty()) {
                    sql += " and " + key.toUpperCase() + ".id_"+key+" in " + arrayInIn(objects.get(key));
                }
            }
            for (String key : criterios.keySet()) {
                if (!criterios.get(key).isEmpty()) {
                    sql += " and LIVRO."+key+" in " + arrayInIn(criterios.get(key));
                }
            }
            System.out.println(sql);
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(new Livro(
                        resultado.getInt(1),
                        resultado.getString(7),
                        resultado.getString(3),
                        resultado.getInt(2),
                        resultado.getInt(5),
                        resultado.getLong(6),
                        resultado.getInt(8),
                        resultado.getInt(4),
                        DAOFactory.criaEditoraDAO().buscar(resultado.getInt(9)),
                        DAOFactory.criaSubgeneroDAO().buscar(resultado.getInt(12)),
                        DAOFactory.criaOrigemDAO().buscar(resultado.getInt(11)),
                        DAOFactory.criaIdiomaDAO().buscar(resultado.getInt(10)),
                        DAOFactory.criaAutorDAO().listar(resultado.getInt(1))));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao executar filtro. Erro: " + e.getMessage());
        }
        return lista;
    }
    
    @Override
    public Set<Object> valores(String coluna, boolean repetidos){
         Set<Object> lista = new HashSet<Object>();
         
        try {
            connection = MysqlUtil.getConnection();
            String sql = "select "+(repetidos==true?"distinct":"")+" "+coluna+" FROM livro";
            PreparedStatement consulta = connection.prepareStatement(sql);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                lista.add(resultado.getObject(1));
            }
            MysqlUtil.closeConnection(connection, consulta, resultado);
        } catch (Exception e) {
            System.out.println("Erro ao listar os(as) "+coluna+" da tabela livro. Erro: " + e.getMessage());
        }
        return lista;
    }
    
    public String arrayInIn(List<String> objects) {
        String in = "(";
        for (String object : objects) {
            if (objects.indexOf(object) == objects.size() - 1) {
                in += object + "";
            } else {
                in += object + ",";
            }
        }
        return in+")";
    }
}
