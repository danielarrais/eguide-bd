package br.com.eguide.usuario;

import br.com.eguide.nivelAcesso.NivelAcesso;
import br.com.eguide.util.DAOFactory;
import java.util.List;
import java.util.Set;
import org.apache.commons.codec.digest.DigestUtils;

public class UsuarioRN {
    private UsuarioDAO usuarioDAO;
    public UsuarioRN(){
        this.usuarioDAO = DAOFactory.criaUsuarioDAO();
    }
    public Usuario buscar(Integer id){
        return usuarioDAO.buscar(id);
    }
    public Usuario buscarPorEmail(String email){
        return usuarioDAO.buscarPorEmail(email);
    }
    public void salvar(Usuario usuario){
        Integer codigo = usuario.getId();
        if (codigo==null || codigo==0) {
            usuarioDAO.salvar(usuario);
        }else{
            usuarioDAO.atualizar(usuario);
        }
    }
    public void excluir(Usuario usuario){
        usuarioDAO.excluir(usuario);
    }
    public List listar(){
        return usuarioDAO.listar();
    }
}
