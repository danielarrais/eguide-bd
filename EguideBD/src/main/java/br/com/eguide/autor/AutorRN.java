
package br.com.eguide.autor;

import br.com.eguide.util.DAOFactory;
import java.util.List;

public class AutorRN {
    AutorDAO autorDAO;
    
    public AutorRN(){
        autorDAO = DAOFactory.criaAutorDAO();
    }
    public Autor buscar(Integer id){
        return autorDAO.buscar(id);
    }
    public void salvar(Autor autor){
        Integer codigo = autor.getId();
        if (codigo==null || codigo==0) {
            autorDAO.salvar(autor);
        }else{
            autorDAO.atualizar(autor);
        }
    }
    public void excluir(Autor autor){
        autorDAO.excluir(autor);
    }
    public List<Autor> listar(){
        return autorDAO.listar();
    }
}
