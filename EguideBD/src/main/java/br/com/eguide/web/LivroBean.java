package br.com.eguide.web;

import br.com.eguide.autor.Autor;
import br.com.eguide.avaliacao.Avaliacao;
import br.com.eguide.avaliacao.AvaliacaoRN;
import br.com.eguide.livro.Livro;
import br.com.eguide.livro.LivroRN;
import br.com.eguide.statuslivro.StatusLivro;
import br.com.eguide.statuslivro.StatusLivroRN;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@javax.faces.view.ViewScoped
public class LivroBean implements Serializable {

    private static final long serialVersionUID = 7567248990963268906L;

    private Livro livro;
    private List<Autor> autores;
    private String isbn;
    private StatusLivro status;

    private Avaliacao avaliacao;
    private List<SelectItem> notas;

    private List<Avaliacao> avaliacoes;
    private int comentarioDoUsuarioLogado;
    private double nota;

    public String marcar(int status) {
        StatusLivroRN statusLivroRN = new StatusLivroRN();
        if (LoginBean.getUsuarioLogado() == null) {
            return "/login";
        }
        statusLivroRN.alterarStatus(LoginBean.getUsuarioLogado().getId(), status, getLivro().getId());
        System.out.println(LoginBean.getUsuarioLogado().getId() + " - " + livro.getId() + " - " + status);
        return "/publico/livro.xhtml?faces-redirect=true&livro=" + livro.getIsbn13();
    }

    public String avaliar() {
        AvaliacaoRN avaliacaoRN = new AvaliacaoRN();
        avaliacao.setLivro(livro);
        avaliacao.setUsuario(LoginBean.getUsuarioLogado());
        avaliacaoRN.salvar(avaliacao);
        avaliacoes = null;
        return null;
    }

    public Livro getLivro() {
        LivroRN livroRN = new LivroRN();
        String codigo = null;
        if (livro == null) {
            codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("livro");
            if (codigo != null) {
                livro = livroRN.buscarISBN13(Long.valueOf(codigo));
            }
        }
        return livro;
    }

    public StatusLivro getStatus() {
        StatusLivroRN statusLivroRN = new StatusLivroRN();
        if (status == null) {
            status = statusLivroRN.buscar(livro, LoginBean.getUsuarioLogado());
        }
        return status;
    }

    public void setStatus(StatusLivro status) {
        this.status = status;
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

    public Avaliacao getAvaliacao() {
        if (avaliacao == null) {
            avaliacao = new Avaliacao();
        }
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<SelectItem> getNotas() {
        notas = new ArrayList<SelectItem>();
        for (int i = 0; i <= 5; i++) {
            notas.add(new SelectItem(i, i + ""));
        }
        return notas;
    }

    public void setNotas(List<SelectItem> notas) {
        this.notas = notas;
    }

    public List<Avaliacao> getAvaliacoes() {
        if (avaliacoes == null) {
            AvaliacaoRN avaliacaoRN = new AvaliacaoRN();
            avaliacoes = avaliacaoRN.listar(livro);
        }
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public boolean exibirFormAvaliacao() {
        if (LoginBean.getUsuarioLogado() == null) {
            return false;
        }else{
            for (Avaliacao avaliacoe : getAvaliacoes()) {
                if (avaliacoe.getUsuario().getId().equals(LoginBean.getUsuarioLogado().getId())) {
                    
                    avaliacoes.remove(avaliacoe);
                    avaliacoes.add(0, avaliacoe);
                    comentarioDoUsuarioLogado = avaliacoe.getId();
                    return false;
                }
            }
        }
        return true;
    }

    public int getComentarioDoUsuarioLogado() {
        return comentarioDoUsuarioLogado;
    }

    public void setComentarioDoUsuarioLogado(int comentarioDoUsuarioLogado) {
        this.comentarioDoUsuarioLogado = comentarioDoUsuarioLogado;
    }

    public double getNota() {
        for (Avaliacao avaliacoe : getAvaliacoes()) {
            nota+=avaliacoe.getNota();
        }
        return Double.valueOf(new DecimalFormat("0.00").format(nota/avaliacoes.size()).replace(",", "."));
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    
}
