package br.com.eguide.web;

import br.com.eguide.autor.Autor;
import br.com.eguide.livro.Livro;
import br.com.eguide.livro.LivroRN;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class LivroBean {

    private Livro livro;
    private List<Autor> autores;
    private String isbn;

    public Livro getLivro() {
        LivroRN livroRN = new LivroRN();
        String codigo = "";
        if (livro == null) {
            codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("livro");
        }
        return livroRN.buscarISBN13(Long.valueOf(codigo));
    }

    public String getIsbn() {
        String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("livro");
        return codigo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Autor> getAutores() {
        autores = new ArrayList<Autor>();
        for (Autor autor : getLivro().getAutor()) {
            autores.add(autor);
        }
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

}
