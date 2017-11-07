
package br.com.eguide.origem;

import java.util.List;

public interface OrigemDAO {
    public void salvar(Origem origem);
    public void atualizar(Origem origem);
    public void excluir(Origem origem);
    public Origem buscar(Integer origem);
    public List<Origem> listar();
}
