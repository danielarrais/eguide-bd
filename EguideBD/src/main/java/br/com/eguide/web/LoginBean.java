package br.com.eguide.web;

import br.com.eguide.nivelAcesso.NivelAcesso;
import br.com.eguide.usuario.Usuario;
import br.com.eguide.usuario.UsuarioDAOMysql;
import br.com.eguide.usuario.UsuarioRN;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

    String emailInformado;
    String senhaInformada;
    private Set<NivelAcesso> nivelAcessos ;
    Usuario usuario;

    public String logar() {
        UsuarioRN usuarioRN = new UsuarioRN();
        if ((usuario = usuarioRN.buscarPorEmail(emailInformado)) != null) {
            String senhaHash = DigestUtils.sha1Hex(senhaInformada);
            nivelAcessos = new HashSet<NivelAcesso>();
            for (NivelAcesso nivelAcesso : usuario.getNivelAcesso()) {
                nivelAcessos.add(nivelAcesso);
            }
            if (usuario.getSenha().equals(usuario.getSenha())) {
                return "/index?faces-redirect=true";
            }
        }
        return "/publico/cadastrarlivro/passo1.xhtml";
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
        for (NivelAcesso nivel : nivelAcessos) {
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
