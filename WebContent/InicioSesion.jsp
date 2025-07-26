<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es"> 
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="with=device-with, initial-scale=1, shrink-to-fit=no">
    
    <title>Iniciar sesion</title>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
    crossorigin="anonymous">
    
    <style type="text/css">
    body {
    background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url(Imagenes/fondo_utn.jpg);
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    background-attachment: fixed;
	}
	</style>
    
</head>
<body>

<!-- PARTE SUPERIOR --> 
<div class="container-fluid p-3 mb-2 bg-dark text-white">
	<div class="row">
		<div class="col-6">
			<a href="InicioSesion.jsp"> <h3 class="text-left font-weight-bold mb-0 d-flex align-items-center">
				<img src="Imagenes/logo_utn_blanco.png" class="img-fluid" style="max-width: 22px; margin-right: 10px; vertical-align: middle;" alt="logo utn">
				UTN HOME BANKING
			</h3> </a>
		</div>
	</div>
</div>

<!-- CUERPO -->

<div class="container-fluid p-3 mb-2 backround: transparent text-dark">
    <div class="row justify-content-center">
        <div class="col-4 p-4 mb-3 bg-dark text-white rounded">
          
            <h3 class="text-center">Iniciar sesión</h3>

				
				<form method="POST" action="ServletUsuario">
				
				
				<%
					String errorMsg = (String) request.getAttribute("errorMsg");
				
				%>

				<%
					if (errorMsg != null) {
				%>
				<div class="alert alert-danger" role="alert">
					<%=errorMsg%>
				</div>
				<% } %>
				
                <div class="form-group">
                    <label for="username">Usuario:</label>
                    <input required type="text" class="form-control" name="name" id="username" placeholder="Ingrese su usuario">
                </div>
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input required type="password" class="form-control" name="password" id="password" placeholder="Ingrese su contraseña">
                </div>
                <button type="submit" name="btnIngresar" class="btn btn-primary w-100">Ingresar</button>
            </form>
            
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
