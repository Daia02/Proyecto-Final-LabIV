<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ include file="adminNavbar.jsp"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cambiar contraseña</title>
    <!-- Incluye Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-error {
            color: red;
            font-size: 0.875em;
        }
    </style>
</head>
<body class="bg-light">

<div class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent;">
    <div class="row justify-content-center">
        <div class="col-6">
            <div class="d-flex flex-column align-items-start mb-2">
                <a href="${pageContext.request.contextPath}/ServletInfoAdministrador">Volver atrás</a>
            </div>
            <div class="p-4 mb-3 bg-dark text-white rounded">
                <h3 class="text-center">Cambiar contraseña</h3>

                <!-- Mostrar mensaje de error si existe -->
                <%
                    if (request.getAttribute("mensajeError") != null) {
                %>
                <div class="alert alert-danger text-center" role="alert">
                    <%= request.getAttribute("mensajeError") %>
                </div>
                <%
                    }
                %>

                <form method="post" action="${pageContext.request.contextPath}/ServletCambiarContraAdmin">
                    
                    <div class="form-group mb-3">
                        <label for="contraActual">Contraseña actual:</label>
                        <input type="password" class="form-control" id="contraActual" name="contraActual" required>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="nuevaContra">Nueva contraseña:</label>
                        <input type="password" class="form-control" id="nuevaContra" name="nuevaContra" required minlength="8">
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="confirmNuevaContra">Confirmar nueva contraseña:</label>
                        <input type="password" class="form-control" id="confirmNuevaContra" name="confirmNuevaContra" required minlength="8">
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