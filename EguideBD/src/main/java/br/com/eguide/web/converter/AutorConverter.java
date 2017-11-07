
package br.com.eguide.web.converter;

import br.com.eguide.autor.Autor;
import br.com.eguide.autor.AutorRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;

//A anotação FacesConverter diz ao JSF que essa classe se trata de um conversor
@FacesConverter(value = "autorConverter", forClass = Autor.class)
public class AutorConverter implements Converter{
    
    //Método utilizado pelo conversor para retornar o objeto selecionado
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length()>0) {
            Integer id = Integer.valueOf(string);
            try {
               return new AutorRN().buscar(id);
            } catch (Exception e) {
                throw new ConverterException("Não foi possível encontrar o autor!"+string+". "+e.getMessage());
            }
        }
        return null;
    }
    //Método que retorna o objeto como string, no caso, retorna o ID,
    //que é utlizado no método anterior para buscar o autor no banco
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o!=null) {
            return ((Autor)o).getId().toString();
        }
        return "";
    }

}
