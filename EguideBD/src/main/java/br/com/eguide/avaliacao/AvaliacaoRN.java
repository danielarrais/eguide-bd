package br.com.eguide.avaliacao;

import br.com.eguide.livro.Livro;
import br.com.eguide.util.DAOFactory;
import java.util.List;

public class AvaliacaoRN implements AvaliacaoDAO{

    @Override
    public void salvar(Avaliacao avaliacao) {
        DAOFactory.criaAvaliacaoDAO().salvar(avaliacao);
    }

    @Override
    public void atualizar(Avaliacao avaliacao) {
        DAOFactory.criaAvaliacaoDAO().atualizar(avaliacao);
    }

    @Override
    public void excluir(Avaliacao avaliacao) {
        DAOFactory.criaAvaliacaoDAO().excluir(avaliacao);
    }

    @Override
    public Avaliacao buscar(Integer avaliacao) {
        return DAOFactory.criaAvaliacaoDAO().buscar(avaliacao);
    }

    @Override
    public List<Avaliacao> listar(Livro livro) {
        return DAOFactory.criaAvaliacaoDAO().listar(livro);
    }
    
}
