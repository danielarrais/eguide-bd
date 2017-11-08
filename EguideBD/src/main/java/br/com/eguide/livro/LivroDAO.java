
package br.com.eguide.livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LivroDAO {
    public void salvar(Livro livro);
    public void atualizar(Livro livro);
    public void excluir(Livro livro);
    public Livro buscar(Integer livro);
    public Livro buscarISBN(Integer livro);
    public Livro buscarISBN(Long livro);
    public List<Livro> listar();
    public List<Livro> listarEspecial(Map<String, ArrayList<Object>> objects);
}
