package usf.model.saidaproduto;

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

public class SaidaProdutoDAO extends BasicDAO{
	
	public SaidaProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public SaidaProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
		super(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
	}


	@Override
	public boolean insert(ModelBasic model) throws SQLException {
		
		SaidaProduto saidaproduto = (SaidaProduto) model;
	
		//Inserindo no banco de dados
		String sql = "INSERT INTO movimentacao (tipo, data, produto, valorUnitario, quantidade, valorTotal) VALUES (?, ?, ?, ?, ?, ?)";

		//Conecatando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, saidaproduto.getTipo());
		statement.setString(2, saidaproduto.getData());
		statement.setString(3, saidaproduto.getProduto());
		statement.setDouble(4, saidaproduto.getValorUnitario());
		statement.setInt(5, saidaproduto.getQuantidade());
		statement.setDouble(6, saidaproduto.getValorTotal());
		
		boolean rowInserted = statement.executeUpdate() > 0;
	
		statement.close();
		disconnect();
		
		return rowInserted;
	}

	@Override
	public List<ModelBasic> listAll() throws SQLException {
		
		List<ModelBasic> listSaidaProduto = new ArrayList<>();
		
		//Buscando tudo no banco de dados de entradaProduto
		String sql = "SELECT * FROM movimentacao";
		
		//Conectando com o banco de dados
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			String tipo = resultSet.getString("tipo");
			Date data = resultSet.getDate("data");
			String usuario = resultSet.getString("usuario");
			String produto = resultSet.getString("produto");
			int quantidade = resultSet.getInt("quantidade");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			double valorTotal = resultSet.getDouble("valorTotal");
			
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormatada = formatador.format(data);
			
			SaidaProduto saidaProduto = new SaidaProduto(tipo, dataFormatada, produto, valorUnitario, quantidade, valorTotal, usuario);
			listSaidaProduto.add(saidaProduto);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listSaidaProduto;
	}
	
	public List<ModelBasic> search(String nome) throws SQLException {
		List<ModelBasic> listSaidaProduto = new ArrayList<>();
		//Buscando produto pelo id
		String sql = "SELECT * FROM estoque WHERE nome LIKE ? ";
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, '%' + nome + '%');
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String tipo = resultSet.getString("tipo");
			String data = resultSet.getString("data");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			int quantidade = resultSet.getInt("quantidade");
			double valorTotal = resultSet.getDouble("valorTotal");
			String produto = resultSet.getString("produto");
			String usuario = resultSet.getString("usuario");
			
			SaidaProduto saidaProduto = new SaidaProduto(id, tipo, data, produto, valorUnitario, quantidade, valorTotal, usuario);
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
		String sql = "DELETE FROM movimentacao WHERE id = ?";
		
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
		String sql = "UPDATE movimentacao SET tipo = ?, data = ?, produto = ?, valorUnitario = ?, quantidade = ?, valorTotal = ?, usuario = ?";
		sql += " WHERE id = ?";
		
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, saidaProduto.getTipo());
		statement.setString(2, saidaProduto.getData());
		statement.setString(3, saidaProduto.getProduto());
		statement.setDouble(4, saidaProduto.getValorUnitario());
		statement.setInt(5, saidaProduto.getQuantidade());
		statement.setDouble(6, saidaProduto.getValorTotal());
		statement.setString(7, saidaProduto.getUsuario());
		statement.setInt(8, saidaProduto.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		
		statement.close();
		disconnect();
		
		return rowUpdated;
	}

	@Override
	public ModelBasic getRecord(int id) throws SQLException {
		
		SaidaProduto saidaProduto = null;
		
		String sql = "SELECT * FROM estoque WHERE id = ?";
		
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
			
			saidaProduto = new SaidaProduto(tipo, data, produto, valorUnitario, quantidade, valorTotal, usuario);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return saidaProduto;
	}
}