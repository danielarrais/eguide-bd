package br.com.eguide.web.converter;

import br.com.eguide.editora.Editora;
import br.com.eguide.editora.EditoraRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;

@FacesConverter(value = "editoraConverter", forClass = Editora.class)
public class EditoraConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length()>0) {
            Integer id = Integer.valueOf(string);
            try {
                EditoraRN editoraRN = new EditoraRN();
                return editoraRN.buscar(id);
            } catch (Exception e) {
                throw new ConverterException("Não foi possível encontrar o editora!"+string+". "+e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o!=null) {
            Editora editora = (Editora) o;
            return editora.getId().toString();
        }
        return "";
    }

}
