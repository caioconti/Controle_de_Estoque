package usf.model.produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import usf.model.basic.BasicDAO;
import usf.model.basic.ModelBasic;

public class ProdutoDAO extends BasicDAO{

	public ProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public ProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
		super(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
	}

	@Override
	public boolean insert(ModelBasic model) throws SQLException {
		Produto produto = (Produto) model;
		
		//Inserindo no banco de dados
		String sql = "INSERT INT produto (nome, valorUnitario, quantidade, descricao, fornecedor) VALUES (?, ?, ?, ?, ?)";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, produto.getNome());
		statement.setDouble(2, produto.getValorUnitario());
		statement.setInt(3, produto.getQuantidade());
		statement.setString(4, produto.getDescricao());
		statement.setString(1, produto.getFornecedor());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowInserted;
	}

	@Override
	public List<ModelBasic> listAll() throws SQLException {
		List<ModelBasic> listProduto = new ArrayList<>();
		
		//Buscando tudo no banco de dados de produto
		String sql = "SELECT * FROM produto";
		
		//Conectando com o banco de dados
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			int quantidade = resultSet.getInt("quantidade");
			String descricao = resultSet.getString("descricao");
			String fornecedor = resultSet.getString("fornecedor");
			
			Produto produto = new Produto(id, nome, valorUnitario, quantidade, descricao, fornecedor);
			listProduto.add(produto);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listProduto;
	}

	@Override
	public boolean delete(ModelBasic produto) throws SQLException {
		//Deletando do banco pelo id de produto
		String sql = "DELETE FROM produto WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, produto.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowDeleted;
	}

	@Override
	public boolean update(ModelBasic model) throws SQLException {
		//Fazendo CAST do tipo ModelBasic para o tipo Produto
		Produto produto = (Produto) model;
		
		//Atualizando produto no banco pelo id
		String sql = "UPDATE produto SET nome = ?, valorUnitario = ?, quantidade = ?, descricao = ?, fornecedor = ?";
		sql += " WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, produto.getNome());
		statement.setDouble(2, produto.getValorUnitario());
		statement.setInt(3, produto.getQuantidade());
		statement.setString(4, produto.getDescricao());
		statement.setString(5, produto.getFornecedor());
		statement.setInt(6, produto.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowUpdated;
	}

	@Override
	public ModelBasic getRecord(int id) throws SQLException {
		Produto produto = null;
		
		//Buscando produto pelo id
		String sql = "SELECT * FROM produto WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String nome = resultSet.getString("nome");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			int quantidade = resultSet.getInt("quantidade");
			String descricao = resultSet.getString("descricao");
			String fornecedor = resultSet.getString("fornecedor");
			
			produto = new Produto(id, nome, valorUnitario, quantidade, descricao, fornecedor);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return produto;
	}
}
