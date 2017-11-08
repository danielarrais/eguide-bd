
package br.com.eguide.livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LivroDAO {
    public void salvar(Livro livro);
    public void atualizar(Livro livro);
    public void excluir(Livro livro);
    public Livro buscar(Integer livro);
    public Livro buscarISBN(Integer livro);
    public Livro buscarISBN(Long livro);
    public List<Livro> listar();
    public List<Livro> filtarLivros(Map<String, ArrayList<String>> objects, Map<String, ArrayList<String>> criterios);
    public Set<Object> valores(String coluna, boolean repetidos);
}
