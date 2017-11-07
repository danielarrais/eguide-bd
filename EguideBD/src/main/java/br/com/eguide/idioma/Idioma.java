package br.com.eguide.idioma;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Idioma extends Object implements Serializable {

    private static final long serialVersionUID = -5387059360431515899L;

    @Id
    @GeneratedValue
    @Column(name = "id_idioma")
    private Integer id;
    @Column(length = 30, nullable = false)
    private String nome;
    @Column(length = 10, nullable = false)
    private String sigla;

    public Idioma() {
    }

    public Idioma(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public Idioma(Integer id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.nome);
        hash = 71 * hash + Objects.hashCode(this.sigla);
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
        final Idioma other = (Idioma) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sigla, other.sigla)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
