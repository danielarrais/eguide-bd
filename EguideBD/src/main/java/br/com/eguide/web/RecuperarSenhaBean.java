package br.com.eguide.web;

import br.com.eguide.usuario.Usuario;
import br.com.eguide.usuario.UsuarioRN;
import br.com.eguide.util.email.Email;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;

@ManagedBean(name = "recuperarSenha")
@SessionScoped
public class RecuperarSenhaBean {

    private Usuario usuario = null;
    private String confirmarSenha;
    private String email;
    private String codigoGerado;
    private String codigoInformado;
    private String novaSenha;
    private String senhaConfirmada;
    private boolean exibirInsereEmail = true, exibirInsereCodigo, exibeInsereSenha;

    public String enviarCodigo() throws MessagingException {
        UsuarioRN usuarioRN = new UsuarioRN();
        usuario = usuarioRN.buscarPorEmail(email);
        if (usuario == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("E-mail não encontrado na base de dados!"));
        } else {
            Email emailService = new Email();
            Random random = new Random();
            codigoGerado = "";
            for (int i = 0; i < 7; i++) {
                codigoGerado += random.nextInt(9) + "";
            }
            emailService.enviarEmail("Codigo de recuperação de senha do EGuide", "Codigo de recuperação: " + codigoGerado, usuario.getEmail());
            exibirInsereCodigo = true;
            exibirInsereEmail = false;
        }

        return "/publico/recuperarsenha.xhtml";
    }

    public String verificarCodigo() {
        if (codigoInformado.equals(codigoGerado)) {
            exibeInsereSenha = true;
            exibirInsereCodigo = false;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Código Inválido!"));
        }
        return "/publico/recuperarsenha.xhtml";
    }

    public String verificarSenha() {
        if (novaSenha.equals(senhaConfirmada)) {
            UsuarioRN usuarioRN = new UsuarioRN();
            usuario.setSenha(novaSenha);
            usuarioRN.salvar(usuario, true);
            return "/index";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("As senha não coincidem!"));
        }
        return "/publico/recuperarsenha.xhtml";
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getSenhaConfirmada() {
        return senhaConfirmada;
    }

    public void setSenhaConfirmada(String senhaConfirmada) {
        this.senhaConfirmada = senhaConfirmada;
    }

    public boolean isExibirInsereEmail() {
        return exibirInsereEmail;
    }

    public void setExibirInsereEmail(boolean exibirInsereEmail) {
        this.exibirInsereEmail = exibirInsereEmail;
    }

    public boolean isExibirInsereCodigo() {
        return exibirInsereCodigo;
    }

    public void setExibirInsereCodigo(boolean exibirInsereCodigo) {
        this.exibirInsereCodigo = exibirInsereCodigo;
    }

    public boolean isExibeInsereSenha() {
        return exibeInsereSenha;
    }

    public void setExibeInsereSenha(boolean exibeInsereSenha) {
        this.exibeInsereSenha = exibeInsereSenha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigoGerado() {
        return codigoGerado;
    }

    public void setCodigoGerado(String codigoGerado) {
        this.codigoGerado = codigoGerado;
    }

    public String getCodigoInformado() {
        return codigoInformado;
    }

    public void setCodigoInformado(String codigoInformado) {
        this.codigoInformado = codigoInformado;
    }

}
