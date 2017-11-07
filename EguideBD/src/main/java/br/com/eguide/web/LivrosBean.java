/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.web;

import br.com.eguide.autor.Autor;
import br.com.eguide.autor.AutorRN;
import br.com.eguide.editora.Editora;
import br.com.eguide.editora.EditoraRN;
import br.com.eguide.genero.Genero;
import br.com.eguide.genero.GeneroRN;
import br.com.eguide.idioma.Idioma;
import br.com.eguide.idioma.IdiomaRN;
import br.com.eguide.livro.Livro;
import br.com.eguide.livro.LivroRN;
import br.com.eguide.origem.Origem;
import br.com.eguide.origem.OrigemRN;
import br.com.eguide.subgenero.Subgenero;
import br.com.eguide.subgenero.SubgeneroRN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class LivrosBean implements Serializable {

    private static final long serialVersionUID = -5006195333643630711L;

    private List<Livro> livros;
    private List<Idioma> idiomas;
    private List<Origem> origens;
    private List<Editora> editoras;
    private List<Subgenero> subgeneros;
    private List<Autor> autores;

    private List<Livro> livrosSelecionados;
    private List<Idioma> idiomasSelecionados;
    private List<Origem> origensSelecionados;
    private List<Editora> editorasSelecionados;
    private List<Subgenero> subgenerosSelecionados;
    private List<Autor> autoresSelecionados;

    Map<String, ArrayList<Object>> restricoes;

    LivroRN livroRN = new LivroRN();
    GeneroRN generoRN = new GeneroRN();
    IdiomaRN idiomaRN = new IdiomaRN();
    OrigemRN origemRN = new OrigemRN();
    EditoraRN editoraRN = new EditoraRN();
    AutorRN autorRN = new AutorRN();
    SubgeneroRN subgeneroRN = new SubgeneroRN();
    
    public String atualizarResultados(){
        return null;
    }

    //Geters e Seters
    public List<Livro> getLivrosSelecionados() {
        return livrosSelecionados;
    }

    public void setLivrosSelecionados(List<Livro> livrosSelecionados) {
        this.livrosSelecionados = livrosSelecionados;
    }

    public List<Idioma> getIdiomasSelecionados() {
        return idiomasSelecionados;
    }

    public void setIdiomasSelecionados(List<Idioma> idiomasSelecionados) {
        this.idiomasSelecionados = idiomasSelecionados;
    }

    public List<Origem> getOrigensSelecionados() {
        return origensSelecionados;
    }

    public void setOrigensSelecionados(List<Origem> origensSelecionados) {
        this.origensSelecionados = origensSelecionados;
    }

    public List<Editora> getEditorasSelecionados() {
        return editorasSelecionados;
    }

    public void setEditorasSelecionados(List<Editora> editorasSelecionados) {
        this.editorasSelecionados = editorasSelecionados;
    }

    public List<Subgenero> getSubgenerosSelecionados() {
        return subgenerosSelecionados;
    }

    public void setSubgenerosSelecionados(List<Subgenero> subgenerosSelecionados) {
        this.subgenerosSelecionados = subgenerosSelecionados;
    }

    public List<Autor> getAutoresSelecionados() {
        return autoresSelecionados;
    }

    public void setAutoresSelecionados(List<Autor> autoresSelecionados) {
        this.autoresSelecionados = autoresSelecionados;
    }

    public List<Livro> getLivros() {
        restricoes = new HashMap<String, ArrayList<Object>>();
        if (idiomasSelecionados!=null) {
            restricoes.put("idioma", new ArrayList<Object>(idiomasSelecionados));
        }
        if (origensSelecionados!=null) {
            restricoes.put("origem", new ArrayList<Object>(origensSelecionados));
        }
        if (editorasSelecionados!=null) {
            restricoes.put("editora", new ArrayList<Object>(editorasSelecionados));
        }
        if (autoresSelecionados!=null) {
            restricoes.put("autor", new ArrayList<Object>(autoresSelecionados));
        }
//        if (subgenerosSelecionados!=null) {
//            restricoes.put("subgenero", new ArrayList<Object>(subgenerosSelecionados));
//        }
        
        return new LivroRN().listaEspecial(restricoes);
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Idioma> getIdiomas() {
        if (idiomas == null) {
            idiomas = idiomaRN.listar();
        }
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public List<Origem> getOrigens() {
        if (origens == null) {
            origens = origemRN.listar();
        }
        return origens;
    }

    public void setOrigens(List<Origem> origens) {
        this.origens = origens;
    }

    public List<Editora> getEditoras() {
        if (editoras == null) {
            editoras = editoraRN.listar();
        }
        return editoras;
    }

    public void setEditoras(List<Editora> editoras) {
        this.editoras = editoras;
    }

    public List<Subgenero> getSubgeneros() {
        if (subgeneros == null) {
            subgeneros = subgeneroRN.listar();
        }
        return subgeneros;
    }

    public void setSubgeneros(List<Subgenero> subgeneros) {
        this.subgeneros = subgeneros;
    }

    public List<Autor> getAutores() {
        if (autores == null) {
            autores = autorRN.listar();
        }
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}
