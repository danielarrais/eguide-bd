
package br.com.eguide.web;

import br.com.eguide.genero.Genero;
import br.com.eguide.genero.GeneroRN;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "templateBean")
@ApplicationScoped
public class TemplateBean {
    private List<Genero> menuGeneros;
    public List<Genero> getMenuGeneros() {
        if (menuGeneros==null) {
            GeneroRN generoRN = new GeneroRN();
            menuGeneros = generoRN.listar();
        }
        return menuGeneros;
    }
    
    public void setMenuGeneros(List<Genero> menuGeneros) {
        this.menuGeneros = menuGeneros;
    }
    
    
}
