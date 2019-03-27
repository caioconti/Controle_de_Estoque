package usf.model.usuario;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.model.basic.BasicDAO;
import usf.model.basic.ModelBasic;

public class UsuarioDAO extends BasicDAO{

	public UsuarioDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public UsuarioDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
		super(jdbcURL, jdbcUsername, jdbcPassword,jdbcDriver);	
		
	}

	@Override
	public boolean insert(ModelBasic model) throws SQLException {
		Usuario usuario = (Usuario) model;
		
		//Inserindo no banco de dados
		String sql = "INSERT INTO usuario (nome, telefone, email, login, senha, nivelAcesso) VALUES (?, ?, ?, ?, ?, ?)";
		
		//Conecatando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getTelefone());
		statement.setString(3, usuario.getEmail());
		statement.setString(4, usuario.getLogin());
		statement.setString(5, usuario.getSenha());
		statement.setString(6, usuario.getNivelAcesso());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowInserted;
	}

	@Override
	public List<ModelBasic> listAll() throws SQLException {
		List<ModelBasic> listUsuario = new ArrayList<>();
		
		//Buscando tudo no banco de dados de usuario
		String sql = "SELECT * FROM usuario";
		
		//Conectando com o banco de dados
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String telefone = resultSet.getString("telefone");
			String email = resultSet.getString("email");
			String login = resultSet.getString("login");
			String senha = resultSet.getString("senha");
			String nivelAcesso = resultSet.getString("nivelAcesso");
			
			Usuario usuario = new Usuario(id, nome, telefone, email, login, senha, nivelAcesso);
			listUsuario.add(usuario);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listUsuario;
	}

	@Override
	public boolean delete(ModelBasic usuario) throws SQLException {
		//Deletando do banco pelo id de usuario
		String sql = "DELETE FROM usuario WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, usuario.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowDeleted;
	}

	@Override
	public boolean update(ModelBasic model) throws SQLException {
		//Fazendo CAST do tipo ModelBasic para o tipo Usuario
		Usuario usuario = (Usuario) model;
		
		//Atualizando usuario no banco pelo id
		String sql = "UPDATE usuario SET nome = ?, telefone = ?, email = ?, login = ?, senha = ?, nivelAcesso = ?";
		sql += " WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getTelefone());
		statement.setString(3, usuario.getEmail());
		statement.setString(4, usuario.getLogin());
		statement.setString(5, usuario.getSenha());
		statement.setString(6, usuario.getNivelAcesso());
		statement.setInt(7, usuario.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowUpdated;
	}

	@Override
	public ModelBasic getRecord(int id) throws SQLException {
		Usuario usuario = null;
		
		//Buscando usuario pelo id
		String sql = "SELECT * FROM usuario WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String nome = resultSet.getString("nome");
			String telefone = resultSet.getString("telefone");
			String email = resultSet.getString("email");
			String login = resultSet.getString("login");
			String senha = resultSet.getString("senha");
			String nivelAcesso = resultSet.getString("nivelAcesso");
			
			usuario = new Usuario(id, nome, telefone, email, login, senha, nivelAcesso);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return usuario;
	}
}