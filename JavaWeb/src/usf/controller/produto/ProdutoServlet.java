package usf.controller.produto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usf.model.basic.ModelBasic;
import usf.model.produto.Produto;
import usf.model.produto.ProdutoDAO;

public class ProdutoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ProdutoDAO produtoDAO = null;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcDriver = getServletContext().getInitParameter("jdbcDriver");
		
		produtoDAO = new ProdutoDAO(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
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
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insert(request, response);
				break;
			case "/delete":
				delete(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				update(request, response);
				break;
			case "/list" :
				list(request, response);
				break;
			case "/search" :
				search(request, response);
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
			List<ModelBasic> listProduto = produtoDAO.listAll();
			request.setAttribute("listProduto", listProduto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/produto/ProdutoList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			String nome = request.getParameter("procurar");
			System.out.print(nome);
			List<ModelBasic> listProduto = produtoDAO.searchProduto(nome);
			request.setAttribute("listProduto", listProduto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/produto/ProdutoList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/produto/ProdutoForm.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ModelBasic existingProduto = produtoDAO.getRecord(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/produto/ProdutoForm.jsp");
			request.setAttribute("produto", existingProduto);
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			String nome = request.getParameter("nome");
			double valorCompra = Double.parseDouble(request.getParameter("valorCompra"));
			double valorVenda = Double.parseDouble(request.getParameter("valorVenda"));
			String descricao = request.getParameter("descricao");
			String fornecedor = request.getParameter("fornecedor");
			
			Produto newProduto = new Produto(nome, valorCompra, valorVenda, descricao, fornecedor);
			produtoDAO.insert(newProduto);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String nome = request.getParameter("nome");
			double valorCompra = Double.parseDouble(request.getParameter("valorCompra"));
			double valorVenda = Double.parseDouble(request.getParameter("valorVenda"));
			String descricao = request.getParameter("descricao");
			String fornecedor = request.getParameter("fornecedor");
			
			Produto produto = new Produto(id, nome, valorVenda, valorCompra, descricao, fornecedor);
			produtoDAO.update(produto);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
		
	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Produto produto = new Produto(id);
			produtoDAO.delete(produto);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}