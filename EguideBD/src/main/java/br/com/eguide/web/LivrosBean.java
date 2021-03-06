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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "livrosBean")
@ViewScoped
public class LivrosBean {

    private List<Livro> livros;
    private List<Idioma> idiomas;
    private List<Origem> origens;
    private List<Editora> editoras;
    private List<Subgenero> subgeneros;
    private List<Genero> generos;
    private List<Autor> autores;
    private Set<String> anos;

    private String busca;

    private ArrayList<String> livrosSelecionados;
    private ArrayList<String> idiomasSelecionados;
    private ArrayList<String> origensSelecionados;
    private ArrayList<String> editorasSelecionados;
    private ArrayList<String> subgenerosSelecionados;
    private ArrayList<String> autoresSelecionados;
    private ArrayList<String> anosSelecionados;
    private ArrayList<String> generosSelecionados;
    Map<String, ArrayList<String>> restricoes;
    Map<String, ArrayList<String>> restricoesIsoladas;

    LivroRN livroRN = new LivroRN();
    GeneroRN generoRN = new GeneroRN();
    IdiomaRN idiomaRN = new IdiomaRN();
    OrigemRN origemRN = new OrigemRN();
    EditoraRN editoraRN = new EditoraRN();
    AutorRN autorRN = new AutorRN();
    SubgeneroRN subgeneroRN = new SubgeneroRN();

    public String atualizarResultados() {
        return null;
    }

    public Set<String> getAnos() {
        if (anos == null) {
            anos = new HashSet<String>();
        }
        for (Object valor : livroRN.valores("ano", false)) {
            anos.add(valor.toString());
        }
        return anos;
    }

    //Geters e Seters
    public void setAnos(Set<String> anos) {
        this.anos = anos;
    }

    public List<String> getAnosSelecionados() {
        return anosSelecionados;
    }

    public void setAnosSelecionados(ArrayList<String> anosSelecionados) {
        this.anosSelecionados = anosSelecionados;
    }

    public List<String> getLivrosSelecionados() {
        return livrosSelecionados;
    }

    public void setLivrosSelecionados(ArrayList<String> livrosSelecionados) {
        this.livrosSelecionados = livrosSelecionados;
    }

    public List<String> getIdiomasSelecionados() {
        return idiomasSelecionados;
    }

    public void setIdiomasSelecionados(ArrayList<String> idiomasSelecionados) {
        this.idiomasSelecionados = idiomasSelecionados;
    }

    public List<String> getOrigensSelecionados() {
        return origensSelecionados;
    }

    public void setOrigensSelecionados(ArrayList<String> origensSelecionados) {
        this.origensSelecionados = origensSelecionados;
    }

    public List<String> getEditorasSelecionados() {
        return editorasSelecionados;
    }

    public void setEditorasSelecionados(ArrayList<String> editorasSelecionados) {
        this.editorasSelecionados = editorasSelecionados;
    }

    public List<String> getSubgenerosSelecionados() {
        return subgenerosSelecionados;
    }

    public String getCampoBusca() {
        return busca;
    }

    public void setCampoBusca(String campoBusca) {
        this.busca = campoBusca;
    }

    public void setSubgenerosSelecionados(ArrayList<String> subgenerosSelecionados) {
        this.subgenerosSelecionados = subgenerosSelecionados;
    }

    public List<String> getAutoresSelecionados() {
        return autoresSelecionados;
    }

    public void setAutoresSelecionados(ArrayList<String> autoresSelecionados) {
        this.autoresSelecionados = autoresSelecionados;
    }

    public List<Livro> getLivros() {
        restricoes = new HashMap<String, ArrayList<String>>();
        restricoesIsoladas = new HashMap<String, ArrayList<String>>();
        if (idiomasSelecionados != null) {
            restricoes.put("idioma", idiomasSelecionados);
        }
        if (origensSelecionados != null) {
            restricoes.put("origem", origensSelecionados);
        }
        if (editorasSelecionados != null) {
            restricoes.put("editora", editorasSelecionados);
        }
        if (autoresSelecionados != null) {
            restricoes.put("autor", autoresSelecionados);
        }
        if (subgenerosSelecionados != null) {
            restricoes.put("subgenero", subgenerosSelecionados);
        }
        if (generosSelecionados != null) {
            restricoes.put("genero", generosSelecionados);
        }
        if (anosSelecionados != null) {
            restricoesIsoladas.put("ano", anosSelecionados);
        }
        if (busca != null) {
            ArrayList<String> buscas = new ArrayList<String>();
            buscas.add(busca);
            restricoesIsoladas.put("busca", buscas);
        }
        return new LivroRN().listaEspecial(restricoes, restricoesIsoladas);
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

    public List<Genero> getGeneros() {
        if (generos == null) {
            generos = generoRN.listar();
        }
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public ArrayList<String> getGenerosSelecionados() {
        return generosSelecionados;
    }

    public void setGenerosSelecionados(ArrayList<String> generosSelecionados) {

        this.generosSelecionados = generosSelecionados;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public LivrosBean() {
        String genero = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("genero");
        if (genero != null) {
            if (generosSelecionados == null) {
                generosSelecionados = new ArrayList<String>();
            }
            generosSelecionados.add(genero);
        }
        String subgenero = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("subgenero");
        if (subgenero != null) {
            if (subgenerosSelecionados == null) {
                subgenerosSelecionados = new ArrayList<String>();
            }
            subgenerosSelecionados.add(subgenero);
        }
        String buscaFeita = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("busca");
        this.busca = buscaFeita;
        System.out.println("Parametro busca:"+busca);
    }

}
