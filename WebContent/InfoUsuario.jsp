<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ include file="usuarioNavbar.jsp"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Información del Usuario</title>
    <!-- Incluye Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

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
					<a href="<%= request.getContextPath() %>/ServletEditarInfoUsuario">Editar
						datos de contacto</a> 
						<a href="CambiarContra.jsp">Cambiar contraseña</a>
				</div>
				<div class="p-4 mb-3 bg-dark text-white rounded">
					<h3 class="text-center">Información del Usuario</h3>

					<%
                    Map<String, Object> informacionUsuario = (Map<String, Object>) request.getAttribute("informacionUsuario");
                    if (informacionUsuario == null) {
                %>
					<div class="alert alert-danger text-center" role="alert">No
						se pudo cargar la información del usuario.</div>
					<%
                    } else {
                %>

					<form>
						<div class="form-group mb-3">
							<label for="cuil">CUIL:</label> <input type="text"
								class="form-control" id="cuil" readonly
								value="<%=informacionUsuario.get("CUIL")%>">
						</div>

						<div class="form-group mb-3">
							<label for="dni">DNI:</label> <input type="text"
								class="form-control" id="dni" readonly
								value="<%=informacionUsuario.get("DNI")%>">
						</div>

						<div class="form-group mb-3">
							<label for="nombre">Nombre:</label> <input type="text"
								class="form-control" id="nombre" readonly
								value="<%=informacionUsuario.get("Nombre")%>">
						</div>

						<div class="form-group mb-3">
							<label for="apellido">Apellido:</label> <input type="text"
								class="form-control" id="apellido" readonly
								value="<%=informacionUsuario.get("Apellido")%>">
						</div>

						<div class="form-group mb-3">
							<label for="sexo">Sexo:</label> <input type="text"
								class="form-control" id="sexo" readonly
								value="<%=informacionUsuario.get("Sexo")%>">
						</div>

						<div class="form-group mb-3">
							<label for="fechaNacimiento">Fecha de Nacimiento:</label> <input
								type="text" class="form-control" id="fechaNacimiento" readonly
								value="<%=informacionUsuario.get("Fecha de Nacimiento")%>">
						</div>

						<div class="form-group mb-3">
							<label for="direccion">Dirección:</label> <input type="text"
								class="form-control" id="direccion" readonly
								value="<%=informacionUsuario.get("Dirección")%>">
						</div>

						<div class="form-group mb-3">
							<label for="correoElectronico">Correo Electrónico:</label> <input
								type="text" class="form-control" id="correoElectronico" readonly
								value="<%=informacionUsuario.get("Correo Electrónico")%>">
						</div>

						<div class="form-group mb-3">
							<label for="telefono">Teléfono:</label> <input type="text"
								class="form-control" id="telefono" readonly
								value="<%=informacionUsuario.get("Teléfono")%>">
						</div>

						<div class="form-group mb-3">
							<label for="nombreUsuario">Nombre de Usuario:</label> <input
								type="text" class="form-control" id="nombreUsuario" readonly
								value="<%=((Map<String, Object>) informacionUsuario.get("Usuario")).get("Nombre de Usuario")%>">
						</div>

						<div class="form-group mb-3">
							<label for="nombreLocalidad">Nombre de Localidad:</label> <input
								type="text" class="form-control" id="nombreLocalidad" readonly
								value="<%=((Map<String, Object>) informacionUsuario.get("Localidad")).get("Nombre de Localidad")%>">
						</div>

						<div class="form-group mb-3">
							<label for="nombreProvincia">Nombre de Provincia:</label> <input
								type="text" class="form-control" id="nombreProvincia" readonly
								value="<%=((Map<String, Object>) informacionUsuario.get("Provincia")).get("Nombre de Provincia")%>">
						</div>

						<div class="form-group mb-3">
							<label for="nacionalidad">Nacionalidad:</label> <input
								type="text" class="form-control" id="nacionalidad" readonly
								value="<%=((Map<String, Object>) informacionUsuario.get("Nacionalidad")).get("Nacionalidad")%>">
						</div>
					</form>

					<%
                    }
                %>
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
}