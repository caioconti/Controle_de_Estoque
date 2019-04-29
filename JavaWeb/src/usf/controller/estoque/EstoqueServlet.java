package usf.controller.estoque;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usf.model.basic.ModelBasic;
import usf.model.entradaproduto.EntradaProduto;
import usf.model.entradaproduto.EntradaProdutoDAO;
import usf.model.estoque.Estoque;
import usf.model.estoque.EstoqueDAO;
import usf.model.saidaproduto.SaidaProduto;
import usf.model.saidaproduto.SaidaProdutoDAO;

public class EstoqueServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private EstoqueDAO estoqueDAO = null;
	private SaidaProdutoDAO saidaDAO = null;
	private EntradaProdutoDAO entradaDAO = null;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcDriver = getServletContext().getInitParameter("jdbcDriver");
		
		estoqueDAO = new EstoqueDAO(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
		saidaDAO = new SaidaProdutoDAO(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
		entradaDAO = new EntradaProdutoDAO(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = ((HttpServletRequest) request).getPathInfo();
		//String action = request.getServletPath();
		
		if (action == null) {
			return;
		}
		
		try {
			switch (action) {
			case "/newEntrada":
				showNewFormEntrada(request, response);
				break;
			case "/newSaida":
				showNewFormSaida(request, response);
				break;	
			case "/insertentrada":
				insertEntrada(request, response);
				break;
			case "/insertsaida":
				insertSaida(request, response);
				break;
			case "/list" :
				list(request, response);
				break;
			case "/search" :
				search(request, response);
				break;
			case "/movi" :
				movi(request, response);
				break;
			default:
				list(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		  }
	}
		
	private void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			List<ModelBasic> listEstoque = estoqueDAO.listAll();
			request.setAttribute("list", listEstoque);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/EstoqueList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void movi(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			List<ModelBasic> listMovi = saidaDAO.listAll();
			request.setAttribute("list", listMovi);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/MovimentacaoList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			String nome = request.getParameter("procurar");
			List<ModelBasic> listEstoque = estoqueDAO.search(nome);
			request.setAttribute("list", listEstoque);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/EstoqueList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showNewFormEntrada(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/EntradaProdutoForm.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showNewFormSaida(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/SaidaProdutoForm.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	private void insertEntrada(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			
			String usuario = "Caio";
			String tipo = "Entrada";
			Date data = new Date();
			
			//Formatando a data para o MySQL aceitar
			SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
			String dataFormatada = formatador.format(data);
			
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			double valorUnitario = Double.parseDouble(request.getParameter("valorUnitario"));
			double valorTotal = Double.parseDouble(request.getParameter("valorTotal"));
			String produto = request.getParameter("produto");
			
			EntradaProduto newEP = new EntradaProduto(tipo, dataFormatada, produto, valorUnitario, quantidade, valorTotal, usuario);
			entradaDAO.insert(newEP);
			
			response.sendRedirect("list");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void insertSaida(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			
			String usuario = "Caio";
			String tipo = "Saida";
			Date data = new Date();
			
			//Formatando a data para o MySQL aceitar
			SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
			String dataFormatada = formatador.format(data);
			
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			double valorUnitario = Double.parseDouble(request.getParameter("valorUnitario"));
			double valorTotal = Double.parseDouble(request.getParameter("valorTotal"));
			String produto = request.getParameter("produto");
			
			SaidaProduto newSP = new SaidaProduto(tipo, dataFormatada, produto, valorUnitario, quantidade, valorTotal, usuario);
			saidaDAO.insert(newSP);
			
			response.sendRedirect("list");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
