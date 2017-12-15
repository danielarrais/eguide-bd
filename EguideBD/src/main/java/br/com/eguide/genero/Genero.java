package br.com.eguide.genero;

import br.com.eguide.subgenero.Subgenero;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Genero implements Serializable {

    private static final long serialVersionUID = -3519805135626378833L;
    
    private Integer id;
    private String nomeGenero;
    private List<Subgenero> subgeneros;
    
    public static final boolean SUBGENERO = true;
    public static final boolean GENERO = false;

    public Genero() {
    }

    public Genero(String nome) {
        this.nomeGenero = nome;
    }

    public Genero(Integer id, String nome) {
        this.id = id;
        this.nomeGenero = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeGenero() {
        return nomeGenero;
    }

    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

    public List<Subgenero> getSubgeneros() {
        
        return subgeneros;
    }

    public void setSubgeneros(List<Subgenero> subgeneros) {
        this.subgeneros = subgeneros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.nomeGenero);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Genero other = (Genero) obj;
        if (!Objects.equals(this.nomeGenero, other.nomeGenero)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
