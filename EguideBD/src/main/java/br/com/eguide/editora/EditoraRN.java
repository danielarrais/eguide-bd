
package br.com.eguide.editora;

import br.com.eguide.util.DAOFactory;
import java.util.List;

public class EditoraRN {
    EditoraDAO editoraDAO;
    
    public EditoraRN(){
        editoraDAO = DAOFactory.criaEditoraDAO();
    }
    public Editora buscar(Integer id){
        return editoraDAO.buscar(id);
    }
    public void salvar(Editora editora){
        Integer codigo = editora.getId();
        if (codigo==null || codigo==0) {
            editoraDAO.salvar(editora);
        }else{
            editoraDAO.atualizar(editora);
        }
    }
    public void excluir(Editora editora){
        editoraDAO.excluir(editora);
    }
    public List<Editora> listar(){
        return editoraDAO.listar();
    }
}
