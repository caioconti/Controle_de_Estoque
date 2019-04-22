package usf.controller.saidaproduto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usf.model.basic.ModelBasic;
import usf.model.saidaproduto.SaidaProduto;
import usf.model.saidaproduto.SaidaProdutoDAO;

public class SaidaProdutoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private SaidaProdutoDAO saidaProdutoDAO = null;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcDriver = getServletContext().getInitParameter("jdbcDriver");
		
		saidaProdutoDAO = new SaidaProdutoDAO(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
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
			List<ModelBasic> listSaidaProduto = saidaProdutoDAO.listAll();
			request.setAttribute("list", listSaidaProduto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/MovimentacaoList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			String nome = request.getParameter("procurar");
			List<ModelBasic> listSaidaProduto = saidaProdutoDAO.search(nome);
			request.setAttribute("listSaidaProduto", listSaidaProduto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/EstoqueList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/SaidaProdutoForm.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ModelBasic existingSaidaProduto = saidaProdutoDAO.getRecord(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/SaidaProdutoForm.jsp");
			request.setAttribute("saidaproduto", existingSaidaProduto);
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			String tipo = request.getParameter("tipo");
			String data = request.getParameter("data");
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			double valorUnitario = Double.parseDouble(request.getParameter("valorUnitario"));
			double valorTotal = Double.parseDouble(request.getParameter("valorTotal"));
			String produto = request.getParameter("produto");
			String usuario = request.getParameter("usuario");
			
			SaidaProduto newSaidaProduto = new SaidaProduto(tipo, data, produto, valorUnitario, quantidade, valorTotal, usuario);
			saidaProdutoDAO.insert(newSaidaProduto);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			String tipo = request.getParameter("tipo");
			String data = request.getParameter("data");
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			double valorUnitario = Double.parseDouble(request.getParameter("valorUnitario"));
			double valorTotal = Double.parseDouble(request.getParameter("valorTotal"));
			String produto = request.getParameter("produto");
			String usuario = request.getParameter("usuario");
			
			SaidaProduto saidaProduto = new SaidaProduto(tipo, data, produto, valorUnitario, quantidade, valorTotal, usuario);
			saidaProdutoDAO.update(saidaProduto);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
		
	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			SaidaProduto saidaProduto = new SaidaProduto(id);
			saidaProdutoDAO.delete(saidaProduto);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}