<%@page import="usf.model.usuario.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<title>CCSEstoque - Usuário</title>
	<style>
		body {
			background: #9777da;
		}
		
		h3 {
			text-align: center;
		}
		
		.form-group {
			padding: 0 10px;
		}
		
		.form {
			padding-bottom: 10px;
		}
		
		.botoes {
			padding-top: 10px;
			padding-bottom: 20px;
		}
		
		.btn-cancelar {
			color: white;
		}
	</style>
</head>
<%
	String path = this.getServletContext().getContextPath();

	HttpSession usuario =  request.getSession();
	Usuario login = (Usuario) usuario.getAttribute("usuario");
	if(login == null) {
		response.sendRedirect("../../index.jsp");
	}

%>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-dark">
			<a class="navbar-link text-white btn btn-outline-primary"
				href="../usuario/list">Voltar</a>
		</nav>
	</header>
	<div class="container">
		<br>
		<div class="form-group text-white bg-dark">
			<br>
			<h3>
				<c:if test="${usuario != null}">
					<p>Editar usuário</p>
				</c:if>
				<c:if test="${usuario == null}">
					<p>Adicionar usuário</p>
				</c:if>
			</h3>
			<br>
			<c:if test="${usuario != null}">
				<form action="<%=path%>/usuario/update" method="post">
			</c:if>
			<c:if test="${usuario == null}">
				<form action="<%=path%>/usuario/insert" method="post">
			</c:if>
			<c:if test="${usuario != null}">
				<input type="hidden" name="id"
					value="<c:out value='${usuario.id}' />" />
			</c:if>
			<div class="form-group text-white">
				<label for="inputNome">Nome:</label> <input type="text" name="nome"
					class="form-control" id="inputNome" placeholder="Nome"
					value="<c:out value='${usuario.nome}' />" required autofocus />
			</div>
			<div class="form-group text-white">
				<label for="inputTelefone">Telefone:</label> <input type="text"
					name="telefone" class="form-control" id="inputTelefone"
					placeholder="Telefone"
					value="<c:out value='${usuario.telefone}' />" required />
			</div>
			<div class="form-group text-white">
				<label for="inputEmail">Email:</label> <input type="email"
					name="email" class="form-control" id="inputEmail"
					placeholder="Email" value="<c:out value='${usuario.email}' />"
					required />
			</div>
			<div class="form-group text-white">
				<label for="inputLogin">Login:</label> <input type="text"
					name="login" class="form-control" id="inputLogin"
					placeholder="Login" value="<c:out value='${usuario.login}' />"
					required />
			</div>
			<div class="form-group text-white">
				<label for="inputSenha">Senha:</label> <input type="password"
					name="senha" class="form-control" id="inputSenha"
					placeholder="Senha" value="<c:out value='${usuario.senha}' />"
					required />
			</div>
			<div class="form-group botoes text-white">
				<button class="btn btn-success" type="submit">Inserir</button>
				<a class="btn btn-danger float-right" href='list'>Cancelar</a>
			</div>
			</form>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>
</html>