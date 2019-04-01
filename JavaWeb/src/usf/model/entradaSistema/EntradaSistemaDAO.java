package usf.model.entradaSistema;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import usf.model.basic.BasicDAO;
import usf.model.basic.ModelBasic;

public class EntradaSistemaDAO extends BasicDAO {

	public EntradaSistemaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public EntradaSistemaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
		super(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
	}

	@Override
	public boolean insert(ModelBasic model) throws SQLException {
		EntradaSistema entradaSistema = (EntradaSistema) model;
		
		//Inserindo no banco de dados
		String sql = "INSERT INTO fornecedor (nome, login, data, hora) VALUES (?, ?, ?, ?)";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entradaSistema.getNome());
		statement.setString(2, entradaSistema.getLogin());
		statement.setString(3, entradaSistema.getData());
		statement.setString(4, entradaSistema.getHora());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowInserted;
	}

	@Override
	public List<ModelBasic> listAll() throws SQLException {
		List<ModelBasic> listEntradaSistema = new ArrayList<>();
		
		String sql = "SELECT * FROM entradaSistema";
		
		//Conectando com o banco de dados
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String login = resultSet.getString("login");
			String data = resultSet.getString("data");
			String hora = resultSet.getString("hora");
			
			EntradaSistema entradaSistema = new EntradaSistema(id, nome, login, data, hora);
			listEntradaSistema.add(entradaSistema);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listEntradaSistema;
	}

	@Override
	public boolean delete(ModelBasic model) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ModelBasic model) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ModelBasic getRecord(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
