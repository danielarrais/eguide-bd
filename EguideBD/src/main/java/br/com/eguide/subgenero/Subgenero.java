package br.com.eguide.subgenero;

import br.com.eguide.genero.Genero;
import java.io.Serializable;
import java.util.Objects;

public class Subgenero extends Genero implements Serializable {

    private static final long serialVersionUID = 8265675426430422192L;
    private Integer idSub;
    private String nomeSubgenero;

    public Subgenero() {
    }

    public Subgenero(String nome, Genero genero) {
        super(genero.getId(), genero.getNomeGenero());
        this.nomeSubgenero = nome;
    }

    public Subgenero(Integer id, String nome, Genero genero) {
        super(genero.getId(), genero.getNomeGenero());
        this.idSub = id;
        this.nomeSubgenero = nome;
    }

    public Subgenero(Integer idSub, String nome) {
        this.idSub = idSub;
        this.nomeSubgenero = nome;
    }

    public Integer getIdSub() {
        return idSub;
    }

    public void setIdSub(Integer idSub) {
        this.idSub = idSub;
    }

    public String getNomeSubgenero() {
        return nomeSubgenero;
    }

    public void setNomeSubgenero(String nomeSubgenero) {
        this.nomeSubgenero = nomeSubgenero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.idSub);
        hash = 79 * hash + Objects.hashCode(this.nomeSubgenero);
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
        final Subgenero other = (Subgenero) obj;
        if (!Objects.equals(this.nomeSubgenero, other.nomeSubgenero)) {
            return false;
        }
        if (!Objects.equals(this.idSub, other.idSub)) {
            return false;
        }
        return true;
    }
}
