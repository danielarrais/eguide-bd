package br.com.eguide.web;

import br.com.eguide.autor.Autor;
import br.com.eguide.autor.AutorRN;
import br.com.eguide.editora.Editora;
import br.com.eguide.editora.EditoraRN;
import br.com.eguide.genero.Genero;
import br.com.eguide.genero.GeneroRN;
import br.com.eguide.idioma.Idioma;
import br.com.eguide.idioma.IdiomaRN;
import br.com.eguide.livro.Livro;
import br.com.eguide.livro.LivroRN;
import br.com.eguide.origem.Origem;
import br.com.eguide.origem.OrigemRN;
import br.com.eguide.subgenero.Subgenero;
import br.com.eguide.subgenero.SubgeneroRN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@javax.faces.view.ViewScoped
public class CadastroLivroBean implements Serializable {

    private static final long serialVersionUID = 8642498319160375496L;

    private String ISBNinserido = "";
    private Livro livro = new Livro();
    private List<Autor> autores;
    private List<Autor> autoresSelecionados;
    private List<SelectItem> idiomas;
    private int idiomaSelecionado;
    private List<SelectItem> origens;
    private int origemSelecionada;
    private List<SelectItem> editoras;
    private int editoraSelecionada;
    private Autor autorSelecionado;
    private List<SelectItem> generos;
    private int idGeneroSelecionado;
    private List<SelectItem> subgeneros;
    private int idSubGeneroSelecionado;

    private boolean passo1 = true, passo2, passo3;

    public String cadastrar() {
        LivroRN livroRN = new LivroRN();
        SubgeneroRN subgeneroRN = new SubgeneroRN();
        OrigemRN origemRN = new OrigemRN();
        IdiomaRN idiomaRN = new IdiomaRN();
        EditoraRN editoraRN = new EditoraRN();

        List<Autor> setAutores = new ArrayList<Autor>();

        for (Autor autor : autoresSelecionados) {
            setAutores.add(autor);
        }
        livro.setOrigem(origemRN.buscar(idiomaSelecionado));
        livro.setIdioma(idiomaRN.buscar(idiomaSelecionado));
        livro.setSubgenero(subgeneroRN.buscar(idSubGeneroSelecionado));
        livro.setEditora(editoraRN.buscar(editoraSelecionada));
        livro.setAutor(setAutores);

        livroRN.salvar(livro);
        livro = new Livro();

        return "cadastrosucesso";
    }

    public String addAutor(Autor autor) {
        if (autoresSelecionados == null) {
            autoresSelecionados = new ArrayList<Autor>();
        }
        Set<Autor> autors = new HashSet<Autor>(autoresSelecionados);
        if (autors.contains(autor)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você já selecionou esse autor!"));
            return null;
        }
        autoresSelecionados.add(autor);
        return null;
    }

    public String removerAutor(Autor autor) {
        try {
            autoresSelecionados.remove(autor);
        } catch (Exception e) {
        }
        return null;
    }

    public boolean verificarISBN() {
        LivroRN livroRN = new LivroRN();
        if (livroRN.buscarISBN13(Long.valueOf(ISBNinserido)) != null) {
            return true;
        } else {
            livro.setIsbn13(Long.valueOf(ISBNinserido));
            return false;
        }
    }

    public List<SelectItem> getGeneros() {
        GeneroRN generoRN = new GeneroRN();
        if (generos == null) {
            generos = new ArrayList<SelectItem>();
            for (Genero genero : generoRN.listar()) {
                generos.add(new SelectItem(genero.getId(), genero.getNomeGenero()));
            }
        }
        return generos;
    }

    public boolean isPasso1() {
        return passo1;
    }

    public boolean isPasso2() {
        return passo2;
    }

    public boolean isPasso3() {
        return passo3;
    }

    public void setPasso1(boolean passo1) {
        this.passo1 = passo1;
    }

    public void setPasso2(boolean passo2) {
        this.passo2 = passo2;
    }

    public void setPasso3(boolean passo3) {
        this.passo3 = passo3;
    }
    
    

    public void estadoUm() {
        passo1 = true;
        passo2 = false;
        passo3 = false;
    }

    public String estadoDois() {
        if (ISBNinserido.equals("")) {
            FacesContext.getCurrentInstance().addMessage("campoVasio", new FacesMessage("O campo está vazio!"));
            return null;
        } else if (verificarISBN()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O livro já existe!"));
            return "/publico/livro.xhtml?faces-redirect=true&livro=" + ISBNinserido;
        } else {
            passo1 = false;
            passo2 = true;
            passo3 = false;
        }
        return null;
    }

    public String estadoTres() {
        if (autoresSelecionados == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Você deve adicionar pelo menos um autor!"));
            return null;
        }
        passo1 = false;
        passo2 = false;
        passo3 = true;
        return null;
    }

    public int getIdiomaSelecionado() {
        return idiomaSelecionado;
    }

    public void setIdiomaSelecionado(int idiomaSelecionado) {
        this.idiomaSelecionado = idiomaSelecionado;
    }

    public int getOrigemSelecionada() {
        return origemSelecionada;
    }

    public void setOrigemSelecionada(int origemSelecionada) {
        this.origemSelecionada = origemSelecionada;
    }

    public int getEditoraSelecionada() {
        return editoraSelecionada;
    }

    public void setEditoraSelecionada(int editoraSelecionada) {
        this.editoraSelecionada = editoraSelecionada;
    }

    public void setGeneros(List<SelectItem> generos) {
        this.generos = generos;
    }

    public List<SelectItem> getSubgeneros() {
        GeneroRN generoRN = new GeneroRN();
        if (generos != null && idGeneroSelecionado > 0) {
            subgeneros = new ArrayList<SelectItem>();
            List<Subgenero> sub
                    = generoRN.
                            buscar(idGeneroSelecionado).
                            getSubgeneros();
            for (Subgenero subgenero : sub) {
                subgeneros.add(new SelectItem(subgenero.getIdSub(), subgenero.getNomeSubgenero()));
            }
        }
        return subgeneros;
    }

    public void setSubgeneros(List<SelectItem> subgeneros) {
        this.subgeneros = subgeneros;
    }

    public List<SelectItem> getIdiomas() {
        IdiomaRN idiomaRN = new IdiomaRN();
        if (idiomas == null) {
            idiomas = new ArrayList<SelectItem>();
            for (Idioma idioma : idiomaRN.listar()) {
                idiomas.add(new SelectItem(idioma.getId(), idioma.getNome()));
            }
        }
        return idiomas;
    }

    public void setIdiomas(List<SelectItem> idiomas) {
        this.idiomas = idiomas;
    }

    public List<SelectItem> getOrigens() {
        OrigemRN origemRN = new OrigemRN();
        if (origens == null) {
            origens = new ArrayList<SelectItem>();
            for (Origem idioma : origemRN.listar()) {
                origens.add(new SelectItem(idioma.getId(), idioma.getOrigem()));
            }
        }
        return origens;
    }

    public void setOrigens(List<SelectItem> origens) {
        this.origens = origens;
    }

    public List<SelectItem> getEditoras() {
        EditoraRN editoraRN = new EditoraRN();
        if (editoras == null) {
            editoras = new ArrayList<SelectItem>();
            for (Editora editora : editoraRN.listar()) {
                editoras.add(new SelectItem(editora.getId(), editora.getNome()));
            }
        }
        return editoras;
    }

    public void setEditoras(List<SelectItem> editoras) {
        this.editoras = editoras;
    }

    public int getIdGeneroSelecionado() {
        return idGeneroSelecionado;
    }

    public void setIdGeneroSelecionado(int idGeneroSelecionado) {
        this.idGeneroSelecionado = idGeneroSelecionado;
    }

    public String getISBNinserido() {
        return ISBNinserido;
    }

    public void setISBNinserido(String ISBNinserido) {
        this.ISBNinserido = ISBNinserido;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Autor> getAutoresSelecionados() {
        return autoresSelecionados;
    }

    public void setAutoresSelecionados(List<Autor> autoresSelecionados) {
        this.autoresSelecionados = autoresSelecionados;
    }

    public List<Autor> getAutores() {
        if (autores == null) {
            AutorRN autorRN = new AutorRN();
            autores = autorRN.listar();
        }

        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Autor getAutorSelecionado() {
        return autorSelecionado;
    }

    public void setAutorSelecionado(Autor autor) {
        this.autorSelecionado = autor;
    }

    public int getIdSubGeneroSelecionado() {
        return idSubGeneroSelecionado;
    }

    public void setIdSubGeneroSelecionado(int idSubGeneroSelecionado) {
        this.idSubGeneroSelecionado = idSubGeneroSelecionado;
    }

    @PostConstruct
    void init() {
        System.out.println("Initializing Address Bean.");
    }

    @PreDestroy
    void destroy() {
        System.out.println("Destroying Address Bean.");
    }

}
