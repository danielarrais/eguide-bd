package br.com.eguide.usuario;

import br.com.eguide.nivelAcesso.NivelAcesso;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Usuario implements Serializable, Cloneable {

    private static final long serialVersionUID = 1991432369109352952L;
    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private String emailSec;
    private String senha;
    private Date nascimento;
    private Set<NivelAcesso> nivelAcesso = new HashSet<NivelAcesso>();

    public Usuario() {
    }

    public Usuario(String nome, String sobrenome, String email, String emailSec, String senha, Date nascimento, Set<NivelAcesso> nivelAcesso) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.emailSec = emailSec;
        this.senha = senha;
        this.nascimento = nascimento;
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario(Integer id, String nome, String sobrenome, String email, String emailSec, String senha, Date nascimento, Set<NivelAcesso> nivelAcesso) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.emailSec = emailSec;
        this.senha = senha;
        this.nascimento = nascimento;
        this.nivelAcesso = nivelAcesso;
    }

    public Set<NivelAcesso> getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(Set<NivelAcesso> nivelAcesso) {

        this.nivelAcesso = nivelAcesso;
    }
    public void addNivelAcesso(NivelAcesso nivelAcesso) {
        if (this.nivelAcesso == null) {
            this.nivelAcesso = new HashSet<NivelAcesso>();
        }
        this.nivelAcesso.add(nivelAcesso);
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailSec() {
        return emailSec;
    }

    public void setEmailSec(String emailSec) {
        this.emailSec = emailSec;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 59 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 59 * hash + (this.sobrenome != null ? this.sobrenome.hashCode() : 0);
        hash = 59 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 59 * hash + (this.emailSec != null ? this.emailSec.hashCode() : 0);
        hash = 59 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        hash = 59 * hash + (this.nascimento != null ? this.nascimento.hashCode() : 0);
        hash = 59 * hash + (this.nivelAcesso != null ? this.nivelAcesso.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.sobrenome == null) ? (other.sobrenome != null) : !this.sobrenome.equals(other.sobrenome)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.emailSec == null) ? (other.emailSec != null) : !this.emailSec.equals(other.emailSec)) {
            return false;
        }
        if ((this.senha == null) ? (other.senha != null) : !this.senha.equals(other.senha)) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.nascimento != other.nascimento && (this.nascimento == null || !this.nascimento.equals(other.nascimento))) {
            return false;
        }
        if (this.nivelAcesso != other.nivelAcesso && (this.nivelAcesso == null || !this.nivelAcesso.equals(other.nivelAcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
}
