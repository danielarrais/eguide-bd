package br.com.eguide.web.converter;

import br.com.eguide.genero.Genero;
import br.com.eguide.genero.GeneroRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;

@FacesConverter(value = "generoConverter", forClass = Genero.class)
public class GeneroConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length()>0) {
            Integer id = Integer.valueOf(string);
            try {
                GeneroRN generoRN = new GeneroRN();
                return generoRN.buscar(id);
            } catch (Exception e) {
                throw new ConverterException("Não foi possível encontrar o genero!"+string+". "+e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o!=null) {
            Genero genero = (Genero) o;
            return genero.getId().toString();
        }
        return "";
    }

}
