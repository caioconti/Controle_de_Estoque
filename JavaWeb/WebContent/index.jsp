<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<title>CCSEstoque - Controle de Estoque</title>
	<style>
		* {
			margin: 0;
			padding: 0;
		}
		
		body {
			background-color: #9777da;
		}
		
		.box-logo {
			width: 300px;
			height: auto;
			padding: 20px;
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			border: 1px solid #000000;
		}
		
		.texto-branco {
			color: #FFFFFF;
		}
		
		.botao-login {
			margin-top: 10px;
			margin-left: 95px;
			margin-right: 95px;
		}
		
		.bemvindo {
			text-transform: uppercase;
			font-size: 20px;
			text-align: center;
		}
	</style>
</head>
<%
	String path = this.getServletContext().getContextPath();
%>
<body>
	<div class="box-logo bg-dark">
		<div class="bemvindo">
			<p class="texto-branco">Bem-Vindo</p>
		</div>
		<form action="usuario/aut" method="POST" >
			<div class="form-group">
		    	<label class="texto-branco" for="Login">Login: </label>
		    	<input type="text" name="login" class="form-control" id="Login" placeholder="Login">
		  	</div>
		  	<div class="form-group">
		   		<label class="texto-branco" for="Senha">Senha: </label>
		    	<input type="password" name="senha" class="form-control" id="Senha" placeholder="Senha">
		  	</div>
		  	<button type="submit" class="btn btn-success botao-login">Entrar</button>
		</form>
	</div>
</body>
</html>