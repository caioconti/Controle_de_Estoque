<%@page import="usf.model.usuario.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<title>CCSEstoque - Controle de Estoque</title>
	<style>
		body {
			background: #9777da !important;
		}
		
		.bloco div {
			padding: 40px;
			text-align: center;
		}
		
		.bloco a {
			color: white;
		}
		
		.bloco a:hover {
			text-decoration: none;
			color: white;
		}
		
		.botao-sair {
			padding-left: 20px !important;
			padding-right: 20px !important;
		}
		
		.texto-centro {
			text-align: center;
		}
	</style>
</head>
<%
	String path = this.getServletContext().getContextPath();

	HttpSession usuario =  request.getSession();
	
	Usuario nome = (Usuario) usuario.getAttribute("usuario");
	
	if(nome == null) {
		response.sendRedirect("index.jsp");
	}
	
%>
<body>
	<header>
		<nav class="navbar navbar-expand-lg bg-dark">
			<a class="navbar-brand text-white" href="home.jsp">Home</a>
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item"><a
						class="nav-link text-white btn btn-danger botao-sair" href="<%=path %>/usuario/des">Sair</a>
					</li>
				</ul>
			</div>
		</nav>
	</header>
	<div class="container">
		<br>
		<div class="row bloco">
			<div class="col-4">
				<a href="<%=path %>/produto/list"><div class="bg-dark">Produtos</div></a>
			</div>
			<div class="col-4">
				<a href="<%=path %>/fornecedor/list"><div class="bg-dark">Fornecedores</div></a>
			</div>
			<div class="col-4">
				<a href="<%=path %>/usuario/list"><div class="bg-dark">Usuários</div></a>
			</div>
		</div>
		<div class="row bloco">
			<div class="col-4">
				<a href="<%=path %>/estoque/list"><div class="bg-dark">Estoque</div></a>
			</div>
			<div class="col-4">
				<a href="<%=path %>/estoque/movi"><div class="bg-dark">Movimentação</div></a>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>
</html>