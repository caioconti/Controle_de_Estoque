package usf.model.saidaproduto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import usf.model.basic.BasicDAO;
import usf.model.basic.ModelBasic;

public class SaidaProdutoDAO extends BasicDAO{
	
	public SaidaProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public SaidaProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
		super(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
	}

	@Override
	public boolean insert(ModelBasic model) throws SQLException {
		
		SaidaProduto entradaProduto = (SaidaProduto) model;
		
		//Inserindo no banco de dados
		String sql = "INSERT INTO saidaProduto (data, usuario, produto, quantidade, valorUnitario, valorTotal) VALUES (?, ?, ?, ?, ?, ?)";
		
		//Conecatando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entradaProduto.getData());
		statement.setString(2, entradaProduto.getUsuario());
		statement.setString(3, entradaProduto.getProduto());
		statement.setInt(4, entradaProduto.getQuantidade());
		statement.setDouble(5, entradaProduto.getValorUnitario());
		statement.setDouble(6, entradaProduto.getValorTotal());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowInserted;
	}

	@Override
	public List<ModelBasic> listAll() throws SQLException {
		
		List<ModelBasic> listSaidaProduto = new ArrayList<>();
		
		//Buscando tudo no banco de dados de entradaProduto
		String sql = "SELECT * FROM saidaProduto";
		
		//Conectando com o banco de dados
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String data = resultSet.getString("data");
			String usuario = resultSet.getString("usuario");
			String produto = resultSet.getString("produto");
			int quantidade = resultSet.getInt("quantidade");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			double valorTotal = resultSet.getDouble("valorTotal");
			
			SaidaProduto saidaProduto = new SaidaProduto(id, data, usuario, produto, quantidade, valorUnitario, valorTotal);
			listSaidaProduto.add(saidaProduto);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listSaidaProduto;
	}

	@Override
	public boolean delete(ModelBasic saidaProduto) throws SQLException {
		
		//Deletando do banco pelo id de EntradaProduto
		String sql = "DELETE FROM saidaProduto WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, saidaProduto.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowDeleted;
	}

	@Override
	public boolean update(ModelBasic model) throws SQLException {
		
		//Fazendo CAST do tipo ModelBasic para o tipo EntradaProduto
		SaidaProduto saidaProduto = (SaidaProduto) model;
		
		//Atualizando EntradaProduto no banco pelo id
		String sql = "UPDATE saidaProduto SET data = ?, usuario = ?, produto = ?, quantidade = ?, valorUnitario = ?, valorTotal = ?";
		sql += " WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, saidaProduto.getData());
		statement.setString(2, saidaProduto.getUsuario());
		statement.setString(3, saidaProduto.getProduto());
		statement.setInt(4, saidaProduto.getQuantidade());
		statement.setDouble(5, saidaProduto.getValorUnitario());
		statement.setDouble(6, saidaProduto.getValorTotal());
		statement.setInt(7, saidaProduto.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowUpdated;
	}

	@Override
	public ModelBasic getRecord(int id) throws SQLException {
		
		SaidaProduto saidaProduto = null;
		
		String sql = "SELECT * FROM saidaProduto WHERE id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String data = resultSet.getString("data");
			String usuario = resultSet.getString("usuario");
			String produto = resultSet.getString("produto");
			int quantidade = resultSet.getInt("quantidade");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			double valorTotal = resultSet.getDouble("valorTotal");
			
			saidaProduto = new SaidaProduto(id, data, usuario, produto, quantidade, valorUnitario, valorTotal);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return saidaProduto;
	}
}