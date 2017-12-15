package br.com.eguide.web;

import br.com.eguide.genero.Genero;
import br.com.eguide.genero.GeneroRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class GeneroBean {

    private Genero genero;
    private List<Genero> generos;

    public Genero getGenero() {
        if (genero == null) {
            genero = new Genero();
        }
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Genero> getGeneros() {
        return new GeneroRN().listar();
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public String gravar() {
        new GeneroRN().salvar(genero);
        genero = null;
        generos=null;
        return null;
    }

    public String excluir(Genero genero) {
        new GeneroRN().excluir(genero);
        generos=null;
        return null;
    }
    
    public String editar(Genero genero) {
        this.genero = genero;
        return null;
    }
}
