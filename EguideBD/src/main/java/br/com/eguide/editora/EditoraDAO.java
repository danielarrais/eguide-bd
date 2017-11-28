
package br.com.eguide.editora;

import br.com.eguide.usuario.Usuario;
import java.util.List;

public interface EditoraDAO {
    public void salvar(Editora editora);
    public void atualizar(Editora editora);
    public void excluir(Editora editora);
    public Editora buscar(Integer editora);
    public List<Editora> listar();
}
