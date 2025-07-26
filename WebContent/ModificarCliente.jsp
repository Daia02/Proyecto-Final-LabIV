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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <title>Editar Cliente</title>
    <style type="text/css">
    .btn-container {
        display: flex;
        justify-content: center;
        gap: 5px;
    }
    #map {
        height: 400px;
        width: 100%;
    }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCd406fqemHhdxr9GnMRFU5jlxMw7BlQBk&callback=initMap" async defer></script>
    
</head>
<body class="bg-light">
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
    %>
    <div class="alert alert-info text-center" role="alert">
        <%=mensaje%>
    </div>
    <%
        if ("El cliente fue actualizado exitosamente.".equals(mensaje)) {
    
        }
        }
    %>

    <!-- Mostrar mensaje de error si existe -->
        <%
            if (request.getAttribute("error") != null) {
        %>
        <div class="alert alert-danger" role="alert">
            <%=request.getAttribute("error")%>
        </div>
        <% } %>

        <div class="container-fluid p-3 mb-2 text-dark"
            style="background-color: transparent;">
            <div class="row justify-content-center">
                <div class="col-4 p-4">
                    <a href="ServletListarClientes" id="volverClientes"> Volver a
                        la tabla de clientes </a>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-4 p-4 mb-3 bg-dark text-white rounded">
                    <h3 class="text-center">Editar Cliente</h3>
                    <%
                // Obtener el cliente desde el atributo "cliente" de la solicitud
                Cliente cliente = (Cliente) request.getAttribute("cliente");
            %>
                    <form action="ServletModificarClientes" method="post">
                                            <div class="form-group mb-3">
                            <label for="cuil">CUIL:</label> 
                            <input type="text" class="form-control" id="cuil" name="txtcuil" value="<%= cliente.getCuilCl() %>" readonly>
                        </div>

                        <div class="form-group mb-3">
                            <label for="idusuario">ID USUARIO:</label> 
                            <input type="text" class="form-control" id="idusuario" name="txtidusuario" value="<%= cliente.getIdUsuario() %>" required pattern="\d+" minlength="5" maxlength="20">
                        </div>

                        <div class="form-group mb-3">
                            <label for="dni">DNI:</label> 
                            <input type="number" class="form-control" id="dni" name="txtdni" value="<%= cliente.getDni() %>" required pattern="\d+" minlength="7" maxlength="8">
                        </div>

                        <div class="form-group mb-3">
                            <label for="nombre">Nombre:</label> 
                            <input type="text" class="form-control" id="nombre" name="txtnombre" value="<%= cliente.getNombre() %>" required pattern="[a-zA-Z\s]+" minlength="2" maxlength="50">
                        </div>

                        <div class="form-group mb-3">
                            <label for="apellido">Apellido:</label> 
                            <input type="text" class="form-control" id="apellido" name="txtapellido" value="<%= cliente.getApellido() %>" required pattern="[a-zA-Z\s]+" minlength="2" maxlength="50">
                        </div>

                        <!-- Radio buttons para selección de sexo -->
                        <div class="form-group mb-3">
                            <label for="sexo">Sexo:</label><br>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="rd-sexo" id="masculino" value="Masculino" <%= cliente.getSexo().equals("M") ? "checked" : "" %> required>
                                <label class="form-check-label" for="masculino">Masculino</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="rd-sexo" id="femenino" value="Femenino" <%= cliente.getSexo().equals("F") ? "checked" : "" %>>
                                <label class="form-check-label" for="femenino">Femenino</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="rd-sexo" id="otro" value="Otro" <%= cliente.getSexo().equals("O") ? "checked" : "" %>>
                                <label class="form-check-label" for="otro">Otro...</label>
                            </div>
                        </div>

                        <div class="form-group mb-3">
                            <label for="nacionalidad">Nacionalidad:</label> 
                            <select id="nacionalidad" class="form-control" name="selectNacionalidad" required>
                                <option value="0" disabled>Seleccione su nacionalidad:</option>
                                <%
                                List<Nacionalidad> nacionalidades = (List<Nacionalidad>) request.getAttribute("nacionalidades");
                                if (nacionalidades != null) {
                                    for (Nacionalidad nac : nacionalidades) {
                                %>
                                <option value="<%= nac.getNroNacionalidad() %>" <%= cliente.getNroNacionalidad().equals(String.valueOf(nac.getNroNacionalidad())) ? "selected" : "" %>>
                                    <%= nac.getNroNacionalidad() %> - <%= nac.getNacionalidad() %>
                                </option>
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
                            <label for="provincia">Provincia:</label> 
                            <select id="provincia" class="form-control" name="selectProvincia" required>
                                <option value="0" disabled>Seleccione su provincia:</option>
                                <%
                                List<Provincia> provincias = (List<Provincia>) request.getAttribute("provincias");
                                if (provincias != null) {
                                    for (Provincia prov : provincias) {
                                %>
                                <option value="<%= prov.getNroProvincia() %>" <%= cliente.getNroProvincia().equals(String.valueOf(prov.getNroProvincia())) ? "selected" : "" %>>
                                    <%= prov.getNroProvincia() %> - <%= prov.getProvincia() %>
                                </option>
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
                            <label for="localidad">Localidad:</label> 
                            <select id="localidad" class="form-control" name="selectLocalidad" required>
                                <option value="0" disabled>Seleccione su localidad:</option>
                                <%
                                List<Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
                                if (localidades != null) {
                                    for (Localidad loc : localidades) {
                                %>
                                <option value="<%= loc.getNroLocalidad() %>" <%= cliente.getNroLocalidad().equals(String.valueOf(loc.getNroLocalidad())) ? "selected" : "" %>>
                                    <%= loc.getNroLocalidad() %> - <%= loc.getLocalidad() %>
                                </option>
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
                                                <div class="form-group mb-3">
                            <label for="fechanac">Fecha de nacimiento:</label> 
                            <input type="date" class="form-control" id="fechanac" name="txtFechaNac" value="<%= cliente.getFechaNacimiento() %>" required>
                        </div>

                        <div class="form-group mb-3">
                            <label for="direccion">Dirección:</label> 
                            <input type="text" class="form-control" id="direccion" name="txtDirecccion" value="<%= cliente.getDireccion() %>" required minlength="5" maxlength="100">
                        </div>

                        <div class="form-group mb-3">
                            <label for="email">Correo electrónico:</label> 
                            <input type="email" class="form-control" id="email" name="txtEmail" value="<%= cliente.getCorreoElectronico() %>" required>
                        </div>

                        <div class="form-group mb-3">
                            <label for="telefono">Teléfono:</label> 
                            <input type="tel" class="form-control" id="telefono" name="txtTelefono" value="<%= cliente.getTelefono() %>" required pattern="^\d{10}$">
                        </div>

                        <div class="btn-container">
                            <button type="submit" name="btnAceptar" class="btn btn-warning w-100 mt-2">Actualizar</button>
                            <a class="btn btn-secondary w-100 mt-2" href="ServletListarClientes">Cancelar</a>
                        </div>

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