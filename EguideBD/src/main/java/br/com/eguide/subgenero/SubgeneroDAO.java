package br.com.eguide.subgenero;

import java.util.List;

public interface SubgeneroDAO {
    public void salvar(Subgenero subgenero);
    public void atualizar(Subgenero subgenero);
    public void excluir(Subgenero subgenero);
    public Subgenero buscar(Integer subgenero);
    public List<Subgenero> listar();
    public List<Subgenero> listarSubgenerosGenero(Integer genero);
}
