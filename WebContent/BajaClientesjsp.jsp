<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidades.Cliente"%>
<%@ include file="adminNavbar.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmar baja de cliente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="bg-light">
    
    <!-- Mostrar mensaje de resultado -->
    <div class="container mt-3">
        <% if (request.getAttribute("mensaje") != null) { %>
            <div class="alert alert-success" role="alert">
                <%= request.getAttribute("mensaje") %>
            </div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
    </div>

    <!-- Volver a la tabla de clientes -->
    <div class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent;">
        <div class="row justify-content-center">
        </div>
        <div class="row justify-content-center">
            <div class="col-6 p-4 mb-3 bg-dark text-white rounded">
                <h3 class="text-center">Confirmar Baja de Cliente</h3>
                <form action="ServletBajaClientes" method="post">
                    <input type="hidden" name="cuil" value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getCuilCl() : "" %>">
                    <div class="form-group mb-3">
                        <label for="cuil">CUIL:</label>
                        <input type="text" class="form-control" id="cuil" name="txtcuil" value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getCuilCl() : "" %>" readonly>
                    </div>
                    <div class="form-group mb-3">
                        <label for="idusuario">ID USUARIO:</label>
                        <input type="text" class="form-control" id="idusuario" name="txtidusuario" value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getIdUsuario() : "" %>" readonly>
                    </div>
                    <div class="form-group mb-3">
                        <label for="dni">DNI:</label>
                        <input type="number" class="form-control" id="dni" name="txtdni" value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getDni() : "" %>" readonly>
                    </div>
                    <div class="form-group mb-3">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="txtnombre" value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getNombre() : "" %>" readonly>
                    </div>
                    <div class="form-group mb-3">
                        <label for="apellido">Apellido:</label>
                        <input type="text" class="form-control" id="apellido" name="txtapellido" value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getApellido() : "" %>" readonly>
                    </div>
                    <div class="btn-container"> 
                    <button type="submit" class="btn btn-danger w-100 mt-2">Confirmar Baja</button>
                    <a href="ServletListarClientes" class="btn btn-secondary w-100 mt-2">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
 
</body>
</html>