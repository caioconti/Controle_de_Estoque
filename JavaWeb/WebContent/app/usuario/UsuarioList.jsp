<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<title>Usuários</title>
	<link rel="stylesheet" href="../../style.css">
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
                    <a class="nav-link text-white btn btn-primary botao" href="<%=path%>/usuario/new">Incluir Usuario</a>
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
            <h3 class="texto-centro text-white">Usuários</h3><br>
            <!--<a class="btn btn-primary" href="<%=path%>/usuario/list" role="button">Listar usuários</a>-->
			<table class='table table-hover table-dark table-bordered'>
				<thead>
					<tr>
						<th scope='col'>ID</th>
						<th scope='col'>Nome</th>
						<th scope='col'>Telefone</th>
						<th scope='col'>Email</th>
						<th scope='col'>Login</th>
						<th scope='col'>Senha</th>
						<th scope='col' colspan="2" class='texto-centro'>Opções</th>
					</tr>
				</thead>
				
				
				<c:forEach var="usuario" items="${listUsuario}">
					<tr>
						<td scope='row' style="width:10px"><c:out value="${usuario.id}" /></td>
						<td scope='row' style="width:200px"><c:out value="${usuario.nome}" /></td>
						<td scope='row' style="width:130px"><c:out value="${usuario.telefone}" /></td>
						<td scope='row' style="width:200px"><c:out value="${usuario.email}" /></td>
						<td scope='row' style="width:100px"><c:out value="${usuario.login}" /></td>
						<td scope='row' style="width:100px"><c:out value="${usuario.senha}" /></td>
						<td scope='row' style="width:30px" align="center" class='texto-centro'><a href="../usuario/edit?id=<c:out value='${usuario.id}' />"><i class='material-icons' style='color: grey'>create</i></a></td>
						<td scope='row' style="width:30px" align="center" class='texto-centro'><a href="../usuario/delete?id=<c:out value='${usuario.id}' />" data-confirm='Tem certeza que deseja excluir este item?'><i class='material-icons' style='color: red;'>remove_circle_outline</i></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script>
	    $(document).ready(function(){
	    	$('a[data-confirm]').click(function(ev){
	        	var href = $(this).attr('href');
	        	if(!$('#confirm-delete').length){
	            	$('body').append('<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true"><div class="modal-dialog modal-dialog-centered" role="document"><div class="modal-content"><div class="modal-header bg-danger text-white">Excluir Item<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button></div><div class="modal-body">Tem certeza que deseja excluir este item?</div><div class="modal-footer"><button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button><a class="bg-success btn text-white" id="dataConfirmOK">Excluir</a></div></div></div></div>');
	        	}
	        	$('#dataConfirmOK').attr('href', href);
	        	$('#confirm-delete').modal({shown:true});
	        return false;
	    	});
		});
    </script>
</body>
</html>