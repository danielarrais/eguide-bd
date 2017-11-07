package br.com.eguide.idioma;

import br.com.eguide.util.DAOFactory;
import java.util.List;

public class IdiomaRN {

    IdiomaDAO idiomaDAO;

    public IdiomaRN() {
        idiomaDAO = DAOFactory.criaIdiomaDAO();
    }

    public Idioma buscar(Integer id) {
        return idiomaDAO.buscar(id);
    }

    public void salvar(Idioma idioma) {
        Integer codigo = idioma.getId();
        if (codigo == null || codigo == 0) {
            idiomaDAO.salvar(idioma);
        } else {
            idiomaDAO.atualizar(idioma);
        }
    }

    public void excluir(Idioma idioma) {
        try {
            idiomaDAO.excluir(idioma);
        } catch (Exception e) {
        }
    }

    public List<Idioma> listar() {
        return idiomaDAO.listar();
    }
}
