package usf.controller.entradaproduto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usf.model.basic.ModelBasic;
import usf.model.entradaproduto.EntradaProduto;
import usf.model.entradaproduto.EntradaProdutoDAO;

public class EntradaProdutoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private EntradaProdutoDAO entradaProdutoDAO = null;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcDriver = getServletContext().getInitParameter("jdbcDriver");
		
		entradaProdutoDAO = new EntradaProdutoDAO(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
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
			List<ModelBasic> listEntradaProduto = entradaProdutoDAO.listAll();
			request.setAttribute("listEntradaProduto", listEntradaProduto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/EstoqueList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			String nome = request.getParameter("procurar");
			List<ModelBasic> listEntradaProduto = entradaProdutoDAO.search(nome);
			request.setAttribute("listEntradaProduto", listEntradaProduto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/EstoqueList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/EntradaProdutoForm.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ModelBasic existingEntradaProduto = entradaProdutoDAO.getRecord(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/EntradaProdutoForm.jsp");
			request.setAttribute("entradaproduto", existingEntradaProduto);
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			String data = request.getParameter("data");
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			double valorUnitario = Double.parseDouble(request.getParameter("valorUnitario"));
			double valorTotal = Double.parseDouble(request.getParameter("valorTotal"));
			String produto = request.getParameter("produto");
			String fornecedor = request.getParameter("fornecedor");
			String usuario = request.getParameter("usuario");
			
			EntradaProduto newEntradaProduto = new EntradaProduto(data, produto, valorUnitario, quantidade, valorTotal, fornecedor, usuario);
			entradaProdutoDAO.insert(newEntradaProduto);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			String data = request.getParameter("data");
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			double valorUnitario = Double.parseDouble(request.getParameter("valorUnitario"));
			double valorTotal = Double.parseDouble(request.getParameter("valorTotal"));
			String produto = request.getParameter("produto");
			String fornecedor = request.getParameter("fornecedor");
			String usuario = request.getParameter("usuario");
			
			EntradaProduto entradaProduto = new EntradaProduto(data, produto, valorUnitario, quantidade, valorTotal, fornecedor, usuario);
			entradaProdutoDAO.update(entradaProduto);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
		
	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			EntradaProduto entradaProduto = new EntradaProduto(id);
			entradaProdutoDAO.delete(entradaProduto);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
