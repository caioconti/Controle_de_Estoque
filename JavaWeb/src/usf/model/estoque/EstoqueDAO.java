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
		String sql = "SELECT * FROM estoque";
		
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
	
	public Estoque dadosProdutoEstoque(String prod) throws SQLException {
		
		Estoque produtoResultado = null;
		
		String sql = "SELECT * FROM estoque WHERE produto LIKE ?";
		
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, prod);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String produto = resultSet.getString("produto");
			int quantidade = resultSet.getInt("quantidade");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			double valorTotal = resultSet.getDouble("valorTotal");
			
			produtoResultado = new Estoque(produto, valorUnitario, quantidade, valorTotal);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return produtoResultado;
	}

	@Override
	public boolean insert(ModelBasic model) throws SQLException {
		
		/*INSERT da entrada de um produto*/
		
		/*
		 * Fazer a inserção na tabela movimentacao com os dados daqui e adiconar
		 * os itens:
		 * 	- Tipo (neste caso, Entrada);
		 * 	- Data;
		 * 	- Hora;
		 * 	- Usuário;
		 * 
		 * */
		
		Estoque estoque = (Estoque) model;
		Estoque produtoResultado = dadosProdutoEstoque(estoque.getProduto());

		connect();
		
		if (produtoResultado != null) {
			
			int produtoQuantidade = (produtoResultado.getQuantidade() + estoque.getQuantidade());
			double produtoValorTotal = (produtoQuantidade * produtoResultado.getValorUnitario());
			
			String sql = "UPDATE estoque SET quantidade = ?, valorTotal = ? WHERE produto LIKE ?";
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, produtoQuantidade);
			statement.setDouble(2, produtoValorTotal);
			statement.setString(3, estoque.getProduto());
			
			boolean rowInserted = statement.executeUpdate() > 0;
			
			statement.close();
			disconnect();
			
			return rowInserted;
			
			
		} else {
			
			String sql = "INSERT INTO estoque(produto, valorUnitario, quantidade, valorTotal) VALUES(?, ?, ?, ?)";
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, estoque.getProduto());
			statement.setDouble(2, estoque.getValorUnitario());
			statement.setInt(3, estoque.getQuantidade());
			statement.setDouble(4, estoque.getValorTotal());
			
			boolean rowInserted = statement.executeUpdate() > 0;
			
			statement.close();
			disconnect();
			
			return rowInserted;
			
		}
		
	
		
		//System.out.println(estoque.getQuantidade());
		//System.out.println(produtoQuantidade);
		//System.out.println(produtoValorTotal);
		
		
	}
	
	public boolean insertSaida(ModelBasic model) throws SQLException {
		
		/*
		 * 
		 * Fazer a inserção da saída de produtos aqui, da mesma forma que o insert anterior;
		 *  
		 * */
		
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
	
	@Override
	public ModelBasic getRecord(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
