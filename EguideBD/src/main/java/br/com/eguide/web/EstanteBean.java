/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.web;

import br.com.eguide.livro.Livro;
import br.com.eguide.statuslivro.StatusLivro;
import br.com.eguide.statuslivro.StatusLivroRN;
import br.com.eguide.usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author danie
 */
@Named
@ViewScoped
public class EstanteBean implements Serializable {

    private static final long serialVersionUID = 367723874371239259L;
    ArrayList<Livro> livrosLidos = new ArrayList<Livro>();
    ArrayList<Livro> livrosQuero = new ArrayList<Livro>();
    ArrayList<Livro> livrosLendo = new ArrayList<Livro>();
    Map<Integer, ArrayList<Livro>> statusLivros;
    Usuario usuario ;

    public Map<Integer, ArrayList<Livro>> getStatusLivros() {
        StatusLivroRN statusLivroRN = new StatusLivroRN();
        if (statusLivros == null) {
            statusLivros = statusLivroRN.listarPorUsuario(getUsuario().getId());
        }
        return statusLivros;
    }

    public void setStatusLivros(Map<Integer, ArrayList<Livro>> statusLivros) {
        this.statusLivros = statusLivros;
    }

    public ArrayList<Livro> getLivrosLidos() {
        return getStatusLivros().get(StatusLivro.LIDO);
    }

    public void setLivrosLidos(ArrayList<Livro> livrosLidos) {
        this.livrosLidos = livrosLidos;
    }

    public ArrayList<Livro> getLivrosQuero() {
        return getStatusLivros().get(StatusLivro.QUEROLER);
    }

    public void setLivrosQuero(ArrayList<Livro> livrosQuero) {
        this.livrosQuero = livrosQuero;
    }

    public ArrayList<Livro> getLivrosLendo() {
        return getStatusLivros().get(StatusLivro.LENDO);
    }

    public void setLivrosLendo(ArrayList<Livro> livrosLendo) {
        this.livrosLendo = livrosLendo;
    }

    public Usuario getUsuario() {
        return LoginBean.getUsuarioLogado();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

}
