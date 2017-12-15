/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eguide.usuario;

import br.com.eguide.nivelAcesso.NivelAcesso;
import java.util.List;
import java.util.Set;

/**
 *
 * @author danie
 */
public interface UsuarioDAO {
    public void salvar(Usuario usuario);
    public void atualizar(Usuario usuario);
    public void excluir(Usuario usuario);
    public Usuario buscar(Integer usuario);
    public Usuario buscarPorEmail(String email);
    public List<Usuario> listar();
    public void atualizar(Usuario usuario, Boolean sha1);
}
