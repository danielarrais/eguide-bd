/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.avaliacao;

import br.com.eguide.livro.Livro;
import java.util.List;

/**
 *
 * @author danie
 */
public interface AvaliacaoDAO {
    public void salvar(Avaliacao avaliacao);
    public void atualizar(Avaliacao avaliacao);
    public void excluir(Avaliacao avaliacao);
    public Avaliacao buscar(Integer avaliacao);
    public List<Avaliacao> listar(Livro livro);
}
