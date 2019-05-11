package usf.controller.usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import usf.model.basic.ModelBasic;
import usf.model.usuario.Usuario;
import usf.model.usuario.UsuarioDAO;

public class UsuarioServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO = null;
	
	public UsuarioServlet() {
		super();
	}
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		String jdbcDriver = getServletContext().getInitParameter("jdbcDriver");
		
		usuarioDAO = new UsuarioDAO(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
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
				insertUsuario(request, response);
				break;
			case "/delete":
				deleteUsuario(request, response);
				break;
			case "/edit":
				showEditUsuario(request, response);
				break;
			case "/update":
				updateUsuario(request, response);
				break;
			case "/list" :
				listUsuario(request, response);
				break;
			case "/search" :
				searchUsuario(request, response);
				break;
			case "/aut" :
				autentica(request, response);
				break;
			case "/des" :
				deslogar(request, response);
				break;
			default:
				listUsuario(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private void listUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			List<ModelBasic> listUsuario = usuarioDAO.listAll();
			request.setAttribute("listUsuario", listUsuario);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/usuario/UsuarioList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void searchUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			String nome = request.getParameter("procurar");
			List<ModelBasic> listUsuario = usuarioDAO.searchUsuario(nome);
			request.setAttribute("listUsuario", listUsuario);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/usuario/UsuarioList.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/usuario/UsuarioForm.jsp");
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void showEditUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ModelBasic existingUsuario = usuarioDAO.getRecord(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/usuario/UsuarioForm.jsp");
			request.setAttribute("usuario", existingUsuario);
			dispatcher.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void insertUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			Usuario newUsuario = new Usuario(nome, telefone, email, login, senha);
			usuarioDAO.insert(newUsuario);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void updateUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			Usuario usuario = new Usuario(id, nome, telefone, email, login, senha);
			usuarioDAO.update(usuario);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private void deleteUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Usuario usuario = new Usuario(id);
			usuarioDAO.delete(usuario);
			response.sendRedirect("list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void autentica(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			//Pegando login e senha digitados
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			//Testa se foi colocado um login e senha, caso n�o tenha passa direto para o else
			if(login != null && senha != null && !login.isEmpty() && !senha.isEmpty()) {
			
				Usuario usuario = new Usuario(login, senha);
				
				//Verifica se o login e senha constam no BD
				if (usuarioDAO.autentica(usuario)) {
				
					//Criando uma sess�o para o usuario	
					HttpSession session =  request.getSession();
					session.setAttribute("usuario", usuario);
					
					//Redirecionando para a tela principal depois de criar uma sess�o
					RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
					dispatcher.forward(request, response);
				
				//Se n�o constarem no BD, redireciona para a pagina de login
				} else {
					response.sendRedirect("../index.jsp");
				}
			
			//Se n�o houver login e senha, redireciona para a pagina de login	
			} else {
				response.sendRedirect("../index.jsp");
			}
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void deslogar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Destruindo a sess�o atual
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("../index.jsp");
	}
	
}