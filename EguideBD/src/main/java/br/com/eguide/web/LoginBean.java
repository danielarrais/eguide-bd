package br.com.eguide.web;

import br.com.eguide.nivelAcesso.NivelAcesso;
import br.com.eguide.usuario.Usuario;
import br.com.eguide.usuario.UsuarioRN;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

    String emailInformado;
    String senhaInformada;
    Usuario usuario;

    public String logar() {
        UsuarioRN usuarioRN = new UsuarioRN();
        Usuario user;
        if ((user = usuarioRN.buscarPorEmail(emailInformado)) != null) {
            if (user.getSenha().equals(DigestUtils.sha1Hex(senhaInformada))) {
                usuario = user;
                return null;
            }
        }
        return null;
    }

    public String sair() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuario = null;
        return "/index?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isNivel(String nivelAcesso) {
        for (NivelAcesso nivel : usuario.getNivelAcesso()) {
            if (nivel.getNivel().equalsIgnoreCase(nivelAcesso)) {
                return true;
            }
        }
        return false;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEmailInformado() {
        return emailInformado;
    }

    public void setEmailInformado(String emailInformado) {
        this.emailInformado = emailInformado;
    }

    public String getSenhaInformada() {
        return senhaInformada;
    }

    public void setSenhaInformada(String senhaInformada) {
        this.senhaInformada = senhaInformada;
    }

    public static Usuario getUsuarioLogado() {
        return ((LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean")).usuario;
    }
}
