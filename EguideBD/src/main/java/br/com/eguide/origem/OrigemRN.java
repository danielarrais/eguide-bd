
package br.com.eguide.origem;

import br.com.eguide.util.DAOFactory;
import java.util.List;

public class OrigemRN {
    OrigemDAO origemDAO;
    
    public OrigemRN(){
        origemDAO = DAOFactory.criaOrigemDAO();
    }
    public Origem buscar(Integer id){
        return origemDAO.buscar(id);
    }
    public void salvar(Origem origem){
        Integer codigo = origem.getId();
        if (codigo==null || codigo==0) {
            origemDAO.salvar(origem);
        }else{
            origemDAO.atualizar(origem);
        }
    }
    public void excluir(Origem origem){
        origemDAO.excluir(origem);
    }
    public List<Origem> listar(){
        return origemDAO.listar();
    }
}
