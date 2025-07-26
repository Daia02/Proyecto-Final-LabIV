<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Administrador" %>
<%@ include file="adminNavbar.jsp"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Información del Administrador</title>
    <!-- Incluye Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Mostrar mensaje de resultado -->
	<div class="container mt-3">
		<%
			if (session.getAttribute("mensaje") != null) {
		%>
		<div class="alert alert-success" role="alert">
			<%=session.getAttribute("mensaje")%>
		</div>
		<%
			session.removeAttribute("mensaje");
			}
		%>
		<%
			if (session.getAttribute("mensajeError") != null) {
		%>
		<div class="alert alert-danger" role="alert">
			<%=session.getAttribute("mensajeError")%>
		</div>
		<%
			session.removeAttribute("mensajeError");
			}
		%>
	</div>

	<div class="container-fluid p-3 mb-2 text-dark"
		style="background-color: transparent;">
		<div class="row justify-content-center">
			<div class="col-6">
				<div class="d-flex flex-column align-items-start mb-2">
					<a href="<%= request.getContextPath() %>/ServletEditarInfoAdmin">Editar
						datos de contacto</a> <a href="CambiarContraAdmin.jsp">Cambiar
						contraseña</a>
				</div>
				<div class="p-4 mb-3 bg-dark text-white rounded">
					<h3 class="text-center">Información del Administrador</h3>

					<%
                Administrador administrador = (Administrador) request.getAttribute("Administrador");
                if (administrador == null) {
            %>
					<div class="alert alert-danger text-center" role="alert">No
						se pudo cargar la información del usuario.</div>
					<%
                } else {
            %>

					<form>
						<div class="form-group mb-3">
							<label for="cuil">ID usuario:</label> <input type="text"
								class="form-control" id="cuil" readonly
								value="<%=administrador.getIdUsuario()%>">
						</div>
						<div class="form-group mb-3">
							<label for="cuil">ID usuario:</label> <input type="text"
								class="form-control" id="cuil" readonly
								value="<%=administrador.getAd_Usuario()%>">
						</div>

						<div class="form-group mb-3">
							<label for="dni">DNI:</label> <input type="text"
								class="form-control" id="dni" readonly
								value="<%=administrador.getDni()%>">
						</div>

						<div class="form-group mb-3">
							<label for="nombre">Nombre:</label> <input type="text"
								class="form-control" id="nombre" readonly
								value="<%=administrador.getNombre()%>">
						</div>

						<div class="form-group mb-3">
							<label for="apellido">Apellido:</label> <input type="text"
								class="form-control" id="apellido" readonly
								value="<%=administrador.getApellido()%>">
						</div>

					</form>

					<%
                }
            %>
				</div>
			</div>

		</div>
	</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
 
</body>
</html>