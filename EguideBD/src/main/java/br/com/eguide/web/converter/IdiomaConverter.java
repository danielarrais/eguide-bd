package br.com.eguide.web.converter;

import br.com.eguide.idioma.Idioma;
import br.com.eguide.idioma.IdiomaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;

@FacesConverter(value = "idiomaConverter", forClass = Idioma.class)
public class IdiomaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length()>0) {
            Integer id = Integer.valueOf(string);
            try {
                IdiomaRN idiomaRN = new IdiomaRN();
                return idiomaRN.buscar(id);
            } catch (Exception e) {
                throw new ConverterException("Não foi possível encontrar o idioma!"+string+". "+e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o!=null) {
            Idioma idioma = (Idioma) o;
            return idioma.getId().toString();
        }
        return "";
    }

}
