package br.com.eguide.subgenero;

import br.com.eguide.genero.Genero;
import java.io.Serializable;
import java.util.Objects;

public class Subgenero implements Serializable {

    private static final long serialVersionUID = 8265675426430422192L;
    private Integer id;
    private String nome;
    private Genero genero;

    public Subgenero() {
    }

    public Subgenero(String nome) {
        this.nome = nome;
        this.genero = genero;
    }

    public Subgenero(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.genero);
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
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        return true;
    }
}
