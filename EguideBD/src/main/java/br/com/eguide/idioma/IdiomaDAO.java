
package br.com.eguide.idioma;

import java.util.List;

public interface IdiomaDAO {
    public void salvar(Idioma idioma);
    public void atualizar(Idioma idioma);
    public void excluir(Idioma idioma);
    public Idioma buscar(Integer idioma);
    public List<Idioma> listar();
}
