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
import usf.model.estoque.Estoque;

public class SaidaProdutoDAO extends BasicDAO{
	
	public SaidaProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public SaidaProdutoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword, String jdbcDriver) {
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
		
		SaidaProduto saidaProduto = (SaidaProduto) model;
		
		//Chamando um metódo para pegar os dados do produto do Estoque
		Estoque produtoEstoque = dadosEstoque(saidaProduto.getProduto());

		//Conecatando com o banco de dados
		connect();

		if (saidaProduto.getQuantidade() <= produtoEstoque.getQuantidade()) {
			
			//Quantidade que vai ser atualizada para o  BD estoque
			int attQuantidadeEstoque = produtoEstoque.getQuantidade() - saidaProduto.getQuantidade();
			
			//Inserindo os dados da venda no BD de movimentação
			String sql = "INSERT INTO movimentacao (tipo, data, produto, valorUnitario, quantidade, valorTotal, usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = jdbcConnection.prepareStatement(sql);
			st.setString(1, saidaProduto.getTipo());
			st.setString(2, saidaProduto.getData());
			st.setString(3, saidaProduto.getProduto());
			st.setDouble(4, saidaProduto.getValorUnitario());
			st.setInt(5, saidaProduto.getQuantidade());
			st.setDouble(6, saidaProduto.getValorTotal());
			st.setString(7, saidaProduto.getUsuario());
			
			if (st.executeUpdate() > 0) {
				
				//Atualizando a quantidade do produto no BD estoque
				String sql2 = "UPDATE estoque SET quantidade = ? WHERE produto = ?";				
				
				st = jdbcConnection.prepareStatement(sql2);
				st.setInt(1, attQuantidadeEstoque);
				st.setString(2, saidaProduto.getProduto());
			}
			
			boolean rowInserted = st.executeUpdate() > 0;
			
			st.close();
			disconnect();
			
			return rowInserted;
			
		} else {
			
			/* Retornando falso caso a quantidade em estoque seja menor do que a
			 * quantidade de venda, e não fez o insert
			 */
			return false;
			
		}
		
	}

	@Override
	public List<ModelBasic> listAll() throws SQLException {
		
		List<ModelBasic> listSaidaProduto = new ArrayList<>();
		
		//Buscando os dados no BD
		String sql = "SELECT * FROM movimentacao";
		
		//Conectando com o banco de dados
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		while (rs.next()) {
			String tipo = 	 		rs.getString("tipo");
			Date data =      		rs.getDate("data");
			String produto = 		rs.getString("produto");
			int quantidade = 		rs.getInt("quantidade");
			double valorUnitario = 	rs.getDouble("valorUnitario");
			double valorTotal = 	rs.getDouble("valorTotal");
			String usuario = 		rs.getString("usuario");
			
			//Passando a data para o pabrão BR
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormatada = formatador.format(data);
			
			SaidaProduto saidaProduto = new SaidaProduto(tipo, dataFormatada, produto, valorUnitario, quantidade, valorTotal, usuario);
			listSaidaProduto.add(saidaProduto);
		}
		
		rs.close();
		statement.close();
		disconnect();
		
		return listSaidaProduto;
	}
	
	public List<ModelBasic> search(String produto) throws SQLException {
		
		List<ModelBasic> listaResultado = new ArrayList<>();
		
		//Buscando dados pelo nome de produto
		String sql = "SELECT * FROM movimentacao WHERE produto LIKE ? ";
		//Conectando com o banco de dados
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, '%' + produto + '%');
		
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String tipo = resultSet.getString("tipo");
			String data = resultSet.getString("data");
			double valorUnitario = resultSet.getDouble("valorUnitario");
			int quantidade = resultSet.getInt("quantidade");
			double valorTotal = resultSet.getDouble("valorTotal");
			String item = resultSet.getString("produto");
			String usuario = resultSet.getString("usuario");
			
			SaidaProduto saidaProduto = new SaidaProduto(id, tipo, data, item, valorUnitario, quantidade, valorTotal, usuario);
			listaResultado.add(saidaProduto);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		
		return listaResultado;
	}

	@Override
	public boolean delete(ModelBasic saidaProduto) throws SQLException {
		return false;
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