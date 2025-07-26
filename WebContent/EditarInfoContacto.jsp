<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="entidades.Localidad"%>
<%@ page import="entidades.Provincia"%>
<%@ include file="usuarioNavbar.jsp"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar información</title>
    <!-- Incluye Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent;">
    <div class="row justify-content-center">
        <div class="col-6">
            <div class="d-flex flex-column align-items-start mb-2">
                <a href="ServletInfoUsuario">Volver atrás</a>
                <a href="#">Cambiar contraseña</a>
            </div>
            <div class="p-4 mb-3 bg-dark text-white rounded">
                <h3 class="text-center">Editar información de contacto</h3>

                <%
                    Map<String, Object> informacionUsuario = (Map<String, Object>) request.getAttribute("informacionUsuario");
                    if (informacionUsuario == null) {
                %>
                <div class="alert alert-danger text-center" role="alert">
                    No se pudo cargar la información del usuario.
                </div>
                <%
                    } else {
                %>

					<form method="post"
						action="${pageContext.request.contextPath}/ServletEditarInfoUsuario">
						<div class="form-group mb-3">
							<label for="direccion">Dirección:</label> <input type="text"
								class="form-control" id="direccion" name="direccion"
								value="<%=informacionUsuario.get("Dirección") != null ? informacionUsuario.get("Dirección") : ""%>"
								required>
						</div>

						<div class="form-group mb-3">
							<label for="correoElectronico">Correo Electrónico:</label> <input
								type="email" class="form-control" id="correoElectronico"
								name="correoElectronico"
								value="<%=informacionUsuario.get("Correo Electrónico") != null
						? informacionUsuario.get("Correo Electrónico")
						: ""%>"
								required>
						</div>

						<div class="form-group mb-3">
							<label for="telefono">Teléfono:</label> <input type="tel"
								class="form-control" id="telefono" name="telefono"
								value="<%=informacionUsuario.get("Teléfono") != null ? informacionUsuario.get("Teléfono") : ""%>"
								pattern="[0-9]{10}" required>
						</div>

						<div class="form-group mb-3">
							<label for="selectProvincia">Provincia:</label> <select
								id="selectProvincia" class="form-control" name="selectProvincia"
								required>
								<option value="0" disabled>Seleccione su provincia:</option>
								<%
									List<Provincia> provincias = (List<Provincia>) request.getAttribute("provincias");
										if (provincias != null) {
											for (Provincia prov : provincias) {
												String nroProvincia = informacionUsuario.get("NroProvincia") != null
														? informacionUsuario.get("NroProvincia").toString()
														: "";
								%>
								<option value="<%=prov.getNroProvincia()%>"
									<%=nroProvincia.equals(String.valueOf(prov.getNroProvincia())) ? "selected" : ""%>>
									<%=prov.getNroProvincia()%> -
									<%=prov.getProvincia()%>
								</option>
								<%
									}
										} else {
								%>
								<option value="0" disabled>No hay provincias
									disponibles</option>
								<%
									}
								%>
							</select>
						</div>

						<div class="form-group mb-3">
							<label for="selectLocalidad">Localidad:</label> <select
								id="selectLocalidad" class="form-control" name="selectLocalidad"
								required>
								<option value="0" disabled>Seleccione su localidad:</option>
								<%
									List<Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
										if (localidades != null) {
											for (Localidad loc : localidades) {
												String nroLocalidad = informacionUsuario.get("NroLocalidad") != null
														? informacionUsuario.get("NroLocalidad").toString()
														: "";
								%>
								<option value="<%=loc.getNroLocalidad()%>"
									<%=nroLocalidad.equals(String.valueOf(loc.getNroLocalidad())) ? "selected" : ""%>>
									<%=loc.getNroLocalidad()%> -
									<%=loc.getLocalidad()%>
								</option>
								<%
									}
										} else {
								%>
								<option value="0" disabled>No hay localidades
									disponibles</option>
								<%
									}
								%>
							</select>
						</div>

						<div class="btn-container">
							<button type="submit" name="btnAceptar"
								class="btn btn-warning w-100 mt-2">Actualizar</button>
							<a class="btn btn-secondary w-100 mt-2"
								href="${pageContext.request.contextPath}/ServletInfoUsuario">Cancelar</a>
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