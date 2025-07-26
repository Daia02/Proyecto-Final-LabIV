<%@page import="java.util.ArrayList"%>
<%@page import="entidades.SolicitudPrestamo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="usuarioNavbar.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<title>historial Prestamos</title>
  <style>
        .icono-rechazo, .icono-aprobado {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            position: relative;
            margin: 1px;
        }
        .icono-rechazo {
            background-color: red;
        }
        .icono-rechazo::before,
        .icono-rechazo::after {
            content: '';
            position: absolute;
            top: 50%;
            left: 50%;
            width: 30px;
            height: 5px;
            background-color: white;
        }
        .icono-rechazo::before {
            transform: translate(-50%, -50%) rotate(45deg);
        }
        .icono-rechazo::after {
            transform: translate(-50%, -50%) rotate(-45deg);
        }
        .icono-aprobado {
            background-color: green;
        }
        .icono-aprobado::before {
            content: '';
            position: absolute;
            top: 50%;
            left: 50%;
            width: 10px;
            height: 20px;
            border: solid white;
            border-width: 0 5px 5px 0;
            transform: translate(-50%, -50%) rotate(45deg);
        }
        .text-center td, .text-center th {
    text-align: center;
}
    </style>

</head>
<body>
<div class="container mt-4">
    <h2 class="text-center mb-4">historial Préstamos</h2>
    <table id="tablaPrestamos" class="table table-bordered table-striped table-hover text-center">
        <thead class="table-dark">
            <tr>
           		 
                <th>N°Solicitud</th>
				<th>Fecha</th>
                <th>Cui</th>
                <th>Numero cuenta</th>
                
                <th>Importe</th>
                <th>Interes</th>
                <th>Cuotas</th>
                <th>Total</th>
                <th>Responsable</th>
                <th>Estado</th>
                <th>Icono</th>
                <th>Detalles</th>
            </tr>
        </thead>
        <tbody class="text-center">
            <% 
         
            ArrayList<SolicitudPrestamo> lista = (ArrayList<SolicitudPrestamo>) request.getAttribute("lista");
            if (lista != null && !lista.isEmpty()) {
                for (SolicitudPrestamo s : lista) {
            %>
            <tr>
                <form action="ServletAdminHistorialPrestamo" method="post">
                    <td ><%= s.getNroSolicitud() %>
                        <input class="cuil-column" type="hidden" name="NumeroSolicitud" value="<%= s.getNroSolicitud() %>">
                    </td>
                    <td><%= s.getFecha_pedido() %>
                        <input type="hidden" name="fecha" value="<%= s.getFecha_pedido() %>">
                    </td>
                    <td><%= s.getPrestamos().getCliente().getCuilCl() %>
                        <input type="hidden" name="Cuil" value="<%= s.getPrestamos().getCliente().getCuilCl()%>">
                    </td>
                    <td><%= s.getCuenta().getNroCuenta() %>
                        <input type="hidden" name="cuenta" value="<%= s.getCuenta().getNroCuenta() %>">
                    </td>
                    <td><%= s.getPrestamos().getImporte() %>
                        <input type="hidden" name="Importe" value="<%= s.getPrestamos().getImporte() %>">
                    </td>
                    <td><%= s.getPrestamos().getInteres() %>
                        <input type="hidden" name="interes" value="<%= s.getPrestamos().getInteres() %>">
                    </td>
                    <td><%= s.getPrestamos().getCoutas() %>
                        <input type="hidden" name="coutas" value="<%= s.getPrestamos().getCoutas() %>">
                    </td>
                    <td><%= s.getPrestamos().getTotal() %>
                        <input type="hidden" name="Total" value="<%= s.getPrestamos().getTotal() %>">
                    </td>
                    <td><%= s.getAdministrador().getAd_Usuario() %>
                        <input type="hidden" name="Admin" value="<%= s.getAdministrador().getAd_Usuario() %>">
                    </td>
                    
					  <td><%= s.getEstado() %>
                        <input type="hidden" name="Total" value="<%= s.getEstado() %>">
                        
                    </td>  
                     </td>
                     <%if(s.getEstado().equals("Rechazado")){ %>
                    <td>
                        <div class="icono-rechazo"></div> <!-- Icono en columna de acciones -->
                    </td>               <%} %>
                    
                   <%if(s.getEstado().equals("Aprobado")){ %>
                    <td>
                        <div class="icono-aprobado"></div> <!-- Icono en columna de acciones -->
                    </td>               <%} %>
                    
                     <%if(s.getEstado().equals("Pendiente")){ %>
                    <td>
                        <div class="icono-aprobado"></div> <!-- Icono en columna de acciones -->
                    </td>               <%} %>
                    <td>
                        <div class="btn-container">
                            <input class="btn btn-sm btn-primary" type="submit" name="VerDetalles" value="Ver Detalles">
                            
                        </div>
                    </td>
                    
                </form>
            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="9" class="text-center">No hay Préstamos Pendientes registrados.</td>
            </tr>
            <% 
            } 
            %>
        </tbody>
    </table>
</div>
<script>
    $(document).ready(function() {
        $('#tablaPrestamos').DataTable({
            "language": {
                "url": "https://cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json"
            },
            //"searching": false, // Elimina el cuadro de búsqueda
            //"paging": false,    // Muestra todos los registros en una sola página
            //"lengthChange": false // Oculta el selector de registros por página
        });
    });
</script>



<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

<%if(request.getAttribute("VerDetalles")!=null){%>
<script >
Swal.fire({
    title: "Detalles!",
    text: "Haga clic en el botón",
    icon: "success",
    html: ``,
    confirmButtonText: "Aceptar",
    customClass: {
        confirmButton: "btn btn-success"
    }
});

</script>
	<%}%>


</body>
</html>