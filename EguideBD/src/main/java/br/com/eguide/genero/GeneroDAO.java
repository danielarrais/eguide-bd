
package br.com.eguide.genero;

import java.util.List;

public interface GeneroDAO {
    public void salvar(Genero genero);
    public void atualizar(Genero genero);
    public void excluir(Genero genero);
    public Genero buscar(Integer genero);
    public List<Genero> listar();
}
