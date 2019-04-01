package usf.model.fornecedor;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.model.basic.BasicDAO;
import usf.model.basic.ModelBasic;

public class FornecedorDAO extends BasicDAO {

	public FornecedorDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public FornecedorDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
		super(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
	}

	@Override
	public boolean insert(ModelBasic model) throws SQLException {
		Fornecedor fornecedor = (Fornecedor) model;
		
		//Inserindo no banco de dados
		String sql = "INSERT INTO fornecedor (nome, telefone, email, cnpj, cidade) VALUES (?, ?, ?, ?, ?)";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, fornecedor.getNome());
		statement.setString(2, fornecedor.getTelefone());
		statement.setString(3, fornecedor.getEmail());
		statement.setString(4, fornecedor.getCnpj());
		statement.setString(5, fornecedor.getCidade());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowInserted;
	}

	@Override
	public List<ModelBasic> listAll() throws SQLException {
		List<ModelBasic> listFornecedor = new ArrayList<>();
		
		//Buscando tudo no banco de dados de fornecedor
		String sql = "SELECT * FROM fornecedor";
		
		//Conectando com o banco de dados
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String telefone = resultSet.getString("telefone");
			String email = resultSet.getString("email");
			String cnpj = resultSet.getString("cnpj");
			String cidade = resultSet.getString("cidade");
			
			Fornecedor fornecedor = new Fornecedor(id, nome, telefone, email, cnpj, cidade);
			listFornecedor.add(fornecedor);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listFornecedor;
	}
	
	public List<ModelBasic> searchFornecedor(String nome) throws SQLException {
		List<ModelBasic> listFornecedor = new ArrayList<>();
		//Buscando produto pelo id
		String sql = "SELECT * FROM fornecedor WHERE nome LIKE ? ";
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
			String cnpj = resultSet.getString("cnpj");
			String cidade = resultSet.getString("cidade");
			
			Fornecedor fornecedor = new Fornecedor(id, nomeRS, telefone, email, cnpj, cidade);
			listFornecedor.add(fornecedor);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listFornecedor;
	}

	@Override
	public boolean delete(ModelBasic fornecedor) throws SQLException {
		//Deletando do banco pelo id de fornecedor
		String sql = "DELETE FROM fornecedor WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, fornecedor.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
				
		return rowDeleted;
	}

	@Override
	public boolean update(ModelBasic model) throws SQLException {
		//Fazendo CAST do tipo ModelBasic para o tipo Fornecedor
		Fornecedor fornecedor = (Fornecedor) model;
		
		//Atualizando fornecedor no banco pelo id
		String sql = "UPDATE fornecedor SET nome = ?, telefone = ?, email = ?, cnpj = ?, cidade = ?";
		sql += " WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, fornecedor.getNome());
		statement.setString(2, fornecedor.getTelefone());
		statement.setString(3, fornecedor.getEmail());
		statement.setString(4, fornecedor.getCnpj());
		statement.setString(5, fornecedor.getCidade());
		statement.setInt(6, fornecedor.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowUpdated;
	}

	@Override
	public ModelBasic getRecord(int id) throws SQLException {
		Fornecedor fornecedor = null;
		
		//Buscando fornecedor pelo id
		String sql = "SELECT * FROM fornecedor WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String nome = resultSet.getString("nome");
			String telefone = resultSet.getString("telefone");
			String email = resultSet.getString("email");
			String cnpj = resultSet.getString("cnpj");
			String cidade = resultSet.getString("cidade");
			
			fornecedor = new Fornecedor(id, nome, telefone, email, cnpj, cidade);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return fornecedor;
	}
}
