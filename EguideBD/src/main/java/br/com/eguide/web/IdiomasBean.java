
package br.com.eguide.web;

import br.com.eguide.idioma.Idioma;
import br.com.eguide.idioma.IdiomaRN;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "idiomasBean")
@ViewScoped
public class IdiomasBean {
    Idioma idioma = new Idioma();
    List<Idioma> idiomasLista;
    public String salvar(){
        IdiomaRN idiomaRN = new IdiomaRN();
        idiomaRN.salvar(idioma);
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage mensagemSucesso = new FacesMessage("Idioma cadastrado com sucesso!");
        context.addMessage("sucesso", mensagemSucesso);
        return "/";
    }
    
    public List<Idioma> getIdiomasLista(){
        IdiomaRN idiomaRN = new IdiomaRN();
        return idiomaRN.listar();
    }
    
    public void excluir(Idioma idioma){
        IdiomaRN idiomaRN = new IdiomaRN();
        idiomaRN.excluir(idioma);
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma i) {
        this.idioma = i;
    }
}
