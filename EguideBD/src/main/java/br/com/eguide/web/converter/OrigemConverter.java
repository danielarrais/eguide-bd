package br.com.eguide.web.converter;

import br.com.eguide.origem.Origem;
import br.com.eguide.origem.OrigemRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;

@FacesConverter(value = "origemConverter", forClass = Origem.class)
public class OrigemConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length()>0) {
            Integer id = Integer.valueOf(string);
            try {
                OrigemRN origemRN = new OrigemRN();
                return origemRN.buscar(id);
            } catch (Exception e) {
                throw new ConverterException("Não foi possível encontrar o origem!"+string+". "+e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o!=null) {
            Origem origem = (Origem) o;
            return origem.getId().toString();
        }
        return "";
    }

}
