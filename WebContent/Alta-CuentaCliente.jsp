<%@page import="jdk.nashorn.internal.ir.RuntimeNode.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entidades.Cliente" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entidades.TipoCuenta" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    
    <title>Clientes</title>
</head>
<body class="bg-light">

<!-- PARTE SUPERIOR -->
<div class="container-fluid p-3 mb-2 bg-dark text-light">
    <div class="row">
        <div class="col-6">
            <nav class="navbar navbar-expand-lg navbar-dark ">
                <a href="Home.jsp" class="navbar-brand">
                    <img src="Imagenes/logo_utn_blanco.png" class="img-fluid" style="max-width: 22px; vertical-align: middle;" alt="logo utn">
                </a>
                
                
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

             
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="clientesDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Clientes
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="clientesDropdown">
                                <li><a class="dropdown-item" href="Clientes.jsp">Tabla de Clientes</a></li>
                                <li><a class="dropdown-item" href="#">Alta de Clientes</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>

<div class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent;">
    <div class="row justify-content-center">
        <div class="col-4 p-4 mb-3 bg-dark text-white rounded">
            <h3 class="text-center">Buscar cliente</h3>
            <form method="get" action="servletCliente">
    	<div class="input-group input-group-sm mb-3">
      		<span class="input-group-text" id="inputGroup-sizing-sm">CUIL</span>
      		<input id="txtCuit" name="txtCuit" type="number" maxlength="11" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" placeholder="Ingrese un CUIL" required>
    	</div>
      		<input name="btnBuscar" value="Buscar" type="submit" class="btn btn-primary btn-sm">
      		<% Cliente aux = null; 
      		if(request.getAttribute("cliente")!=null){
      			aux = (Cliente)request.getAttribute("cliente");
      			session.setAttribute("sessionCliente", aux);
      			}
      		if(session.getAttribute("sessionCliente")!=null){
      			aux = (Cliente)session.getAttribute("sessionCliente");
      			}%>
    	<div class="form-group mb-3">
    		<%if(aux!=null){ %>
            <label for="dni">DNI:</label>
            <input class="form-control" type="text" value="<%=aux.getDni() %>" readonly="readonly">
        </div>
        
        <div class="form-group mb-3">
            <label for="nombre">Nombre:</label>
            <input class="form-control" id="nombre" value="<%=aux.getNombre() %>" readonly="readonly">
        </div>

        <div class="form-group mb-3">
            <label for="apellido">Apellido:</label>
            <input class="form-control" id="apellido" value="<%=aux.getApellido()%>" readonly="readonly">
        </div>

    	<h6>Sexo</h6>
    	<select class="form-select" aria-label="Default select example" >
        	<option value="1" <%if(aux.getSexo().equals("Femenino")){%>selected<%} %>>Femenino</option>
        	<option value="2" <%if(aux.getSexo().equals("Masculino")){%>selected<%} %>>Masculino</option>
        	
      	</select>
      	<%} %>
            </form>
        </div>
    </div>
</div>


<%if(aux!=null) { %>
<div class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent;">
    <div class="row justify-content-center">
        <div class="col-4 p-4 mb-3 bg-dark text-white rounded">
            <h3 class="text-center">Crear cuenta</h3>
            <form method="post" action="servletCrearCuenta">
                <div class="card-body">
                    <div class="d-grid gap-2">
                        <h6>Nombre de usuario</h6>
                        <input name="txtNombre" id="nombreUsuario" class="form-control" id="idusuario" placeholder="Ingrese el nombre de usuario" maxlength="10" required>
                        <h6>Contraseña</h6>
                        <input type="password" name="txtContra" type="text" id="contrasenia" class="form-control" placeholder="Ingrese la contraseña" required>
                        <h6>Repetir contraseña</h6>     
                        <input type="password" name="txtContraRepetir" type="text" id="repetirContrasenia" class="form-control" placeholder="Repita la contraseña" required>
                        <h6>ID de cuenta</h6>
                        <input readonly="readonly" name="txtIDCuenta" value="<%=session.getAttribute("ultimoIdCuenta") %>" id="idCuenta" class="form-control">
                        <h6>Fecha</h6>
                        <input name="txtFecha" type="date" id="fechaHoy" readonly="readonly" class="form-control">
                        <h6>Tipo de cuenta</h6>
                        <%ArrayList<TipoCuenta> lista = null;
                        if(session.getAttribute("listaTipoCuenta")!=null){
                    		lista = (ArrayList<TipoCuenta>)session.getAttribute("listaTipoCuenta");
                    	}
                        %>
                        <select name="tipoUsuario" class="form-select" aria-label="Default select example">
                        <%if(lista!=null){
                        		for(TipoCuenta item : lista){%>
                        			<option value="<%=item.getNroTipoCuenta()%>"><%=item.getTipoCuenta()%></option>
                        		<% }%>
                        	<%}%>
                        </select>
                        
                        <h6>CBU</h6>
                        <input name="txtCBU" type="text" id="usuarioCBU" class="form-control" placeholder="Ingrese un CBU" maxlength="12" required>
                        <h6>Saldo</h6>
                        <div class="input-group input-group-sm mb-3">
                          <span class="input-group-text" id="inputGroup-sizing-sm">$</span>
                          <input name="txtSaldo" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" value="10000" readonly="readonly">
                        </div>
                        <button name="btnCrear" id="btnCrear" type="submit" class="btn btn-primary btn-sm">Asignar cuenta</button>
                        <button  id="btnCancelar" type="submit" class="btn btn-danger btn-sm">Cancelar</button>
                      </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%}%>

<% 
	String mensaje = (String) request.getAttribute("resultadoCuenta");
%>
<% if(mensaje != null){%>
	<h1><%=mensaje %></h1>
<%}%>
<!-- JavaScript de Bootstrap 5 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

<!-- Script para la fecha actual -->
<script>
    const hoy = new Date();
    const fechaActual = hoy.toISOString().split('T')[0];
    document.getElementById('fechaHoy').value = fechaActual;
</script>
</body>
</html>
