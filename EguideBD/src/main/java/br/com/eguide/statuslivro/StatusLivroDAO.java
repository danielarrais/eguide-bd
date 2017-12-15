
package br.com.eguide.statuslivro;

import br.com.eguide.livro.Livro;
import br.com.eguide.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StatusLivroDAO {
    public void salvar(StatusLivro status);
    public void atualizar(StatusLivro status);
    public void excluir(StatusLivro status);
    public StatusLivro buscar(Integer status);
    public StatusLivro buscar(Livro livro, Usuario usuario);
    public List<StatusLivro> listar();
    public Map<Integer, ArrayList<Livro>> listarPorUsuario(Integer livro);
    public void alterarStatus(Integer idUsuario, Integer idStatusLivro, Integer idLivro);
}
