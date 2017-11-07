
package br.com.eguide.nivelAcesso;

import br.com.eguide.util.DAOFactory;
import java.util.List;

public class NivelAcessoRN{
    NivelAcessoDAO nivelAcessoDAO;

    public NivelAcessoRN() {
        nivelAcessoDAO = DAOFactory.criaNivelAcessoDAO();
    }
    
    public void salvar(NivelAcesso nivelAcesso) {
        nivelAcessoDAO.salvar(nivelAcesso);
    }
    public void atualizar(NivelAcesso nivelAcesso) {
        nivelAcessoDAO.atualizar(nivelAcesso);
    }

    public void excluir(NivelAcesso nivelAcesso) {
        nivelAcessoDAO.excluir(nivelAcesso);
    }
    public NivelAcesso buscar(Integer nivelAcesso) {
        return nivelAcessoDAO.buscar(nivelAcesso);
    }
    public List<NivelAcesso> listar() {
        return nivelAcessoDAO.listar();
    }

}
