/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.statuslivro;

import br.com.eguide.livro.Livro;
import br.com.eguide.usuario.Usuario;
import br.com.eguide.util.DAOFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author danie
 */
public class StatusLivroRN {
    StatusLivroDAO statusLivroDAO= DAOFactory.criaStatusLivroDAO();
   
    public void salvar(StatusLivro status) {
        statusLivroDAO.salvar(status);
    }
    public void atualizar(StatusLivro status) {
        statusLivroDAO.atualizar(status);
    }
    public void excluir(StatusLivro status) {
        statusLivroDAO.excluir(status);
    }
    public StatusLivro buscar(Integer status) {
        return buscar(status);
    }
    public List<StatusLivro> listar() {
        return statusLivroDAO.listar();
    }
    public Map<Integer, ArrayList<Livro>> listarPorUsuario(Integer livro) {
        return statusLivroDAO.listarPorUsuario(livro);
    }
    public void alterarStatus(Integer idUsuario, Integer idStatusLivro, Integer idLivro){
        statusLivroDAO.alterarStatus(idUsuario, idStatusLivro, idLivro);
    }
    public StatusLivro buscar(Livro livro, Usuario usuario){
        return statusLivroDAO.buscar(livro, usuario);
    }
}
