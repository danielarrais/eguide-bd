
package br.com.eguide.genero;

import br.com.eguide.util.DAOFactory;
import java.util.List;

public class GeneroRN {
    GeneroDAO generoDAO;
    
    public GeneroRN(){
        generoDAO = DAOFactory.criaGeneroDAO();
    }
    public Genero buscar(Integer id){
        return generoDAO.buscar(id);
    }
    public void salvar(Genero genero){
        Integer codigo = genero.getId();
        if (codigo==null || codigo==0) {
            generoDAO.salvar(genero);
        }else{
            generoDAO.atualizar(genero);
        }
    }
    public void excluir(Genero genero){
        generoDAO.excluir(genero);
    }
    public List<Genero> listar(){
        return generoDAO.listar();
    }
}
