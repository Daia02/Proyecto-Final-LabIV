<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Nacionalidad"%>
<%@ page import="entidades.Provincia"%>
<%@ page import="entidades.Localidad"%>
<%@ include file="adminNavbar.jsp"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css"
	rel="stylesheet">

<title>Clientes</title>


</head>
<body class="bg-light">


	<div class="container-fluid p-3 mb-2 text-dark"
		style="background-color: transparent;">
		<div class="row justify-content-center">
			<div class="col-4 p-4 mb-3 bg-dark text-white rounded">
				<h3 class="text-center">Alta clientes</h3>

				<%-- Mensaje de resultado --%>
				<%
					String mensaje = (String) request.getAttribute("mensaje");
					if (mensaje != null) {
				%>
				<div class="alert alert-info text-center" role="alert">
					<%=mensaje%>
				</div>
				<%
					}
				%>

				<form action="ServletAltaCliente" method="post">

					<div class="form-group mb-3">
						<label for="cuil">CUIL:</label> <input type="number"
							class="form-control" id="cuil" name="txtcuil"
							placeholder="Ingrese su CUIL (XX-XXXXXXXX-X)" maxlength="13"
							required>
					</div>

					<div class="form-group mb-3">
						<label for="idusuario">ID USUARIO:</label> <input type="text"
							class="form-control" id="idusuario" name="txtidusuario"
							placeholder="Ingrese el id usuario" maxlength="20" required>
					</div>

					<div class="form-group mb-3">
						<label for="dni">DNI:</label> <input required type="number"
							class="form-control" id="dni" name="txtdni"
							placeholder="Ingrese su DNI (XXXXXXXX)" maxlength="8">
					</div>

					<div class="form-group mb-3">
						<label for="nombre">Nombre:</label> <input required type="text"
							class="form-control" name="txtnombre" id="nombre"
							placeholder="Ingrese su nombre">
					</div>

					<div class="form-group mb-3">
						<label for="apellido">Apellido:</label> <input required
							type="text" class="form-control" id="apellido" name="txtapellido"
							placeholder="Ingrese su apellido">
					</div>

					<!-- Radio buttons para selección de sexo -->
					<div class="form-group mb-3">
						<label for="sexo">Sexo: </label> <br>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="rd-sexo"
								id="masculino" value="Masculino" required> <label
								class="form-check-label" for="masculino">Masculino</label>
						</div>

						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="rd-sexo"
								id="femenino" value="Femenino" required> <label
								class="form-check-label" for="femenino">Femenino</label>
						</div>

						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="rd-sexo"
								id="otro" value="otro" required> <label
								class="form-check-label" for="otro">Otro...</label>
						</div>
					</div>

					<div class="form-group mb-3">
						<label for="nacionalidad">Nacionalidad: </label> <select required
							id="nacionalidad" class="form-control" name="selectNacionalidad">
							<option value="0" disabled selected>Seleccione su
								nacionalidad:</option>
							<%
								List<Nacionalidad> nacionalidades = (List<Nacionalidad>) request.getAttribute("nacionalidades");
								if (nacionalidades != null) {
									for (Nacionalidad nac : nacionalidades) {
							%>
							<option value="<%=nac.getNroNacionalidad()%>"><%=nac.getNroNacionalidad()%>
								-
								<%=nac.getNacionalidad()%></option>
							<%
								}
								} else {
							%>
							<option value="0" disabled>No hay nacionalidades
								disponibles</option>
							<%
								}
							%>
						</select>
					</div>

					<div class="form-group mb-3">
						<label for="provincia">Provincia:</label> <select required
							id="provincia" class="form-control" name="selectProvincia">
							<option value="0" disabled selected>Seleccione su
								provincia:</option>
							<%
								List<Provincia> provincias = (List<Provincia>) request.getAttribute("provincias");
								if (provincias != null) {
									for (Provincia prov : provincias) {
							%>
							<option value="<%=prov.getNroProvincia()%>"><%=prov.getNroProvincia()%>
								-
								<%=prov.getProvincia()%></option>
							<%
								}
								} else {
							%>
							<option value="0" disabled>No hay provincias disponibles</option>
							<%
								}
							%>
						</select>
					</div>

					<div class="form-group mb-3">
						<label for="localidad">Localidad: </label> <select required
							id="localidad" class="form-control" name="selectLocalidad">
							<option value="0" disabled selected>Seleccione su
								localidad:</option>
							<%
								List<Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
								if (localidades != null) {
									for (Localidad loc : localidades) {
							%>
							<option value="<%=loc.getNroLocalidad()%>"><%=loc.getNroLocalidad()%>
								-
								<%=loc.getLocalidad()%></option>
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


					<div class="form-group mb-3">
						<label for="fechanac">Fecha de nacimiento: </label> <input
							required type="date" class="form-control" id="fechanac"
							name="txtFechaNac" placeholder="Ingrese su fecha de nacimiento">
					</div>

					<div class="form-group mb-3">
						<label for="direccion">Dirección: </label> <input required
							type="text" class="form-control" id="direccion"
							name="txtDirecccion" placeholder="Ingrese su dirección">
					</div>




					<div class="form-group mb-3">
						<label for="email">Correo electrónico:</label> <input required
							type="email" class="form-control" id="email" name="txtEmail"
							placeholder="Ingrese su correo">
					</div>

					<div class="form-group mb-3">
						<label for="telefono">Teléfono:</label> <input required type="tel"
							class="form-control" name="txtTelefono" id="telefono"
							placeholder="Ingrese su teléfono">
					</div>

					<button type="submit" name="btnAceptar"
						class="btn btn-primary w-100">Aceptar</button>
				</form>
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