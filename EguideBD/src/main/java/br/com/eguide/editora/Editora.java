package br.com.eguide.editora;

import br.com.eguide.livro.Livro;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Editora implements Serializable {
    private static final long serialVersionUID = 3603316740949131038L;
    
    @Id
    @GeneratedValue
    @Column(name = "id_editora")
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @OneToMany(mappedBy = "editora", fetch = FetchType.LAZY)
    private List<Livro> livros;

    public Editora() {
    }

    public Editora(String nome) {
        this.nome = nome;
        this.livros = livros;
    }

    public Editora(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
        this.livros = livros;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.nome);
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
        final Editora other = (Editora) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
