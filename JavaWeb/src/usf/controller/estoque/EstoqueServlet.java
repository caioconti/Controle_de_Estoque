package usf.controller.estoque;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usf.model.basic.ModelBasic;
import usf.model.estoque.EstoqueDAO;

public class EstoqueServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private EstoqueDAO estoqueDAO = null;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcDriver = getServletContext().getInitParameter("jdbcDriver");
		
		estoqueDAO = new EstoqueDAO(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
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
			List<ModelBasic> listEstoque = estoqueDAO.listAll();
			request.setAttribute("list", listEstoque);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/estoque/EstoqueList.jsp");
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
}
