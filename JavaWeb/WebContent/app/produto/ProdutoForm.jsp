<%@page import="usf.model.basic.ModelBasic"%>
<%@page import="usf.model.fornecedor.Fornecedor"%>
<%@page import="java.util.List"%>
<%@page import="usf.model.fornecedor.FornecedorDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Pacifico|Roboto+Slab:400,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<title>Insert title here</title>
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
%>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-dark">
			<a class="navbar-link text-white btn btn-outline-primary"
				href="../produto/list">Voltar</a>
		</nav>
	</header>

	<div class="container">
		<br>
		<div class="form-group text-white bg-dark">
			<br>
			<h3>
				<c:if test="${produto != null}">
					<p>Editar produto</p>
				</c:if>
				<c:if test="${produto == null}">
					<p>Adicionar produto</p>
				</c:if>
			</h3>
			<br>
			<c:if test="${produto != null}">
				<form action="<%=path%>/produto/update" method="post">
			</c:if>
			
			<c:if test="${produto == null}">
				<form action="<%=path%>/produto/insert" method="post">
			</c:if>
			
				<c:if test="${produto != null}">
					<input type="hidden" name="id" value="<c:out value='${produto.id}' />" />
				</c:if>
				
				<div class="form-group text-white">
					<label for="inputNome">Nome:</label> 
					<input type="text" name="nome" class="form-control" id="inputNome" placeholder="Nome" value="<c:out value='${produto.nome}' />" required autofocus />
				</div>
				
				<div class="form-group text-white">
					<label for="inputCompra">Valor de Compra: </label> 
					<input type="number" step="any" min="0" name="valorCompra" class="form-control" id="inputCompra" placeholder="Valor unitário de compra" value="<c:out value='${produto.valorCompra}' />" required />
				</div>
				
				<div class="form-group text-white">
					<label for="inputVenda">Valor de Venda: </label> 
					<input type="number" step="any" min="0" name="valorVenda" class="form-control" id="inputVenda" placeholder="Valor unitário de venda" value="<c:out value='${produto.valorVenda}' />" required />
				</div>
				
				<div class="form-group text-white">
					<label for="inputDescricao">Descrição: </label> 
					<input type="text" name="descricao" class="form-control" id="inputDescricao" placeholder="Descrição" value="<c:out value='${produto.descricao}' />" required />
				</div>
				
				<div class="form-group text-white">
					<label for="fornecedores">Fornecedor: </label>
					<select class="form-control" name="fornecedor">
						<c:if test="${produto != null}">
							<option><c:out value='${produto.fornecedor}' /></option>
						</c:if>
						<c:if test="${produto == null}">
							<option value="0">SELECIONE O FORNECEDOR</option>
						</c:if>
						<%
							String jdbcURL = getServletContext().getInitParameter("jdbcURL");
							String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
							String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
							String jdbcDriver = getServletContext().getInitParameter("jdbcDriver");
		
							FornecedorDAO fornecedorDAO = new FornecedorDAO(jdbcURL, jdbcUsername, jdbcPassword, jdbcDriver);
							List<ModelBasic> listFornecedor = fornecedorDAO.listAll();
							for (int i = 0; i < listFornecedor.size(); i++) {
								String nome = ((Fornecedor)listFornecedor.get(i)).getNome();
						%>	
							<option value="<%=nome%>"><%=nome%></option>
						<%
							}
							
							request.getAttribute("");
						%>
					</select>
				</div>
				
				<div class="form-group botoes text-white">
					<button class="btn btn-success" type="submit">Inserir</button>
					<a class="btn btn-danger float-right" href='list' role="button">Cancelar</a>
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