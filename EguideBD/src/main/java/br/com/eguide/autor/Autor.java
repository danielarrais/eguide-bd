package br.com.eguide.autor;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Autor implements Serializable {

    private static final long serialVersionUID = -2490324874783531586L;

    @Id
    @GeneratedValue
    @Column(name = "id_autor")
    private Integer id;
    @Column(length = 80, nullable = false)
    private String nome;
    @Column(length = 30, nullable = false)
    private String nomeAbnt;

    public Autor() {
    }
    
    public Autor(String nome, String nomeAbnt) {
        this.nome = nome;
        this.nomeAbnt = nomeAbnt;
    }
    public Autor(Integer id, String nome, String nomeAbnt) {
        this.id = id;
        this.nome = nome;
        this.nomeAbnt = nomeAbnt;
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

    public String getNomeAbnt() {
        return nomeAbnt;
    }

    public void setNomeAbnt(String nomeAbnt) {
        this.nomeAbnt = nomeAbnt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + Objects.hashCode(this.nomeAbnt);
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
        final Autor other = (Autor) obj;
        return true;
    }

}
