package usf.controller.fornecedor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usf.model.basic.ModelBasic;
import usf.model.fornecedor.Fornecedor;
import usf.model.fornecedor.FornecedorDAO;

public class FornecedorServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private FornecedorDAO fornecedorDAO = null;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcDriver = getServletContext().getInitParameter("jdbcDriver");
		
		fornecedorDAO = new FornecedorDAO(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
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
				insertFornecedor(request, response);
				break;
			case "/delete":
				deleteFornecedor(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateFornecedor(request, response);
				break;
			case "/list" :
				listFornecedor(request, response);
				break;
			default:
				listFornecedor(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		  }
	}
		
	private void listFornecedor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			List<ModelBasic> listFornecedor = fornecedorDAO.listAll();
			request.setAttribute("listFornecedor", listFornecedor);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/fornecedor/FornecedorList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/fornecedor/FornecedorForm.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ModelBasic existingFornecedor = fornecedorDAO.getRecord(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/fornecedor/FornecedorForm.jsp");
			request.setAttribute("fornecedor", existingFornecedor);
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void insertFornecedor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String email = request.getParameter("email");
			String cnpj = request.getParameter("cnpj");
			String cidade = request.getParameter("cidade");
			
			Fornecedor newFornecedor = new Fornecedor(nome, telefone, email, cnpj, cidade);
			fornecedorDAO.insert(newFornecedor);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void updateFornecedor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String email = request.getParameter("email");
			String cnpj = request.getParameter("cnpj");
			String cidade = request.getParameter("cidade");
			
			Fornecedor fornecedor = new Fornecedor(id, nome, telefone, email, cnpj, cidade);
			fornecedorDAO.update(fornecedor);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
		
	private void deleteFornecedor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Fornecedor fornecedor = new Fornecedor(id);
			fornecedorDAO.delete(fornecedor);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
