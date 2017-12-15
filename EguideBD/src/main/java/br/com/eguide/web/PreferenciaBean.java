/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.web;

import br.com.eguide.usuario.Usuario;
import br.com.eguide.usuario.UsuarioRN;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author danie
 */
@ManagedBean
@ViewScoped
public class PreferenciaBean implements Serializable {

    private static final long serialVersionUID = 8922867235307507330L;
    private Usuario usuario;
    private String senhaAntiga;
    private String senhaInformada;
    private String senhaDois;

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getSenhaInformada() {
        return senhaInformada;
    }

    public void setSenhaInformada(String senhaInformada) {
        this.senhaInformada = senhaInformada;
    }

    public Usuario getUsuario() throws CloneNotSupportedException {
        if (usuario == null) {
            usuario = (Usuario) LoginBean.getUsuarioLogado().clone();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSenhaDois() {
        return senhaDois;
    }

    public void setSenhaDois(String senhaDois) {
        this.senhaDois = senhaDois;
    }

    public String salvar() {
        if (!usuario.getSenha().equals(DigestUtils.sha1Hex(senhaAntiga))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A senha digitada não corresponde com sua atual senha!"));
        }
        if (!senhaInformada.equals(senhaDois)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("As novas senhas são incompátiveis!"));
        }
        if (((senhaInformada.equals("") && senhaDois.equals("")) || (senhaInformada.equals(senhaDois))) && usuario.getSenha().equals(DigestUtils.sha1Hex(senhaAntiga))) {
            if (senhaInformada.equals("") && senhaDois.equals("")) {
                new UsuarioRN().salvar(usuario);
            }else{
                new UsuarioRN().salvar(usuario, true);
            }
            LoginBean.getIntancia().setUsuario(usuario);
            return "/user/index.xhtml";
        } 
        return null;
    }
}
