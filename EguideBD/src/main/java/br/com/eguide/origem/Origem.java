
package br.com.eguide.origem;

import java.io.Serializable;
import java.util.Objects;

public class Origem implements Serializable {
    
    private static final long serialVersionUID = -2152593367233506364L;
    
    private Integer id;
    private String origem;

    public Origem() {
    }

    public Origem(String origem) {
        this.origem = origem;
    }

    public Origem(Integer id, String origem) {
        this.id = id;
        this.origem = origem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.origem);
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
        final Origem other = (Origem) obj;
        if (!Objects.equals(this.origem, other.origem)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
