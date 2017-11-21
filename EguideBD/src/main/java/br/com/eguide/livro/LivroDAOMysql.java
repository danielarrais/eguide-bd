package br.com.eguide.livro;

import br.com.eguide.autor.Autor;
import br.com.eguide.editora.Editora;
import br.com.eguide.idioma.Idioma;
import br.com.eguide.origem.Origem;
import br.com.eguide.subgenero.Subgenero;
import br.com.eguide.util.DAOFactory;
import br.com.eguide.util.MysqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LivroDAOMysql implements LivroDAO {

	private Connection connection;

	@Override
	public void salvar(Livro livro) {
		try {
			connection = MysqlUtil.getConnection();
			String sql = "INSERT INTO `eguide`.`livro` (" + "`ano`," + "`descricao`," + "`edicao`," + "`isbn10`,"
					+ "`isbn13`," + "`nome`," + "`paginas`," + "`editora_id_editora`," + "`idioma_id_idioma`,"
					+ "`origem_id_origem`," + "`subgenero_id_subgenero`)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement cadastro = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cadastro.setInt(1, livro.getAno());
			cadastro.setString(2, livro.getDescricao());
			cadastro.setInt(3, livro.getEdicao());
			cadastro.setInt(4, livro.getIsbn10());
			cadastro.setLong(5, livro.getIsbn13());
			cadastro.setString(6, livro.getNome());
			cadastro.setInt(7, livro.getPaginas());
			cadastro.setInt(8, livro.getEditora().getId());
			cadastro.setInt(9, livro.getIdioma().getId());
			cadastro.setInt(10, livro.getOrigem().getId());
			cadastro.setInt(11, livro.getSubgenero().getIdSub());
			cadastro.executeUpdate();
			ResultSet rs = cadastro.getGeneratedKeys();
			rs.next();
			livro.setId(rs.getInt(1));
			MysqlUtil.closeConnection(connection, cadastro);
			insertAutores(livro, livro.getAutor());
		} catch (Exception e) {
			System.out.println("Erro ao salvar livro. Erro: " + e.getMessage());
		}
	}

	@Override
	public void atualizar(Livro livro) {
		try {
			connection = MysqlUtil.getConnection();
			String sql = "UPDATE `eguide`.`livro` SET `ano` = ?, `descricao` = ?, `edicao` = ?, `isbn10` = ?, `isbn13` = ?, `nome` = ?, `paginas` = ?, `editora_id_editora` = ?, `idioma_id_idioma` = ?, `origem_id_origem` = ?, `subgenero_id_subgenero` = ? WHERE `id_livro` = ?";
			PreparedStatement cadastro = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			cadastro.setInt(1, livro.getAno());
			cadastro.setString(2, livro.getDescricao());
			cadastro.setInt(3, livro.getEdicao());
			cadastro.setInt(4, livro.getIsbn10());
			cadastro.setLong(5, livro.getIsbn13());
			cadastro.setString(6, livro.getNome());
			cadastro.setInt(7, livro.getPaginas());
			cadastro.setInt(8, livro.getEditora().getId());
			cadastro.setInt(9, livro.getIdioma().getId());
			cadastro.setInt(10, livro.getOrigem().getId());
			cadastro.setInt(11, livro.getSubgenero().getIdSub());
			cadastro.setInt(12, livro.getId());
			cadastro.executeUpdate();
			ResultSet rs = cadastro.getGeneratedKeys();
			rs.next();
			livro.setId(rs.getInt(1));
			MysqlUtil.closeConnection(connection, cadastro);
			insertAutores(livro, livro.getAutor());
		} catch (Exception e) {
			System.out.println("Erro ao atualizar livro. Erro: " + e.getMessage());
		}
	}

	@Override
	public void excluir(Livro livro) {
		try {
			connection = MysqlUtil.getConnection();
			String sql = "DELETE FROM `livro` WHERE id_livro = ?";
			PreparedStatement cadastro = connection.prepareStatement(sql);
			cadastro.setInt(1, livro.getId());
			cadastro.execute();
			MysqlUtil.closeConnection(connection, cadastro);
		} catch (Exception e) {
			System.err.println("Erro ao excluir livro. Erro: " + e.getMessage());
		}
	}

	@Override
	public Livro buscar(Integer livroID) {
		ArrayList<String> isbn2 = new ArrayList<String>();
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		isbn2.add(livroID.toString());
		map.put("id_livro", isbn2);
		List<Livro> livros = filtarLivros(null, map);
		if (livros.isEmpty()) {
			return null;
		} else {
			return livros.get(0);
		}
	}

	@Override
	public Livro buscarISBN(Integer isbn) {
		return LivroDAOMysql.this.buscarISBN(isbn.longValue());
	}

	@Override
	public Livro buscarISBN(Long isbn) {
		ArrayList<String> isbn2 = new ArrayList<String>();
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		isbn2.add(isbn.toString());
		System.out.println(isbn.toString().length());
		map.put("isbn" + isbn.toString().length(), isbn2);
		List<Livro> livros = filtarLivros(null, map);
		if (livros.isEmpty()) {
			return null;
		} else {
			return livros.get(0);
		}
	}

	@Override
	public List<Livro> filtarLivros(Map<String, ArrayList<String>> objects, Map<String, ArrayList<String>> criterios) {
		Map<Integer, Livro> lista = new HashMap<Integer, Livro>();
		try {
			connection = MysqlUtil.getConnection();
			String sql = "select distinct LIVRO.*, AUTOR.*, EDITORA.*, SUBGENERO.*, ORIGEM.*, IDIOMA.* from livro LIVRO  "
					+ "inner join autor_livro AUTOR_LIVRO on LIVRO.id_livro = AUTOR_LIVRO.id_livro "
					+ "inner join autor AUTOR on AUTOR_LIVRO.id_autor = AUTOR.id_autor "
					+ "inner join editora EDITORA on LIVRO.editora_id_editora = EDITORA.id_editora "
					+ "inner join origem ORIGEM on LIVRO.origem_id_origem = ORIGEM.id_origem "
					+ "inner join idioma IDIOMA on LIVRO.idioma_id_idioma = IDIOMA.id_idioma "
					+ "inner join subgenero SUBGENERO on LIVRO.subgenero_id_subgenero = SUBGENERO.id_subgenero ";
			if (objects != null) {
				for (String key : objects.keySet()) {
					if (!objects.get(key).isEmpty()) {
						if ("genero".equalsIgnoreCase(key)) {
							sql += " and " + "subgenero".toUpperCase() + "."+key+"_id_" + key + " in " + arrayInIn(objects.get(key));
						} else {
							sql += " and " + key.toUpperCase() + ".id_" + key + " in " + arrayInIn(objects.get(key));
						}
					}
				}
			}
			if (criterios != null) {
				for (String key : criterios.keySet()) {
					if (!criterios.get(key).isEmpty()) {
						sql += " and LIVRO." + key + " in " + arrayInIn(criterios.get(key));
					}
				}
			}
			PreparedStatement consulta = connection.prepareStatement(sql);
			ResultSet resultado = consulta.executeQuery();
			List<Autor> autors = new ArrayList<Autor>();
			Livro livro = new Livro();
			while (resultado.next()) {
				if (!lista.containsKey(resultado.getInt(1))) {
					livro = new Livro(resultado.getInt(1), resultado.getString(7), resultado.getString(3),
							resultado.getInt(2), resultado.getInt(5), resultado.getLong(6), resultado.getInt(8),
							resultado.getInt(4), new Editora(resultado.getInt(16), resultado.getString(17)),
							// new Subgenero(resultado.getInt(18), resultado.getString(19)),
							DAOFactory.criaSubgeneroDAO().buscar(resultado.getInt(18)),
							new Origem(resultado.getInt(21), resultado.getString(22)),
							new Idioma(resultado.getInt(23), resultado.getString(24), resultado.getString(24)),
							new ArrayList<Autor>());
					lista.put(resultado.getInt(1), livro);
				}
				if (lista.containsKey(resultado.getInt(1))) {
					lista.get(livro.getId()).getAutor()
							.add(new Autor(resultado.getInt(13), resultado.getString(14), resultado.getString(15)));
				}
			}
			MysqlUtil.closeConnection(connection, consulta, resultado);
		} catch (SQLException e) {
			System.out.println("Erro ao listar livros. Erro: " + e.getMessage());
		}
		ArrayList<Livro> livros = new ArrayList<Livro>();
		for (Map.Entry<Integer, Livro> entry : lista.entrySet()) {
			livros.add(entry.getValue());
		}
		return livros;
	}

	@Override
	public Set<Object> valores(String coluna, boolean repetidos) {
		Set<Object> lista = new HashSet<Object>();
		try {
			connection = MysqlUtil.getConnection();
			String sql = "select " + (repetidos == true ? "distinct" : "") + " " + coluna + " FROM livro";
			PreparedStatement consulta = connection.prepareStatement(sql);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				lista.add(resultado.getObject(1));
			}
			MysqlUtil.closeConnection(connection, consulta, resultado);
		} catch (Exception e) {
			System.out.println("Erro ao listar os(as) " + coluna + " da tabela livro. Erro: " + e.getMessage());
		}
		return lista;
	}

	public String arrayInIn(List<String> objects) {
		String in = "(";
		for (Iterator<String> iterator = objects.iterator(); iterator.hasNext();) {
			String next = iterator.next();
			if (!iterator.hasNext()) {
				in += next + "";
			} else {
				in += next + ",";
			}
		}
		return in + ")";
	}

	@Override
	public List<Livro> listar() {
		return filtarLivros(null, null);
	}

	public void insertAutores(Livro livro, List<Autor> autors) {
		try {
			connection = MysqlUtil.getConnection();
			String sql = "INSERT INTO `autor_livro` (`id_livro`,`id_autor`)VALUES(?, ?)";
			PreparedStatement cadastro = connection.prepareStatement(sql);
			for (Autor autor : autors) {
				cadastro.setInt(1, livro.getId());
				cadastro.setInt(2, autor.getId());
				cadastro.addBatch();
			}
			cadastro.executeBatch();
			MysqlUtil.closeConnection(connection, cadastro);
		} catch (Exception e) {
			System.out.println("Erro ao salvar autores. Erro: " + e.getMessage());
		}
	}
}
