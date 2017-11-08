package br.com.eguide.nivelAcesso;

import java.io.Serializable;

public class NivelAcesso implements Serializable {

    private static final long serialVersionUID = 1683510015743864728L;

    private Integer id;
    private String nivel;
    private String descricao;

    public NivelAcesso() {
    }

    public NivelAcesso(String nivel, String descricao) {
        this.nivel = nivel;
        this.descricao = descricao;
    }

    public NivelAcesso(Integer id, String nivel, String descricao) {
        this.id = id;
        this.nivel = nivel;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 61 * hash + (this.nivel != null ? this.nivel.hashCode() : 0);
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
        final NivelAcesso other = (NivelAcesso) obj;
        if ((this.nivel == null) ? (other.nivel != null) : !this.nivel.equals(other.nivel)) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
