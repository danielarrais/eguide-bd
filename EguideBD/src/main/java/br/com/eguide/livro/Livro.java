package br.com.eguide.livro;

import br.com.eguide.autor.Autor;
import br.com.eguide.editora.Editora;
import br.com.eguide.idioma.Idioma;
import br.com.eguide.origem.Origem;
import br.com.eguide.subgenero.Subgenero;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Livro implements Serializable {

    private static final long serialVersionUID = -8686353448808049596L;
    private Integer id;
    private String nome;
    private String descricao;
    private Integer ano;
    private Integer isbn10;
    private Long isbn13;
    private Integer paginas;
    private Integer edicao;
    private Editora editora;
    private Subgenero subgenero;
    private Origem origem;
    private Idioma idioma;
    private List<Autor> autor = new ArrayList<Autor>(); 

    public Livro() {
    }

    public Livro(String nome, String descricao, Integer ano, Integer isbn10, Long isbn13, Integer paginas, Integer edicao, Editora editora, Subgenero subgenero, Origem origem, Idioma idioma) {
        this.nome = nome;
        this.descricao = descricao;
        this.ano = ano;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.paginas = paginas;
        this.edicao = edicao;
        this.editora = editora;
        this.subgenero = subgenero;
        this.origem = origem;
        this.idioma = idioma;
    }

    public Livro(Integer id, String nome, String descricao, Integer ano, Integer isbn10, Long isbn13, Integer paginas, Integer edicao, Editora editora, Subgenero subgenero, Origem origem, Idioma idioma, List<Autor> autor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ano = ano;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.paginas = paginas;
        this.edicao = edicao;
        this.editora = editora;
        this.subgenero = subgenero;
        this.origem = origem;
        this.idioma = idioma;
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(Integer isbn10) {
        this.isbn10 = isbn10;
    }

    public Long getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(Long isbn13) {
        this.isbn13 = isbn13;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Subgenero getSubgenero() {
        return subgenero;
    }

    public void setSubgenero(Subgenero subgenero) {
        this.subgenero = subgenero;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.nome);
        hash = 43 * hash + Objects.hashCode(this.descricao);
        hash = 43 * hash + Objects.hashCode(this.ano);
        hash = 43 * hash + Objects.hashCode(this.isbn10);
        hash = 43 * hash + Objects.hashCode(this.isbn13);
        hash = 43 * hash + Objects.hashCode(this.paginas);
        hash = 43 * hash + Objects.hashCode(this.edicao);
        hash = 43 * hash + Objects.hashCode(this.editora);
        hash = 43 * hash + Objects.hashCode(this.subgenero);
        hash = 43 * hash + Objects.hashCode(this.origem);
        hash = 43 * hash + Objects.hashCode(this.autor);
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
        final Livro other = (Livro) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ano, other.ano)) {
            return false;
        }
        if (!Objects.equals(this.isbn10, other.isbn10)) {
            return false;
        }
        if (!Objects.equals(this.isbn13, other.isbn13)) {
            return false;
        }
        if (!Objects.equals(this.paginas, other.paginas)) {
            return false;
        }
        if (!Objects.equals(this.edicao, other.edicao)) {
            return false;
        }
        if (!Objects.equals(this.editora, other.editora)) {
            return false;
        }
        if (!Objects.equals(this.subgenero, other.subgenero)) {
            return false;
        }
        if (!Objects.equals(this.origem, other.origem)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livro{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", ano=" + ano + ", isbn10=" + isbn10 + ", isbn13=" + isbn13 + ", paginas=" + paginas + ", edicao=" + edicao + ", editora=" + editora + ", subgenero=" + subgenero.getNomeSubgenero() + ", origem=" + origem.getOrigem() + ", idioma=" + idioma.getNome() + ", autor=" + autor + '}';
    }
}
