package br.com.eguide.web.converter;
import br.com.eguide.subgenero.SubgeneroRN;
import br.com.eguide.subgenero.Subgenero;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;

@FacesConverter(value = "subgeneroConverter", forClass = Subgenero.class)
public class SubgeneroConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length()>0) {
            Integer id = Integer.valueOf(string);
            try {
                SubgeneroRN subgeneroRN = new SubgeneroRN();
                return subgeneroRN.buscar(id);
            } catch (Exception e) {
                throw new ConverterException("Não foi possível encontrar o subgenero!"+string+". "+e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o!=null) {
            Subgenero subgenero = (Subgenero) o;
            return subgenero.getId().toString();
        }
        return "";
    }

}
