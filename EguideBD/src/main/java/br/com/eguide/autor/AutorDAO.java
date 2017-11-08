
package br.com.eguide.autor;

import java.util.List;

public interface AutorDAO {
    public void salvar(Autor autor);
    public void atualizar(Autor autor);
    public void excluir(Autor autor);
    public Autor buscar(Integer autor);
    public List<Autor> listar();
    public List<Autor> listar(int livro);
}
