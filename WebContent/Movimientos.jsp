<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="entidades.Movimiento" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    
    <title>Movimientos</title>
    
 <style>
        th, td {
            text-align: center;  /* Centra el texto en los encabezados y celdas */
        }

        .btn-container {
            display: flex;
            justify-content: center;
            gap: 5px;
        }

        .btn-sm {
            width: 80px;
        }

        table {
            table-layout: auto; /* Ajusta el tamaño de las celdas al contenido */
            width: 100%; /* Hace que la tabla ocupe el 100% del contenedor */
        }

        .table-container {
            margin-top: 30px;
        }
    </style>
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
                                Usuario
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="clientesDropdown">
                                <li><a class="dropdown-item" href="#">Transferir</a></li>
                                <li><a class="dropdown-item" href="#">Movimientos</a></li>
                                <li><a class="dropdown-item" href="#">Prestamos</a></li>
                                <li><a class="dropdown-item" href="#">Ver perfil</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>

<div class="container mt-4 d-flex justify-content-center">
    <div class="table-container">
        <h2 class="text-center mb-4">Historial de movimientos</h2>
        <table class="table table-bordered table-striped table-hover mb-4">
            <thead class="table-dark">
                <tr>
                    <th>Numero de transaccion</th>
                    <th>Origen</th>
                    <th>Destinatario</th>
                    <th>Fecha</th>
                    <th>Detalles</th>
                    <th>Importe</th>
                    <th>Tipo de movimiento</th>
                </tr>
            </thead>
            <tbody>
            <%
            	ArrayList<Movimiento> lista = null;
            	if(request.getAttribute("listaMov")!=null){
            		lista = (ArrayList<Movimiento>)request.getAttribute("listaMov");
            	}
            %>
            <% if(lista!=null){
            for(Movimiento item : lista){ %>
            	<tr>
                    <td><%=item.getNroMovimiento()%></td>
                    <td><%=item.getCuilEmisor() %></td>
                    <td><%=item.getCuilReceptor() %></td>
                    <td><%=item.getFecha() %></td>
                    <td><%=item.getDetalle() %></td>
                    <td><%=item.getImporte() %></td>
                    <td><%=item.getNroTipoMov() %></td>
                </tr>
            <%	
            }}
            %>
            </tbody>
        </table>
    </div>
</div>
<form method="post" action="servletListarMovimiento">
	<div class="container mt-4 d-flex justify-content-center">
		<input id="txtImporte" name="txtImporte" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" placeholder="Ingrese un importe">
		<select class="form-select" aria-label="Default select example" name="filtroImporte" >
        	<option value="<">Menor al importe</option>
        	<option value="=">Igual al importe</option>
        	<option value=">">Mayor al importe</option>   
      	</select>
		<input type="submit" name="BtnCargarMovimiento" value="Cargar movimientos">
	</div>
</form>

<!-- JavaScript de Bootstrap 5 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
 

</body>
</html>