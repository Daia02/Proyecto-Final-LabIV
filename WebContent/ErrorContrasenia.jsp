<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    
    <title>ERROR</title>
</head>
<body class="bg-light">

<!-- PARTE SUPERIOR -->
<div class="container-fluid p-3 mb-2 bg-dark text-light">
    <div class="row">
        <div class="col-6">
            <nav class="navbar navbar-expand-lg navbar-dark ">
                <a href="Home.jsp" class="navbar-brand">
                    <img src="Imagenes/logo_utn_blanco.png" class="img-fluid" style="max-width: 22px; vertical-align: middle;" alt="logo utn">
                </a>
                
                
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

             
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="clientesDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Clientes
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="clientesDropdown">
                                <li><a class="dropdown-item" href="Clientes.jsp">Tabla de Clientes</a></li>
                                <li><a class="dropdown-item" href="#">Alta de Clientes</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>

<form method="post" action="servletError">
<div class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent;">
    <div class="row justify-content-center">
    	<div class="col-4 p-4 mb-3 bg-dark text-white rounded">
			<div class="alert alert-danger" role="alert">
				Las contraseñas no son iguales.
			</div>
			<input name="btnVolver" value="Volver" type="submit" class="btn btn-primary w-100">
    	</div>
    </div>
</div>
</form>
<!-- JavaScript de Bootstrap 5 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>