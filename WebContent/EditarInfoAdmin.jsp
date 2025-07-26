<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminNavbar.jsp"%>
<%@ page import="entidades.Administrador" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Información Personal</title>
    <!-- Incluye Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent;">
    <div class="row justify-content-center">
        <div class="col-6">
            <div class="d-flex flex-column align-items-start mb-2">
                <a href="ServletInfoAdministrador">Volver atrás</a>
                <a href="#">Cambiar contraseña</a>
            </div>
            <div class="p-4 mb-3 bg-dark text-white rounded">
                <h3 class="text-center">Editar Información Personal</h3>

                <!-- Mostrar mensajes de éxito o error -->
                <%
                    String mensajeExito = (String) request.getAttribute("mensajeExito");
                    String mensajeError = (String) request.getAttribute("mensajeError");
                    Administrador administrador = (Administrador) request.getAttribute("administrador");

                    if (mensajeExito != null) {
                %>
                    <div class="alert alert-success text-center" role="alert">
                        <%= mensajeExito %>
                    </div>
                <%
                    }
                    if (mensajeError != null) {
                %>
                    <div class="alert alert-danger text-center" role="alert">
                        <%= mensajeError %>
                    </div>
                <%
                    }
                %>

                <form method="post" action="${pageContext.request.contextPath}/ServletEditarInfoAdmin">
                    <div class="form-group mb-3">
                        <label for="dni">DNI:</label>
                        <input type="text" class="form-control" id="dni" name="dni" value="<%= administrador != null ? administrador.getDni() : "" %>" required>
                    </div>

                    <div class="form-group mb-3">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" value="<%= administrador != null ? administrador.getNombre() : "" %>" required>
                    </div>

                    <div class="form-group mb-3">
                        <label for="apellido">Apellido:</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" value="<%= administrador != null ? administrador.getApellido() : "" %>" required>
                    </div>

                    <div class="btn-container">
                        <button type="submit" name="btnAceptar" class="btn btn-warning w-100 mt-2">Actualizar</button>
                        <a class="btn btn-secondary w-100 mt-2" href="${pageContext.request.contextPath}/ServletInfoAdministrador">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- JavaScript de Bootstrap 5 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
 
</body>
</html>