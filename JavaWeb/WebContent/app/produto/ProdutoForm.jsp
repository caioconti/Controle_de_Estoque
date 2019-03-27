<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Pacifico|Roboto+Slab:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<title>Insert title here</title>
	
	<style>
		body {
			background: #f1f1f1;
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
	    	<a class="navbar-link text-white btn btn-outline-primary" href="ProdutoList.jsp">Voltar</a>
	    </nav>
    </header>

	<div class="container"><br>
		<div class="form-group text-white bg-dark"><br>
			<h3>
				<c:if test="${produto != null}">
	            	<p>Editar produto</p>
	            </c:if>
				<c:if test="${produto == null}">
	                <p>Adicionar produto</p>
	            </c:if>
	        </h3><br>
			<c:if test="${produto != null}">
				<form action="update" method="post">
			</c:if>
			<c:if test="${produto == null}">
				<form action="insert" method="post">
			</c:if>
				<c:if test="${produto != null}">
					<input type="hidden" name="id" value="<c:out value='${produto.id}' />" />
				</c:if>
				<div class="form-group text-white">
	                <label for="inputNome">Nome:</label>
	                <input type="text" name="nome" class="form-control" id="inputNome" placeholder="Nome" value="<c:out value='${produto.nome}' />" required autofocus />
	            </div>
				<div class="form-group text-white">
	                <label for="inputPreco">Preço(R$): </label>
	                <input type="number" step="any" min="0" name="preco" class="form-control" id="inputPreco" placeholder="Preço" value="<c:out value='${produto.valor}' />" required />
	            </div>
				<div class="form-group text-white">
	                <label for="inputQuantidade">Quantidade: </label>
	                <input type="number" name="quantidade" class="form-control" id="inputQuantidade" placeholder="Quantidade" value="<c:out value='${produto.quantidade}' />" required />
	            </div>
	            <div class="form-group text-white">
	                <label for="inputDescricao">Descrição: </label>
	                <input type="text" name="descricao" class="form-control" id="inputDescricao" placeholder="Descrição" value="<c:out value='${produto.descricao}' />" required />
	            </div>
	            <div class="form-group text-white">
	                <label for="inputFornecedor">Fornecedor: </label>
	                <input type="text" name="fornecedor" class="form-control" id="inputFornecedor" placeholder="Fornecedor" value="<c:out value='${produto.fornecedor}' />" required />
	            </div>
				<div class="form-group botoes text-white">
	            	<button class="btn btn-success" type="submit">Inserir</button>
	                <a class="btn btn-danger float-right" href='list' role="button">Cancelar</a>
	            </div>
			</form>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
</body>
</html>