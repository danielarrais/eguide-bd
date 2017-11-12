
package br.com.eguide.statuslivro;

import java.util.List;

public interface StatusLivroDAO {
    public void salvar(StatusLivro status);
    public void atualizar(StatusLivro status);
    public void excluir(StatusLivro status);
    public StatusLivro buscar(Integer status);
    public List<StatusLivro> listar();
    public List<StatusLivro> listarPorUsuario(Integer livro);
}
