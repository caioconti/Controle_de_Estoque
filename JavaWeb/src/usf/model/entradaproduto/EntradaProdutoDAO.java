package usf.model.entradaproduto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import usf.model.basic.BasicDAO;
import usf.model.basic.ModelBasic;
import usf.model.estoque.Estoque;

public class EntradaProdutoDAO extends BasicDAO{
	
	public EntradaProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public EntradaProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
		super(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
	}

	public Estoque dadosEstoque(String produto) throws SQLException {
		
		Estoque produtoEncontrado = null;
		
		String sql = "SELECT * FROM estoque WHERE produto = ?";
		connect();
		
		PreparedStatement st = jdbcConnection.prepareStatement(sql);
		st.setString(1, produto);
		
		ResultSet rs = st.executeQuery();
		
		if (rs.next()) {
			String item = rs.getString("produto");
			int quantidade = rs.getInt("quantidade");
			
			produtoEncontrado = new Estoque(item, quantidade);
			
		}
		
		rs.close();
		st.close();
		disconnect();
		
		return produtoEncontrado;
		
	}
	
	@Override
	public boolean insert(ModelBasic model) throws SQLException {
		
		EntradaProduto entradaProduto = (EntradaProduto) model;
		Estoque produtoEstoque = dadosEstoque(entradaProduto.getProduto());
		
		//Conecatando com o banco de dados
		connect();
		
		if (produtoEstoque != null) {
			
			//Quantidade que vai ser atualizada para o  BD estoque
			int attQuantidadeEstoque = produtoEstoque.getQuantidade() + entradaProduto.getQuantidade();
			
			//Inserindo os dados da venda no BD de movimentação
			String sql = "INSERT INTO movimentacao (tipo, data, produto, valorUnitario, quantidade, valorTotal, usuario) VALUES (?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement st = jdbcConnection.prepareStatement(sql);
			st.setString(1, entradaProduto.getTipo());
			st.setString(2, entradaProduto.getData());
			st.setString(3, entradaProduto.getProduto());
			st.setDouble(4, entradaProduto.getValorUnitario());
			st.setInt(5, entradaProduto.getQuantidade());
			st.setDouble(6, entradaProduto.getValorTotal());
			st.setString(7, entradaProduto.getUsuario());
			
			if (st.executeUpdate() > 0) {
				
				//Atualizando a quantidade do produto no BD estoque
				String sql2 = "UPDATE estoque SET quantidade = ? WHERE produto = ?";	
				
				st = jdbcConnection.prepareStatement(sql2);
				st.setInt(1, attQuantidadeEstoque);
				st.setString(2, entradaProduto.getProduto());
				
			}
			
			boolean rowInserted = st.executeUpdate() > 0;
			
			st.close();
			disconnect();
			
			return rowInserted;
			
		} else {
			
			//Inserindo os dados da venda no BD de movimentação
			String sql = "INSERT INTO movimentacao (tipo, data, produto, valorUnitario, quantidade, valorTotal, usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = jdbcConnection.prepareStatement(sql);
			
			st.setString(1, entradaProduto.getTipo());
			st.setString(2, entradaProduto.getData());
			st.setString(3, entradaProduto.getProduto());
			st.setDouble(4, entradaProduto.getValorUnitario());
			st.setInt(5, entradaProduto.getQuantidade());
			st.setDouble(6, entradaProduto.getValorTotal());
			st.setString(7, entradaProduto.getUsuario());
			
			if (st.executeUpdate() > 0) {
				
				//Inserindo no estoque o produto e a quantidade
				String sql2 = "INSERT INTO estoque (produto, quantidade) VALUES(?, ?)";
				
				st = jdbcConnection.prepareStatement(sql2);
				st.setString(1, entradaProduto.getProduto());
				st.setInt(2, entradaProduto.getQuantidade());
				
			}
			
			boolean rowInserted = st.executeUpdate() > 0;
			
			st.close();
			disconnect();
			
			return rowInserted;
			
		}

	}

	@Override
	public List<ModelBasic> listAll() throws SQLException {
		List<ModelBasic> listEntradaProduto = new ArrayList<>();
		
		//Buscando tudo no banco de dados de entradaProduto
		String sql = "SELECT * FROM movimentacao";
		
		//Conectando com o banco de dados
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			String tipo = resultSet.getString("tipo");
			Date data = resultSet.getDate("data");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			int quantidade = resultSet.getInt("quantidade");
			double valorTotal = resultSet.getDouble("valorTotal");
			String produto = resultSet.getString("produto");
			String usuario = resultSet.getString("usuario");
			
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormatada = formatador.format(data);
			
			EntradaProduto entradaProduto = new EntradaProduto(tipo, dataFormatada, produto, valorUnitario, quantidade, valorTotal, usuario);
			listEntradaProduto.add(entradaProduto);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listEntradaProduto;
	}
	
	public List<ModelBasic> search(String nome) throws SQLException {
		List<ModelBasic> listEntradaProduto = new ArrayList<>();
		//Buscando produto pelo id
		String sql = "SELECT * FROM movimentacao WHERE nome LIKE ? ";
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, '%' + nome + '%');
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			String tipo = resultSet.getString("tipo");
			String data = resultSet.getString("data");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			int quantidade = resultSet.getInt("quantidade");
			double valorTotal = resultSet.getDouble("valorTotal");
			String produto = resultSet.getString("produto");
			String usuario = resultSet.getString("usuario");
			
			EntradaProduto entradaProduto = new EntradaProduto(tipo, data, produto, valorUnitario, quantidade, valorTotal, usuario);
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
		String sql = "DELETE FROM movimentacao WHERE id = ?";
		
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
		String sql = "UPDATE movimentacao SET tipo = ?, data = ?, produto = ?, valorUnitario = ?, quantidade = ?, valorTotal = ?, usuario = ?";
		sql += " WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, entradaProduto.getTipo());
		statement.setString(2, entradaProduto.getData());
		statement.setString(3, entradaProduto.getUsuario());
		statement.setString(4, entradaProduto.getProduto());
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
		
		String sql = "SELECT * FROM movimentacao WHERE id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String tipo = resultSet.getString("tipo");
			String data = resultSet.getString("data");
			String usuario = resultSet.getString("usuario");
			String produto = resultSet.getString("produto");
			int quantidade = resultSet.getInt("quantidade");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			double valorTotal = resultSet.getDouble("valorTotal");
			
			entradaProduto = new EntradaProduto(tipo, data, produto, valorUnitario, quantidade, valorTotal, usuario);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return entradaProduto;
	}
}