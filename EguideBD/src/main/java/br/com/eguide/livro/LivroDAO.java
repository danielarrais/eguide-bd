
package br.com.eguide.livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

public interface LivroDAO {
    public void salvar(Livro livro);
    public void atualizar(Livro livro);
    public void excluir(Livro livro);
    public Livro buscar(Integer livro);
    public Livro buscarISBN10(Integer livro);
    public Livro buscarISBN13(Long livro);
    public List<Livro> listar();
    public List<Livro> listarEspecial(Map<String, ArrayList<Object>> objects);
}
