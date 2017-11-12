/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.statuslivro;

/**
 *
 * @author danie
 */
public class StatusLivro {

    private Integer id;
    private String nome;

    public StatusLivro() {
    }

    public StatusLivro(String nome) {
        this.nome = nome;
    }

    public StatusLivro(Integer id, String nome) {
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

}
