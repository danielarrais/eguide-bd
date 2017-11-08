package br.com.eguide.livro;

import br.com.eguide.util.DAOFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LivroRN {

    LivroDAO livroDAO;

    public LivroRN() {
        livroDAO = DAOFactory.criaLivroDAO();
    }

    public Livro buscar(Integer id) {
        return livroDAO.buscar(id);
    }

    public Livro buscarISBN10(Integer ISBN) {
        return livroDAO.buscarISBN(ISBN);
    }

    public Livro buscarISBN13(Long ISBN) {
        return livroDAO.buscarISBN(ISBN);
    }

    public void salvar(Livro livro) {
        Integer codigo = livro.getId();
        if (codigo == null || codigo == 0) {
            livroDAO.salvar(livro);
        } else {
            livroDAO.atualizar(livro);
        }
    }

    public void excluir(Livro livro) {
        livroDAO.excluir(livro);
    }

    public List<Livro> listar() {
        return livroDAO.listar();
    }

    public List<Livro> listaEspecial(Map<String, ArrayList<Object>> objects) {
        return livroDAO.listarEspecial(objects);
    }
}
