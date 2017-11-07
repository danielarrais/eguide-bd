package br.com.eguide.livro;

import br.com.eguide.autor.Autor;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class LivroDAOHibernate implements LivroDAO {

    private Connection connection;

    public void setSession(Connection connection) {
        
    }

    @Override
    public void salvar(Livro livro) {

    }

    @Override
    public void atualizar(Livro livro) {

    }

    @Override
    public void excluir(Livro livro) {

    }

    @Override
    public Livro buscar(Integer livro) {
        return null;
    }

    @Override
    public Livro buscarISBN10(Integer isbn) {
        return null;
    }

    @Override
    public Livro buscarISBN13(Long isbn) {
        return null;
    }

    @Override
    public List<Livro> listar() {
        return null;
    }

    @Override
    public List<Livro> listarEspecial(Map<String, ArrayList<Object>> objects) {
        return null;
    }
}
