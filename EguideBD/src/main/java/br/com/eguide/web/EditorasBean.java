/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.web;

import br.com.eguide.editora.Editora;
import br.com.eguide.editora.EditoraRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author danie
 */
@ManagedBean
@ViewScoped
public class EditorasBean {

    private List<Editora> editoras;
    private Editora editora;

    public List<Editora> getEditoras() {
        if (editoras == null) {
            editoras = new EditoraRN().listar();
        }
        return editoras;
    }

    public void setEditoras(List<Editora> editoras) {
        this.editoras = editoras;
    }

    public Editora getEditora() {
        if (editora == null) {
            editora = new Editora();
        }
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String gravar() {
        new EditoraRN().salvar(editora);
        editoras = null;
        editora = null;
        return null;
    }

    public String excluir(Editora editora) {
        new EditoraRN().excluir(editora);
        editoras = null;
        return null;
    }

    public String editar(Editora editora) {
        this.editora = editora;
        return null;
    }
}
