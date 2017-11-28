package br.com.eguide.web;

import br.com.eguide.genero.Genero;
import br.com.eguide.genero.GeneroRN;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "templateBean")
@SessionScoped
public class TemplateBean {

    private List<Genero> menuGeneros;
    private String campoBusca;

    public List<Genero> getMenuGeneros() {
        if (menuGeneros == null) {
            GeneroRN generoRN = new GeneroRN();
            menuGeneros = generoRN.listar();
        }
        return menuGeneros;
    }

    public void setMenuGeneros(List<Genero> menuGeneros) {
        this.menuGeneros = menuGeneros;
    }

    public String getCampoBusca() {
        return campoBusca;
    }

    public void setCampoBusca(String campoBusca) {
        this.campoBusca = campoBusca;
    }
    
    public void buscar() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/EguideBD/livros.xhtml?busca="+campoBusca);
        campoBusca = null;
    }
    
}
