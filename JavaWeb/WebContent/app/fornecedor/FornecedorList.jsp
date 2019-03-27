<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<title>Insert title here</title>
	<style>
		body {
			background: #9777da;
		}
		.texto-centro {
			text-align: center;
		}
	</style>
</head>
<%
	String path = this.getServletContext().getContextPath();
%>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-dark">
            <a class="navbar-brand text-white" href="../home.jsp">Home</a>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
                <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link text-white btn btn-primary" href="<%=path%>/fornecedor/new">Incluir Fornecedor</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white btn btn-danger" href="#">Sair</a>
                </li>
                </ul>
            </div>
        </nav>
    </header>
    <div class="container-fluid"><br>
        <div class="modelo-divs bg-dark"><br>
            <h3 class="texto-centro text-white">Fornecedor</h3><br>
			<table class='table table-hover table-dark table-bordered'>
				<thead>
					<tr>
						<th scope='col'>ID</th>
						<th scope='col'>Nome</th>
						<th scope='col'>Telefone</th>
						<th scope='col'>Email</th>
						<th scope='col'>CNPJ</th>
						<th scope='col'>Cidade</th>
						<th scope='col' colspan="2" class='texto-centro'>Opções</th>
					</tr>
				</thead>
				<c:forEach var="fornecedor" items="${listFornecedor}">
					<tr>
						<td scope='row'><c:out value="${fornecedor.id}" /></td>
						<td scope='row'><c:out value="${fornecedor.nome}" /></td>
						<td scope='row'><c:out value="${fornecedor.telefone}" /></td>
						<td scope='row'><c:out value="${fornecedor.email}" /></td>
						<td scope='row'><c:out value="${fornecedor.cnpj}" /></td>
						<td scope='row'><c:out value="${fornecedor.cidade}" /></td>
						<td scope='row' align="center" class='texto-centro'><a href="../fornecedor/edit?id=<c:out value='${fornecedor.id}' />">Editar</a></td>
						<td scope='row' align="center" class='texto-centro'><a href="../fornecedor/delete?id=<c:out value='${fornecedor.id}' />">Deletar</a></td>
					</tr>
				</c:forEach>
			</table>
		</div><br>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
</body>
</html>