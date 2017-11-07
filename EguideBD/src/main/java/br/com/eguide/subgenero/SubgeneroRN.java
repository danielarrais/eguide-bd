package br.com.eguide.subgenero;

import br.com.eguide.util.DAOFactory;
import java.util.List;

public class SubgeneroRN {
    SubgeneroDAO origemDAO;
    
    public SubgeneroRN(){
        origemDAO = DAOFactory.criaSubgeneroDAO();
    }
    public Subgenero buscar(Integer id){
        return origemDAO.buscar(id);
    }
    public void salvar(Subgenero origem){
        Integer codigo = origem.getId();
        if (codigo==null || codigo==0) {
            origemDAO.salvar(origem);
        }else{
            origemDAO.atualizar(origem);
        }
    }
    public void excluir(Subgenero origem){
        origemDAO.excluir(origem);
    }
    public List<Subgenero> listar(){
        return origemDAO.listar();
    }
    public List<Subgenero> listarPorGenero(Integer genero){
        return origemDAO.listarSubgenerosGenero(genero);
    }
}
