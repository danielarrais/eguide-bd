package br.com.eguide.genero;

import br.com.eguide.subgenero.Subgenero;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Genero implements Serializable {

    private static final long serialVersionUID = -3519805135626378833L;
    @Id
    @GeneratedValue
    @Column(name = "id_genero")
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY)
    private List<Subgenero> subgeneros;

    public Genero() {
    }

    public Genero(String nome) {
        this.nome = nome;
    }

    public Genero(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
        hash = 73 * hash + Objects.hashCode(this.nome);
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
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
