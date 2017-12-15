/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.web;

import br.com.eguide.autor.Autor;
import br.com.eguide.autor.AutorRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author danie
 */
@ManagedBean
@ViewScoped
public class AutoresBean {
    private List<Autor> autores;
    private Autor autor ;

    public List<Autor> getAutores() {
        if (autores==null) {
            autores = new AutorRN().listar();
        }
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Autor getAutor() {
        if (autor == null) {
            autor = new Autor();
        }
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    public String gravar(){
        new AutorRN().salvar(autor);
        autor= null;
        autores=null;
        return null;
    }
    
    public String excluir(Autor autor){
        new AutorRN().excluir(autor);
        this.autor= null;
        autores = null;
        return null;
    }
    
    public String editar(Autor autor){
        this.autor= autor;
        return null;
    }
}
