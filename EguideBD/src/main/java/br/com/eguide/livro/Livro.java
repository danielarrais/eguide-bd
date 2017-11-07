    package br.com.eguide.livro;

    import br.com.eguide.autor.Autor;
    import br.com.eguide.editora.Editora;
    import br.com.eguide.idioma.Idioma;
    import br.com.eguide.origem.Origem;
    import br.com.eguide.subgenero.Subgenero;
    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Objects;
    import java.util.Set;
    import javax.persistence.*;

    @Entity
    public class Livro implements Serializable {

        private static final long serialVersionUID = -8686353448808049596L;

        @Id
        @GeneratedValue
        @Column(name = "id_livro")
        private Integer id;
        @Column(nullable = false)
        private String nome;
        @Column(columnDefinition = "TEXT")
        private String descricao;
        @Column(nullable = false)
        private Integer ano;
        @Column(length = 10, nullable = false, unique = true)
        private Integer isbn10;
        @Column(length = 13, nullable = false, unique = true)
        private Long isbn13;
        @Column(nullable = false)
        private Integer paginas;
        @Column(nullable = false)
        private Integer edicao;

        @ManyToOne
        private Editora editora;

        @OneToOne(cascade = CascadeType.ALL)
        private Subgenero subgenero;

        @OneToOne(cascade = CascadeType.ALL)
        private Origem origem;

        @OneToOne(cascade = CascadeType.ALL)
        private Idioma idioma;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "autor_livro",
                joinColumns = {
                    @JoinColumn(
                            name = "id_livro",
                            referencedColumnName = "id_livro")
                },
                inverseJoinColumns = {
                    @JoinColumn(name = "id_autor")})
        private List<Autor> autor = new ArrayList<Autor>();

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
            return "Livro{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", ano=" + ano + ", isbn10=" + isbn10 + ", isbn13=" + isbn13 + ", paginas=" + paginas + ", edicao=" + edicao + ", editora=" + editora + ", subgenero=" + subgenero + ", origem=" + origem + ", idioma=" + idioma + ", autor=" + autor + '}';
        }
    }
