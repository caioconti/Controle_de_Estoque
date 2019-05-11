package usf.model.usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import usf.model.basic.BasicDAO;
import usf.model.basic.ModelBasic;

public class UsuarioDAO extends BasicDAO {

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
		String sql = "INSERT INTO usuario (nome, telefone, email, login, senha) VALUES (?, ?, ?, ?, ?)";
		
		//Conecatando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getTelefone());
		statement.setString(3, usuario.getEmail());
		statement.setString(4, usuario.getLogin());
		statement.setString(5, usuario.getSenha());
		
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
			String senha = Usuario.getPassword(resultSet.getString("senha"), ILogin.SHA_256);
			
			Usuario usuario = new Usuario(id, nome, telefone, email, login, senha);
			listUsuario.add(usuario);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listUsuario;
	}
	
	public List<ModelBasic> searchUsuario(String nome) throws SQLException {
		List<ModelBasic> listUsuario = new ArrayList<>();
		//Buscando produto pelo id
		String sql = "SELECT * FROM usuario WHERE nome LIKE ? ";
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, '%' + nome + '%');
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String nomeRS = resultSet.getString("nome");
			String telefone = resultSet.getString("telefone");
			String email = resultSet.getString("email");
			String login = resultSet.getString("login");
			String senha = resultSet.getString("senha");
			
			Usuario Usuario = new Usuario(id, nomeRS, telefone, email, login, senha);
			listUsuario.add(Usuario);
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
		String sql = "UPDATE usuario SET nome = ?, telefone = ?, email = ?, login = ?, senha = ?";
		sql += " WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getTelefone());
		statement.setString(3, usuario.getEmail());
		statement.setString(4, usuario.getLogin());
		statement.setString(5, usuario.getSenha());
		statement.setInt(6, usuario.getId());
		
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
			
			usuario = new Usuario(id, nome, telefone, email, login, senha);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return usuario;
	}
	
	public ModelBasic retornaDados(String login) throws SQLException {
		Usuario usuario = null;
		
		String sql = "SELECT * FROM usuario WHERE login = ?";
		connect();
		
		PreparedStatement st = jdbcConnection.prepareStatement(sql);
		st.setString(1, login);
		
		ResultSet rs = st.executeQuery();
		
		if (rs.next()) {
			
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String telefone = rs.getString("telefone");
			String email = rs.getString("email");
			String senha = rs.getString("senha");
			
			usuario = new Usuario(id, nome, telefone, email, login, senha);
		}
		
		rs.close();
		st.close();
		disconnect();
		
		return usuario;
	}
	
	public boolean autentica(Usuario usuario) throws SQLException {
		
		String sql = "SELECT * FROM usuario WHERE login = ? and senha = ? ";
		
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, usuario.getLogin());
		statement.setString(2, Usuario.getPassword(usuario.getSenha(), ILogin.SHA_256));
		
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			return true;
		}
		
		return false;
	}

}