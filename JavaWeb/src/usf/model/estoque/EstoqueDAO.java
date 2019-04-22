package usf.model.estoque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import usf.model.basic.BasicDAO;
import usf.model.basic.ModelBasic;

public class EstoqueDAO extends BasicDAO{
	
	public EstoqueDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public EstoqueDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
		super(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
	}

	@Override
	public List<ModelBasic> listAll() throws SQLException {
		
		List<ModelBasic> listEstoque = new ArrayList<>();
		
		//Buscando tudo no banco de dados de entradaProduto
		String sql = "SELECT produto, valorUnitario, quantidade, valorTotal FROM estoque";
		
		//Conectando com o banco de dados
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			String produto = resultSet.getString("produto");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			int quantidade = resultSet.getInt("quantidade");
			double valorTotal = resultSet.getDouble("valorTotal");
			
			Estoque estoque = new Estoque(produto, valorUnitario, quantidade, valorTotal);
			listEstoque.add(estoque);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listEstoque;
	}
	
	public List<ModelBasic> search(String nome) throws SQLException {
		
		List<ModelBasic> listEstoque = new ArrayList<>();
		
		//Buscando produto pelo id
		String sql = "SELECT * FROM estoque WHERE produto LIKE ? ";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, '%' + nome + '%');
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			String produto = resultSet.getString("produto");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			int quantidade = resultSet.getInt("quantidade");
			double valorTotal = resultSet.getDouble("valorTotal");
			
			Estoque estoque = new Estoque(produto, valorUnitario, quantidade, valorTotal);
			listEstoque.add(estoque);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listEstoque;
	}

	@Override
	public ModelBasic getRecord(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(ModelBasic model) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean update(ModelBasic model) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean delete(ModelBasic entradaProduto) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
