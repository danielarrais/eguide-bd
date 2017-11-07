/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.web.converter;
//A anotação FacesConverter diz ao JSF que essa classe se trata de um conversor


import br.com.eguide.nivelAcesso.NivelAcesso;
import br.com.eguide.nivelAcesso.NivelAcessoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "nivelConverter", forClass = NivelAcesso.class)
public class NivelConverter implements Converter{
    
    //Método utilizado pelo conversor para retornar o objeto selecionado
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length()>0) {
            Integer id = Integer.valueOf(string);
            try {
               return new NivelAcessoRN().buscar(id);
            } catch (Exception e) {
                throw new ConverterException("Não foi possível encontrar o nivel acesso! "+string+". "+e.getMessage());
            }
        }
        return null;
    }
    //Método que retorna o objeto como string, no caso, retorna o ID,
    //que é utlizado no método anterior para buscar o nivel acesso no banco
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o!=null) {
            NivelAcesso nivelAcesso = (NivelAcesso) o;
            return nivelAcesso.getId().toString();
        }
        return "";
    }
}