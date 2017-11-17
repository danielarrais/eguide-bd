/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.avaliacao;

import br.com.eguide.livro.Livro;
import br.com.eguide.usuario.Usuario;

/**
 *
 * @author danie
 */
public class Avaliacao {

    private int id;
    private String comentario;
    private double nota;
    private Usuario usuario;
    private Livro livro;

    public Avaliacao() {
    }

    public Avaliacao(String comentario, double nota, Usuario usuario, Livro livro) {
        this.comentario = comentario;
        this.nota = nota;
        this.usuario = usuario;
        this.livro = livro;
    }

    public Avaliacao(int id_avaliacao, String comentario, double nota, Usuario usuario, Livro livro) {
        this.id = id_avaliacao;
        this.comentario = comentario;
        this.nota = nota;
        this.usuario = usuario;
        this.livro = livro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + (this.comentario != null ? this.comentario.hashCode() : 0);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.nota) ^ (Double.doubleToLongBits(this.nota) >>> 32));
        hash = 11 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
        hash = 11 * hash + (this.livro != null ? this.livro.hashCode() : 0);
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
        final Avaliacao other = (Avaliacao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.nota) != Double.doubleToLongBits(other.nota)) {
            return false;
        }
        if ((this.comentario == null) ? (other.comentario != null) : !this.comentario.equals(other.comentario)) {
            return false;
        }
        if (this.usuario != other.usuario && (this.usuario == null || !this.usuario.equals(other.usuario))) {
            return false;
        }
        if (this.livro != other.livro && (this.livro == null || !this.livro.equals(other.livro))) {
            return false;
        }
        return true;
    }
}
