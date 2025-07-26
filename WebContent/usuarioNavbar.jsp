<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Usuario" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
   
</head>
<body class="bg-light">

<!-- PARTE SUPERIOR -->
<div class="container-fluid p-3 mb-2 bg-dark text-light">
    <div class="row">
        <div class="col-12">
            <nav class="navbar navbar-expand-lg navbar-dark">
                <a href="usuarioHome.jsp" class="navbar-brand">
                    <img src="Imagenes/logo_utn_blanco.png" class="img-fluid" style="max-width: 22px; vertical-align: middle;" alt="logo utn">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="clientesDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Prestamos
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="clientesDropdown">
                                <li><a class="dropdown-item" href="ServletClientePrestamos">Solicitar prestamos</a></li>
                                <li><a class="dropdown-item" href="ServletPagoPrestamos">Gestionar prestamos</a></li>
                                <li><a class="dropdown-item"  href="ServletEstadoPrestamos">Estado Prestamos</a>
                            </ul>
                        </li>
                    </ul>
                    <ul class="navbar-nav ms-auto">
                        <%
                            request.getSession(false);
                            if (session != null) {
                                Usuario usuario = (Usuario) session.getAttribute("usuario");
                                if (usuario != null) {
                                    String tipoUsuario = "Usuario";
                        %>
                        <li class="nav-item">
                            <a href="ServletInfoUsuario" class="nav-link"><%= tipoUsuario %>: <%= usuario.getNombre() %></a>
                        </li>
                        <%
                                }
                            }
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="InicioSesion.jsp">Cerrar sesión</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>



<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>