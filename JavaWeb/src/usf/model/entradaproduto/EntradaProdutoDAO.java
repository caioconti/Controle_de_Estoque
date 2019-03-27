package usf.model.entradaproduto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import usf.model.basic.BasicDAO;
import usf.model.basic.ModelBasic;

public class EntradaProdutoDAO extends BasicDAO{
	
	public EntradaProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public EntradaProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
		super(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
	}

	@Override
	public boolean insert(ModelBasic model) throws SQLException {
		EntradaProduto entradaProduto = (EntradaProduto) model;
		
		//Inserindo no banco de dados
		String sql = "INSERT INTO entradaProduto (data, usuario, produto, fornecedor, quantidade, valorUnitario, valorTotal) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		//Conecatando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entradaProduto.getData());
		statement.setString(2, entradaProduto.getUsuario());
		statement.setString(3, entradaProduto.getProduto());
		statement.setString(4, entradaProduto.getFornecedor());
		statement.setInt(5, entradaProduto.getQuantidade());
		statement.setDouble(6, entradaProduto.getValorUnitario());
		statement.setDouble(7, entradaProduto.getValorTotal());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowInserted;
	}

	@Override
	public List<ModelBasic> listAll() throws SQLException {
		List<ModelBasic> listEntradaProduto = new ArrayList<>();
		
		//Buscando tudo no banco de dados de entradaProduto
		String sql = "SELECT * FROM entradaProduto";
		
		//Conectando com o banco de dados
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String data = resultSet.getString("data");
			String usuario = resultSet.getString("usuario");
			String produto = resultSet.getString("produto");
			String fornecedor = resultSet.getString("fornecedor");
			int quantidade = resultSet.getInt("quantidade");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			double valorTotal = resultSet.getDouble("valorTotal");
			
			EntradaProduto entradaProduto = new EntradaProduto(id, data, usuario, produto, fornecedor, quantidade, valorUnitario, valorTotal);
			listEntradaProduto.add(entradaProduto);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listEntradaProduto;
	}

	@Override
	public boolean delete(ModelBasic entradaProduto) throws SQLException {
		//Deletando do banco pelo id de EntradaProduto
		String sql = "DELETE FROM entradaProduto WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, entradaProduto.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowDeleted;
	}

	@Override
	public boolean update(ModelBasic model) throws SQLException {
		//Fazendo CAST do tipo ModelBasic para o tipo EntradaProduto
		EntradaProduto entradaProduto = (EntradaProduto) model;
		
		//Atualizando EntradaProduto no banco pelo id
		String sql = "UPDATE entradaProduto SET data = ?, usuario = ?, produto = ?, fornecedor = ?, quantidade = ?, valorUnitario = ?, valorTotal = ?";
		sql += " WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entradaProduto.getData());
		statement.setString(2, entradaProduto.getUsuario());
		statement.setString(3, entradaProduto.getProduto());
		statement.setString(4, entradaProduto.getFornecedor());
		statement.setInt(5, entradaProduto.getQuantidade());
		statement.setDouble(6, entradaProduto.getValorUnitario());
		statement.setDouble(7, entradaProduto.getValorTotal());
		statement.setInt(8, entradaProduto.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowUpdated;
	}

	@Override
	public ModelBasic getRecord(int id) throws SQLException {
		EntradaProduto entradaProduto = null;
		
		String sql = "SELECT * FROM entradaProduto WHERE id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String data = resultSet.getString("data");
			String usuario = resultSet.getString("usuario");
			String produto = resultSet.getString("produto");
			String fornecedor = resultSet.getString("fornecedor");
			int quantidade = resultSet.getInt("quantidade");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			double valorTotal = resultSet.getDouble("valorTotal");
			
			entradaProduto = new EntradaProduto(id, data, usuario, produto, fornecedor, quantidade, valorUnitario, valorTotal);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return entradaProduto;
	}
}