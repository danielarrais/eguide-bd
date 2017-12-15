package br.com.eguide.web;

import br.com.eguide.nivelAcesso.NivelAcesso;
import br.com.eguide.usuario.Usuario;
import br.com.eguide.usuario.UsuarioRN;
import br.com.eguide.util.DAOFactory;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean(name = "cadastroBean")
@RequestScoped
public class CadastroBean {

    private Usuario usuario = new Usuario();
    private String confirmarSenha;

    public String salvar() {
        UsuarioRN usuarioRN = new UsuarioRN();
        FacesContext faces = FacesContext.getCurrentInstance();
        String senha = usuario.getSenha();
        if (!senha.equals(confirmarSenha)) {
            FacesMessage mensagem = new FacesMessage("As senha informadas são incompativeis!!!");
            faces.addMessage(null, mensagem);
            return null;
        }
        if (usuarioRN.buscarPorEmail(usuario.getEmail()) != null) {
            faces.addMessage(null, new FacesMessage("Já existe uma conta utilizando este e-mail!!!"));
            return null;
        } else {
            usuario.addNivelAcesso(DAOFactory.criaNivelAcessoDAO().buscar("user"));
            usuarioRN.salvar(usuario);
            return "sucessocadastro";
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

}
