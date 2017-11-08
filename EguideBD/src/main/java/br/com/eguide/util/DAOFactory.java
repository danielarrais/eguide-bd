package br.com.eguide.util;

import br.com.eguide.autor.Autor;
import br.com.eguide.autor.AutorDAO;
import br.com.eguide.autor.AutorDAOMysql;
import br.com.eguide.editora.EditoraDAO;
import br.com.eguide.editora.EditoraDAOMysql;
import br.com.eguide.genero.GeneroDAO;
import br.com.eguide.genero.GeneroDAOMysql;
import br.com.eguide.idioma.*;
import br.com.eguide.livro.Livro;
import br.com.eguide.livro.LivroDAO;
import br.com.eguide.livro.LivroDAOHibernate;
import br.com.eguide.nivelAcesso.NivelAcessoDAO;
import br.com.eguide.nivelAcesso.NivelAcessoDAOMysql;
import br.com.eguide.origem.OrigemDAO;
import br.com.eguide.origem.OrigemDAOMysql;
import br.com.eguide.subgenero.SubgeneroDAO;
import br.com.eguide.subgenero.SubgeneroDAOMysql;
import br.com.eguide.usuario.*;

public class DAOFactory {

    public static UsuarioDAO criaUsuarioDAO() {
        UsuarioDAOMysql usuarioDAO = new UsuarioDAOMysql();
        return usuarioDAO;
    }

    public static IdiomaDAO criaIdiomaDAO() {
        IdiomaDAOMysql idiomaDAO = new IdiomaDAOMysql();
        return idiomaDAO;
    }

    public static GeneroDAO criaGeneroDAO() {
        GeneroDAOMysql generoDAO = new GeneroDAOMysql();
        return generoDAO;
    }

    public static EditoraDAO criaEditoraDAO() {
        EditoraDAOMysql editoraDAO = new EditoraDAOMysql();
        return editoraDAO;
    }

    public static AutorDAO criaAutorDAO() {
        AutorDAOMysql autorDAO = new AutorDAOMysql();
        return autorDAO;
    }

    public static LivroDAO criaLivroDAO() {
        LivroDAOHibernate livroDAO = new LivroDAOHibernate();
        return livroDAO;
    }

    public static OrigemDAO criaOrigemDAO() {
        OrigemDAOMysql origemDAO = new OrigemDAOMysql();
        return origemDAO;
    }

    public static SubgeneroDAO criaSubgeneroDAO() {
        SubgeneroDAOMysql subgeneroDAO = new SubgeneroDAOMysql();
        return subgeneroDAO;
    }

    public static NivelAcessoDAO criaNivelAcessoDAO() {
        NivelAcessoDAOMysql nivelAcessoDAO = new NivelAcessoDAOMysql();
        return nivelAcessoDAO;
    }

    public static void main(String[] args) {

        //Teste Autor Dao
//        AutorDAO autorDAO = criaAutorDAO();
//        Autor a = new Autor();
//        a.setNome("Vitor Sales");
//        a.setNomeAbnt("SALES, Vitor");
//        autorDAO.salvar(a);
//        a = autorDAO.buscar(7);
//        System.out.println(a.getId());
//        a.setNomeAbnt("SALES2, Vitor");
//        autorDAO.atualizar(a);
//        autorDAO.excluir(a);
//        System.out.println("ID: "+a.getId()+" Nome: "+a.getNome() + " excluido!");
//        for (Autor arg : autorDAO.listar()) {
//           System.out.println("ID: "+arg.getId()+" Nome: "+arg.getNome());
//        }
        //Testes EditoraDAO
//        EditoraDAO editoraDAO = criaEditoraDAO();
//        Editora e = new Editora("Flecha");
//        editoraDAO.salvar(e);
//        e = editoraDAO.buscar(6);
//        e.setNome("Flecha Maligna");
//        editoraDAO.atualizar(e);
//        editoraDAO.excluir(e);
//        for (Editora arg : editoraDAO.listar()) {
//           System.out.println("ID: "+arg.getId()+" Nome: "+arg.getNome());
//        }
        //Testes GeneroDAO
//        GeneroDAO generoDAO = criaGeneroDAO();
//        Genero e = new Genero("Ficção");
//        generoDAO.salvar(e);
//        e = generoDAO.buscar(6);
//        e.setNome("Mitologia");
//        generoDAO.atualizar(e);
//        generoDAO.excluir(e);
//        for (Genero arg : generoDAO.listar()) {
//           System.out.println("ID: "+arg.getId()+" Nome: "+arg.getNome());
//        }
        //Testes IdiomaDAO
//        IdiomaDAO idiomaDAO = criaIdiomaDAO();
//        Idioma e = new Idioma("GOtiano", "Got");
//        idiomaDAO.salvar(e);
//        e = idiomaDAO.buscar(10);
//        e.setNome("GOTibriano");
//        idiomaDAO.atualizar(e);
//        idiomaDAO.excluir(e);
//        for (Idioma arg : idiomaDAO.listar()) {
//           System.out.println("ID: "+arg.getId()+" Nome: "+arg.getNome()+" Sigla: "+arg.getSigla());
//        }
        //Testes NivelAcessoDAO
//        NivelAcessoDAO nivelAcessoDAO = criaNivelAcessoDAO();
//        NivelAcesso e = null;
//        nivelAcessoDAO.salvar(e);
//        e = nivelAcessoDAO.buscar("user");
//        System.out.println("ID: " + e.getId() + " Nivel: " + e.getNivel() + " Descrição: " + e.getDescricao());
//        e.setNivel("GOTibriano");
//        nivelAcessoDAO.atualizar(e);
//        nivelAcessoDAO.excluir(e);
//        for (NivelAcesso arg : nivelAcessoDAO.listar()) {
//           System.out.println("ID: "+arg.getId()+" Nivel: "+arg.getNivel()+" Descrição: "+arg.getDescricao());
//        }
        //Testes NivelAcessoDAO
//        OrigemDAO origemDAO = criaOrigemDAO();
//        Origem e = new Origem("Intergalatico");
//        origemDAO.salvar(e);
//        e = origemDAO.buscar(3);
//        e.setOrigem("Iniverso");
//        origemDAO.atualizar(e);
//        origemDAO.excluir(e);
//        for (Origem arg : origemDAO.listar()) {
//           System.out.println("ID: "+arg.getId()+" Origem: "+arg.getOrigem());
//        }
        //Testes SubgeneroDAO
//        SubgeneroDAO usuarioDAO = criaSubgeneroDAO();
//        Subgenero e = new Subgenero("Intergalatico");
//        e.setGenero(new Genero(1, "Historia"));
//        usuarioDAO.salvar(e);
//        e = usuarioDAO.buscar(3);
//        e.setNome("Iniverso");
//        e.setGenero(new Genero(1, "Historia"));
//        usuarioDAO.atualizar(e);
//        usuarioDAO.excluir(e);
//        for (Subgenero arg : usuarioDAO.listar()) {
//           System.out.println("ID: "+arg.getId()+" Subgenero: "+arg.getNome());
//        }
        //Testes SubgeneroDAO
//        UsuarioDAO usuarioDAO = criaUsuarioDAO();
//        Usuario e = null;
//        usuarioDAO.salvar(e);
//        e = usuarioDAO.buscar(1);
//        DAOFactory.criaNivelAcessoDAO().exluirNiveis(e);
//        e = usuarioDAO.buscarPorEmail("lucas@gmail.com");
//        System.out.println(e.getNome());
//        for (NivelAcesso arg : DAOFactory.criaNivelAcessoDAO().listar()) {
//           System.out.println("ID: "+arg.getId()+" Nivel: "+arg.getNivel()+" Descrição: "+arg.getDescricao());
//        }
//        for (NivelAcesso arg : e.getNivelAcesso()) {
//           System.out.println("ID: "+arg.getId()+" Nivel: "+arg.getNivel()+" Descrição: "+arg.getDescricao());
//        }
//        e.setNome("Mateus");
//        usuarioDAO.atualizar(e);
//        usuarioDAO.excluir(e);
//        for (Usuario arg : usuarioDAO.listar()) {
//           System.out.println("ID: "+arg.getId()+" Nome: "+arg.getNome());
//        }
        Livro e = DAOFactory.criaLivroDAO().buscar(1);
        System.out.println(e);
        for (Autor arg : e.getAutor()) {
           System.out.println("ID: "+arg.getId()+" Nome: "+arg.getNome());
        }

        }
    }
