package br.com.eguide.nivelAcesso;

import br.com.eguide.usuario.Usuario;
import br.com.eguide.util.MysqlUtil;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Set;

public interface NivelAcessoDAO {

    public void salvar(NivelAcesso nivelAcesso);

    public void atualizar(NivelAcesso nivelAcesso);

    public void excluir(NivelAcesso nivelAcesso);

    public NivelAcesso buscar(Integer nivelAcesso);
    public NivelAcesso buscar(String nivelAcesso);

    public List<NivelAcesso> listar();

    public Set<NivelAcesso> listar(Integer usuario);

    public void salvarNiveis(Usuario usuario);

    public void exluirNiveis(Usuario usuario);

    public void atualizarNiveis(Usuario usuario);
}
