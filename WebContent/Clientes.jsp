<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Cliente" %>
<%@ page import="entidades.Nacionalidad" %>
<%@ page import="entidades.Provincia" %>
<%@ page import="entidades.Localidad" %>
<%@ include file="adminNavbar.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clientes</title>
    
    <!-- Incluye Bootstrap CSS primero -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">

    <style>
th, td {
	text-align: center;
	padding: 8px;
}

.btn-container {
	display: flex;
	justify-content: center;
	gap: 5px;
}

.btn-sm {
	width: 80px;
}

.table-container {
	margin: 20px;
}

.dataTables_wrapper .dataTables_paginate, .dataTables_wrapper .dataTables_info,
	.dataTables_wrapper .dataTables_length, .dataTables_wrapper .dataTables_filter
	{
	margin-top: 20px;
}

.dataTables_wrapper .dataTables_scroll {
	overflow-x: auto;
}

.dataTables_scrollBody {
	border-bottom: 1px solid #ddd;
}

.modal-body .form-group label {
	color: #000;
}

.modal-content {
	width: 100%;
}

.modal-header, .modal-footer {
	background-color: #343a40;
	color: #ffffff;
}

.btn-close {
	background: #ffffff;
}

.btn-close:before {
	content: '\00d7';
	color: #000;
}

.modal-footer {
	border-top: 1px solid #dee2e6;
}

.modal-body {
	padding-bottom: 0;
}

.form-group {
	margin-bottom: 15px;
}

/* Estilos personalizados para asegurar el funcionamiento del dropdown */
.dropdown-menu {
	display: none;
}

.show>.dropdown-menu {
	display: block;
}

/* Personaliza el backdrop del modal */
.modal-backdrop {
	background-color: rgba(0, 0, 0, 0.3) !important;
	/* Ajusta la opacidad según tu preferencia */
}
</style>
</head>



<body class="bg-light">

	<!-- Mostrar mensaje de resultado -->
	<div class="container mt-3">
		<%
			if (request.getAttribute("mensaje") != null) {
		%>
		<div class="alert alert-success" role="alert">
			<%=request.getAttribute("mensaje")%>
		</div>
		<%
			}
		%>
		<%
			if (request.getAttribute("error") != null) {
		%>
		<div class="alert alert-danger" role="alert">
			<%=request.getAttribute("error")%>
		</div>
		<% } %>
		</div>

		<div class="container mt-4 mb-4">
			<div class="table-container">
				<h2 class="text-center mb-4">Lista de Clientes</h2>
				<a href="ServletAltaCliente" class="text-left mb-4">Agregar
					clientes</a> <br /> <a href="#" class="text-left mb-4"
					data-bs-toggle="modal" data-bs-target="#modalFiltro">Filtrar
					clientes</a> <br />
				<div>
					<table id="tablaClientes"
						class="table table-bordered table-striped table-hover mb-4">
						<thead class="table-dark">
							<tr>
								<th>Cuil Cliente</th>
								<th>ID Usuario</th>
								<th>Nro Localidad</th>
								<th>Nro Provincia</th>
								<th>DNI</th>
								<th>Nombre</th>
								<th>Apellido</th>
								<th>Sexo</th>
								<th>Nacionalidad</th>
								<th>Fecha de Nacimiento</th>
								<th>Dirección</th>
								<th>Correo Electrónico</th>
								<th>Teléfonos</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<% 
                        // Obtener la lista de clientes desde el atributo "clientes" de la solicitud
                        List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                        
                        // Verificar si la lista de clientes no es nula
                        if (clientes != null && !clientes.isEmpty()) {
                            // Iterar sobre la lista de clientes
                            for (Cliente cliente : clientes) {
                    %>

							<tr>
								<td><%= cliente.getCuilCl() %></td>
								<td><%= cliente.getIdUsuario() %></td>
								<td><%= cliente.getNroLocalidad() %></td>
								<td><%= cliente.getNroProvincia() %></td>
								<td><%= cliente.getDni() %></td>
								<td><%= cliente.getNombre() %></td>
								<td><%= cliente.getApellido() %></td>
								<td><%= cliente.getSexo() %></td>
								<td><%= cliente.getNroNacionalidad() %></td>
								<td><%= cliente.getFechaNacimiento() %></td>
								<td><%= cliente.getDireccion() %></td>
								<td><%= cliente.getCorreoElectronico() %></td>
								<td><%= cliente.getTelefono() %></td>
								<td>


									<div class="btn-container">
										<form action="ServletModificarClientes" method="get">
											<input type="hidden" name="action" value="editCliente" /> <input
												type="hidden" name="cuil" value="<%=cliente.getCuilCl()%>" />
											<button type="submit" class="btn btn-sm btn-warning">Editar</button>
										</form>
										<form action="ServletBajaClientes" method="get">
											<input type="hidden" name="action" value="deleteCliente" />
											<input type="hidden" name="cuil"
												value="<%=cliente.getCuilCl()%>" />
											<button type="submit" class="btn btn-sm btn-danger">Eliminar</button>
										</form>
									</div>


								</td>
							</tr>
							<%
                    	}
                    	} else {
                    %>
							<tr>
								<td colspan="13" class="text-center">No hay clientes
									registrados.</td>
							</tr>
							<%  
                        }
                    %>
						</tbody>
					</table>
				</div>
			</div>
		</div>

			
		<!-- Controles de paginacion -->
		<div class="dataTables_wrapper">
			<div class="dataTables_length" id="tablaClientes_length"></div>
			<div class="dataTables_filter" id="tablaClientes_filter"></div>
			<div class="dataTables_info" id="tablaClientes_info"></div>
			<div class="dataTables_paginate paging_simple_numbers"
				id="tablaClientes_paginate"></div>
		</div>


		<!-- Modal filtrar -->
<form action="ServletListarClientes" method="post">
    <div id="modalFiltro" class="modal fade" tabindex="-1" aria-labelledby="modalFiltroLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-dark text-light">
                    <h5 class="modal-title" id="modalFiltroLabel">Filtrar clientes</h5>
                    <button type="button" class="btn-close text-light" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body bg-light">
                    <div class="row justify-content-center">
                        <div class="col-12 p-4 mb-3 bg-light text-dark rounded">
                            <div class="form-group mb-3">
                                <label for="cuil" class="text-dark">CUIL:</label>
                                <input type="number" class="form-control" id="cuil" name="txtcuil"
                                    placeholder="Ingrese el CUIL (XX-XXXXXXXX-X)" maxlength="13" 
                                    value="<%= request.getParameter("txtcuil") != null ? request.getParameter("txtcuil") : "" %>">
                            </div>
                            <div class="form-group mb-3">
                                <label for="idusuario" class="text-dark">ID USUARIO:</label>
                                <input type="text" class="form-control" id="idusuario" name="txtidusuario"
                                    placeholder="Ingrese el id usuario" maxlength="20"
                                    value="<%= request.getParameter("txtidusuario") != null ? request.getParameter("txtidusuario") : "" %>">
                            </div>
                            <div class="form-group mb-3">
                                <label for="dni" class="text-dark">DNI:</label>
                                <input type="number" class="form-control" id="dni" name="txtdni"
                                    placeholder="Ingrese el DNI (XXXXXXXX)" maxlength="8"
                                    value="<%= request.getParameter("txtdni") != null ? request.getParameter("txtdni") : "" %>">
                            </div>
                            <div class="form-group mb-3">
                                <label for="nombre" class="text-dark">Nombre:</label>
                                <input type="text" class="form-control" name="txtnombre" id="nombre"
                                    placeholder="Ingrese el nombre"
                                    value="<%= request.getParameter("txtnombre") != null ? request.getParameter("txtnombre") : "" %>">
                            </div>
                            <div class="form-group mb-3">
                                <label for="apellido" class="text-dark">Apellido:</label>
                                <input type="text" class="form-control" id="apellido" name="txtapellido"
                                    placeholder="Ingrese el apellido"
                                    value="<%= request.getParameter("txtapellido") != null ? request.getParameter("txtapellido") : "" %>">
                            </div>
                            <!-- Radio buttons para selección de sexo -->
                            <div class="form-group mb-3">
                                <label for="sexo" class="text-dark">Sexo: </label>
                                <br>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="rd-sexo" id="masculino" value="M" 
                                    <%= "M".equals(request.getParameter("rd-sexo")) ? "checked" : "" %>> 
                                    <label class="form-check-label text-dark" for="masculino">Masculino</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="rd-sexo" id="femenino" value="F" 
                                    <%= "F".equals(request.getParameter("rd-sexo")) ? "checked" : "" %>>
                                    <label class="form-check-label text-dark" for="femenino">Femenino</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="rd-sexo" id="otro" value="O" 
                                    <%= "O".equals(request.getParameter("rd-sexo")) ? "checked" : "" %>>
                                    <label class="form-check-label text-dark" for="otro">Otro...</label>
                                </div>
                            </div>
                            <div class="form-group mb-3">
                                <label for="nacionalidad" class="text-dark">Nacionalidad:</label>
                                <select id="nacionalidad" class="form-control" name="selectNacionalidad">
                                    <option value="0" disabled <%= request.getParameter("selectNacionalidad") == null ? "selected" : "" %>>Seleccione la nacionalidad:</option>
                                    <% 
                                    List<Nacionalidad> nacionalidades = (List<Nacionalidad>) request.getAttribute("nacionalidades");
                                    if (nacionalidades != null) {
                                        for (Nacionalidad nac : nacionalidades) {
                                    %>
                                    <option
											value="<%=nac.getNroNacionalidad()%>"
											<%=String.valueOf(nac.getNroNacionalidad()).equals(request.getParameter("selectNacionalidad"))? "selected": ""%>>
											<%=nac.getNroNacionalidad()%> -
											<%=nac.getNacionalidad()%></option>
										<% 
                                        }
                                    } else {
                                    %>
                                    <option value="0" disabled>No hay nacionalidades disponibles</option>
                                    <% 
                                    }
                                    %>
                                </select>
                            </div>
                            <div class="form-group mb-3">
                                <label for="provincia" class="text-dark">Provincia:</label>
                                <select id="provincia" class="form-control" name="selectProvincia">
                                    <option value="0" disabled <%= request.getParameter("selectProvincia") == null ? "selected" : "" %>>Seleccione la provincia:</option>
                                    <% 
                                    List<Provincia> provincias = (List<Provincia>) request.getAttribute("provincias");
                                    if (provincias != null) {
                                        for (Provincia prov : provincias) {
                                    %>
                                    <option
											value="<%=prov.getNroProvincia()%>"
											<%=String.valueOf(prov.getNroProvincia()).equals(request.getParameter("selectProvincia"))? "selected": ""%>>
											<%=prov.getNroProvincia()%> -
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
                                <label for="localidad" class="text-dark">Localidad:</label>
                                <select id="localidad" class="form-control" name="selectLocalidad">
                                    <option value="0" disabled <%= request.getParameter("selectLocalidad") == null ? "selected" : "" %>>Seleccione la localidad:</option>
                                    <% 
                                    List<Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
                                    if (localidades != null) {
                                        for (Localidad loc : localidades) {
                                    %>
                                   <option
											value="<%=loc.getNroLocalidad()%>"
											<%=String.valueOf(loc.getNroLocalidad()).equals(request.getParameter("selectLocalidad"))? "selected": ""%>>
											<%=loc.getNroLocalidad()%> -
											<%=loc.getLocalidad()%></option>
										<% 
                                        }
                                    } else {
                                    %>
                                    <option value="0" disabled>No hay localidades disponibles</option>
                                    <% 
                                    }
                                    %>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer bg-dark text-light mt-4">
                    <button type="submit" class="btn btn-primary">Aplicar filtros</button>
                    <a href="ServletListarClientes" class="btn btn-secondary">Deshacer filtros</a>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
</form>
		





<!-- JavaScript de Bootstrap 5 y jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#tablaClientes').DataTable({
            "language": {
                "zeroRecords": "No se encontraron resultados",
                "info": "Mostrando página PAGE de PAGES",
                "infoEmpty": "No hay registros disponibles",
                "infoFiltered": "(filtrado de MAX registros totales)",
                "paginate": {
                    "first": "Primero",
                    "last": "Último",
                    "next": "Siguiente",
                    "previous": "Anterior"
                }
            },
            "searching": false,
            "scrollX": true,    // Habilitar desplazamiento horizontal
            "autoWidth": false,  // Desactivar auto width para evitar scrollbar horizontal
            "dom": '<"top"f>rt<"bottom"ip><"clear">'
        });

        // JavaScript personalizado para asegurar el funcionamiento del dropdown
        $('.dropdown-toggle').on('click', function(e) {
            e.preventDefault();
            $(this).parent().toggleClass('show');
            $(this).next('.dropdown-menu').toggleClass('show');
        });

        $(document).on('click', function(e) {
            if (!$(e.target).closest('.dropdown').length) {
                $('.dropdown').removeClass('show');
                $('.dropdown-menu').removeClass('show');
            }
        });
    });
</script>




</body>
</html>