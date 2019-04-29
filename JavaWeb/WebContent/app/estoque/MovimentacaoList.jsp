<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<title>CCS - Movimentação </title>
	<style>
		body {
			background: #9777da;
		}
		
		.texto-centro {
			text-align: center;
		}
		
		.botao {
			margin-right: 10px;
		}
		
		.modelo-divs {
			padding: 0 20px;
			overflow: hidden;
		}
		
		.procurar {
			float: right;
			padding: 10px 0;
		}
		
		.campo-busca {
			padding: 4px 0;
		}
		
		.botao-busca {
			margin-bottom: 4px;
		}
	</style>
</head>
<%
	String path = this.getServletContext().getContextPath();
%>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="collapse navbar-collapse justify-content-end">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link text-white btn btn-primary botao" href="<%=path%>/entradaproduto/new">Incluir Entrada</a>
					</li>
					<li>
						<a class="nav-link text-white btn btn-primary botao" href="<%=path%>/estoque/new">Incluir Saída</a>
					</li>
					<li class="nav-item">
						<a class="nav-link text-white btn btn-danger" href="../home.jsp">Voltar</a>
					</li>
				</ul>
			</div>
		</nav>
	</header>
	<div class="container-fluid">
		<br>
		<div class="modelo-divs bg-dark">
			<br>
			<h3 class="texto-centro text-white">Movimentação</h3>
			<div class="procurar">
				<form action="<%=path%>/estoque/search" method="POST">
					<input class="campo-busca" type="text" name="procurar" placeholder="Buscar item..." />
					<button class="botao-busca text-white btn btn-primary" type="submit">Procurar</button>
				</form>
			</div>
			<table class='table table-hover table-dark table-bordered'>
				<thead>
					<tr>
						<th scope='col' style="text-align: center">Tipo</th>
						<th scope='col' style="text-align: center">Data</th>
						<th scope='col' style="text-align: center">Item</th>
						<th scope='col' style="text-align: center">Valor UN</th>
						<th scope='col' style="text-align: center">Qntd</th>
						<th scope='col' style="text-align: center">Valor Total</th>
						<th scope='col' style="text-align: center">Usuário</th>
						
					</tr>
				</thead>
				<c:forEach var="list" items="${list}">
					<tr>
						<td scope='row' style="width: 70px; text-align: center">
							<c:out value="${list.tipo}" />
						</td>
						<td scope='row' style="width: 70px; text-align: center">
							<c:out value="${list.data}" />
						</td>
						<td scope='row' style="width: 150px; text-align: center">
							<c:out value="${list.produto}" />
						</td>
						<td scope='row' style="width: 100px; text-align: center">
							R$ <c:out value="${list.valorUnitario}" />
						</td>
						<td scope='row' style="width: 50px; text-align: center">
							<c:out value="${list.quantidade}" /> UN
						</td>
						<td scope='row' style="width: 100px; text-align: center">
							R$ <c:out value="${list.valorTotal}" />
						</td>
						<td scope='row' style="width: 150px; text-align: center">
							<c:out value="${list.usuario}" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
</body>
</html>