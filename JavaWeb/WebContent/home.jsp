<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<title>Insert title here</title>
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
	</style>
</head>
<%
	String path = this.getServletContext().getContextPath();
%>
<body>
	<header>
         <nav class="navbar navbar-expand-lg bg-dark">
             <a class="navbar-brand text-white" href="home.jsp">Home</a>
             <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                 <span class="navbar-toggler-icon"></span>
             </button>
             <div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
                 <ul class="navbar-nav">
                 <li class="nav-item">
                     <a class="nav-link text-white btn btn-danger" href="sair.php">Sair</a>
                 </li>
                 </ul>
             </div>
         </nav>
     </header>
     <div class="container"><br>
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
                 <a href="#"><div class="bg-dark">#</div></a>
             </div>
             <div class="col-4">
                 <a href="#"><div class="bg-dark">#</div></a>
         	</div>
         </div>   
     </div>
    
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        
</body>
</html>