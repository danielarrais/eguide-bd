
package br.com.eguide.genero;

import br.com.eguide.subgenero.Subgenero;
import java.util.List;

public interface GeneroDAO {
    public void salvar(Genero genero);
    public void atualizar(Genero genero);
    public void excluir(Genero genero);
    public Genero buscar(Integer genero, boolean criterio);
    public List<Genero> listar();
    public Genero buscar(Integer subgeneroID);
}
