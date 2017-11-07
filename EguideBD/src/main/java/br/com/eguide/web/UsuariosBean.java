package br.com.eguide.web;

import br.com.eguide.nivelAcesso.NivelAcesso;
import br.com.eguide.nivelAcesso.NivelAcessoRN;
import br.com.eguide.usuario.Usuario;
import br.com.eguide.usuario.UsuarioRN;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.*;

@ManagedBean(name = "usuariosBean")
@ViewScoped
public class UsuariosBean implements Serializable {

    private static final long serialVersionUID = -5216137311601453272L;

    private List<Usuario> listaUsuarios;
    private Usuario usuarioEditar;
    private List<NivelAcesso> nivelAcessos;
    private HashSet<NivelAcesso> nivelAcessosSelecionados;
    private boolean estadoEditar;

    public Set<NivelAcesso> getNivelAcessosSelecionados() {
        return nivelAcessosSelecionados;
    }

    public void setNivelAcessosSelecionados(HashSet<NivelAcesso> nivelAcessosSelecionados) {
        this.nivelAcessosSelecionados = nivelAcessosSelecionados;
    }

    public List<NivelAcesso> getNivelAcessos() {
        return new NivelAcessoRN().listar();
    }

    public void setNivelAcessos(List<NivelAcesso> nivelAcessos) {
        this.nivelAcessos = nivelAcessos;
    }

    public Usuario getUsuarioEditar() {
        return usuarioEditar;
    }

    public void setUsuarioEditar(Usuario usuarioEditar) {
        this.usuarioEditar = usuarioEditar;
    }

    public boolean isEstadoEditar() {
        return estadoEditar;
    }

    public void setEstadoEditar(boolean estadoEditar) {
        this.estadoEditar = estadoEditar;
    }

    public String editar(Usuario usuario) {
        usuarioEditar = new UsuarioRN().buscar(usuario.getId());
        nivelAcessosSelecionados = new HashSet<NivelAcesso>();
        for (NivelAcesso nivelAcesso : usuarioEditar.getNivelAcesso()) {
            nivelAcessosSelecionados.add(nivelAcesso);
        }
        estadoEditar = true;
        return null;
    }

    public String atualizarUsuario() {
        UsuarioRN usuarioRN = new UsuarioRN();
        usuarioEditar.setNivelAcesso(nivelAcessosSelecionados);
        usuarioRN.salvar(usuarioEditar);
        usuarioEditar = null;
        estadoEditar = false;
        return null;
    }

    public List<Usuario> getListaUsuarios() {
        if (listaUsuarios == null) {
            UsuarioRN usuarioRN = new UsuarioRN();
            listaUsuarios = usuarioRN.listar();
        }
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String excluir(Usuario usuario) {
        UsuarioRN usuarioRN = new UsuarioRN();
        usuarioRN.excluir(usuario);
        this.listaUsuarios = null;
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.listaUsuarios != null ? this.listaUsuarios.hashCode() : 0);
        hash = 31 * hash + (this.usuarioEditar != null ? this.usuarioEditar.hashCode() : 0);
        hash = 31 * hash + (this.estadoEditar ? 1 : 0);
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
        final UsuariosBean other = (UsuariosBean) obj;
        if (this.estadoEditar != other.estadoEditar) {
            return false;
        }
        if (this.listaUsuarios != other.listaUsuarios && (this.listaUsuarios == null || !this.listaUsuarios.equals(other.listaUsuarios))) {
            return false;
        }
        if (this.usuarioEditar != other.usuarioEditar && (this.usuarioEditar == null || !this.usuarioEditar.equals(other.usuarioEditar))) {
            return false;
        }
        return true;
    }
}

