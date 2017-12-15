package br.com.eguide.web;

import br.com.eguide.genero.Genero;
import br.com.eguide.genero.GeneroRN;
import br.com.eguide.subgenero.Subgenero;
import br.com.eguide.subgenero.SubgeneroRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class SubgeneroBean {

    private Subgenero subgenero;
    private List<Subgenero> subgeneros;
    private List<Genero> generos;

    public Subgenero getSubgenero() {
        if (subgenero == null) {
            subgenero = new Subgenero();
        }
        return subgenero;
    }

    public void setSubgenero(Subgenero subgenero) {
        this.subgenero = subgenero;
    }

    public List<Subgenero> getSubgeneros() {
         if (subgeneros==null) {
            subgeneros = new SubgeneroRN().listar();
        }
        return subgeneros;
    }

    public void setSubgeneros(List<Subgenero> subgeneros) {
        this.subgeneros = subgeneros;
    }

    public List<Genero> getGeneros() {
        if (generos==null) {
            generos = new GeneroRN().listar();
        }
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public String gravar() {
        new SubgeneroRN().salvar(subgenero);
        subgenero = null;
        subgeneros=null;
        return null;
    }

    public String excluir(Subgenero subgenero) {
        new SubgeneroRN().excluir(subgenero);
        subgeneros=null;
        return null;
    }

    public String editar(Subgenero subgenero) {
        this.subgenero = subgenero;
        return null;
    }
}
