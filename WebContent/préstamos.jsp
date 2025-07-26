<%@page import="entidades.SolicitudPrestamo"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="adminNavbar.jsp"%>

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
<style>
    .cuil-column {
        display: none;
    }
</style>
<title>Prestamos</title>
</head>
<body class="bg-light">
<div class="container mt-3 d-flex justify-content-center">
    <div class="w-50">
        <% if (request.getAttribute("mensajeExito") != null) { %>
        <div class="alert alert-success text-center" role="alert">
            <%= request.getAttribute("mensajeExito") %>
        </div>
        <% } %>
        <% if (request.getAttribute("mensajeError") != null) { %>
        <div class="alert alert-danger text-center" role="alert">
            <%= request.getAttribute("mensajeError") %>
        </div>
        <% } %>
    </div>
</div>
<div class="container mt-4">
    <h2 class="text-center mb-4">Préstamos</h2>
    <table id="tablaPrestamos" class="table table-bordered table-striped table-hover text-center">
        <thead class="table-dark">
            <tr>
                <th class="cuil-column">N°Solicitud</th>
                <th>Fecha</th>
                <th>CUIL</th>
                <th>Numero cuenta</th>
                <th>Importe</th>
                <th>Interes</th>
                <th>Cuotas</th>
                <th>Total</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
            ArrayList<SolicitudPrestamo> lista = (ArrayList<SolicitudPrestamo>) request.getAttribute("SolicitudPrestamo");
            if (lista != null && !lista.isEmpty()) {
                for (SolicitudPrestamo s : lista) {
            %>
            <tr>
                <form action="ServeltAdminPrestamos" method="post">
                    <td style="display: none;"><%= s.getNroSolicitud() %>
                        <input class="cuil-column" type="hidden" name="NumeroSolicitud" value="<%= s.getNroSolicitud() %>">
                    </td>
                    <td><%= s.getFecha_pedido() %>
                        <input type="hidden" name="fecha" value="<%= s.getFecha_pedido() %>">
                    </td>
                    <td><%= s.getPrestamos().getCliente().getCuilCl() %>
                        <input type="hidden" name="Cuil" value="<%= s.getPrestamos().getCliente().getCuilCl() %>">
                    </td>
                    <td><%= s.getCuenta().getNroCuenta() %>
                        <input type="hidden" name="cuenta" value="<%= s.getCuenta().getNroCuenta() %>">
                    </td>
                    <td><%= s.getPrestamos().getImporte() %>
                        <input type="hidden" name="importe" value="<%= s.getPrestamos().getImporte() %>">
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
                    <td>
                        <div class="btn-container">
                            <input class="btn btn-sm btn-warning" type="submit" name="BtnAutorizar" value="Autorizar">
                            <input class="btn btn-sm btn-danger" type="submit" name="BtnRechazar" value="Rechazar">
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
            "searching": false, // Elimina el cuadro de búsqueda
            "paging": false,    // Muestra todos los registros en una sola página
            "lengthChange": false // Oculta el selector de registros por página
        });
    });
</script>


 <p> 
                       <span style="display: none; id="modalNumeroSolicitud">
                           <%= request.getAttribute("NumeroSolicitud") != null ? request.getAttribute("NumeroSolicitud") : "No disponible" %>
                           
                       </span>
                       
                       <input style="display: none; type="hidden" name="NumeroSolicitud" value="<%= request.getAttribute("NumeroSolicitud") != null ? request.getAttribute("fecha") : "" %>">
                   </p>


<!-- JavaScript de Bootstrap 5 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
   <form id="prestamoForm" action="ServeltAdminPrestamos" method="post">
       <div class="modal-dialog modal-dialog-centered modal-lg">
           <div class="modal-content">
               <div class="modal-header bg-primary text-white">
                   <h5 class="modal-title" id="confirmModalLabel">Autorizar Prestamo</h5>
                   <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body">
                   <div class="row">
                       <div class="col-md-6">
                       <span style="display: none; id="modalNumeroSolicitud">
                           <%= request.getAttribute("NumeroSolicitud") != null ? request.getAttribute("NumeroSolicitud") : "No disponible" %>
                           
                       </span>
                       
                       <input style="display: none; type="hidden" name="NumeroSolicitud" value="<%= request.getAttribute("NumeroSolicitud") != null ? request.getAttribute("NumeroSolicitud") : "" %>">
                       
                           <p><strong>Fecha:</strong> 
                               <span id="modalFecha"><%= request.getAttribute("fecha") != null ? request.getAttribute("fecha") : "No disponible" %></span>
                               <input type="hidden" name="fecha" value="<%= request.getAttribute("fecha") != null ? request.getAttribute("fecha") : "" %>">
                           </p>
                           <p><strong>Cuil:</strong> 
                               <span id="modalCuil"><%= request.getAttribute("Cuil") != null ? request.getAttribute("Cuil") : "No seleccionado" %></span>
                               <input type="hidden" name="Cuil" value="<%= request.getAttribute("Cuil") != null ? request.getAttribute("Cuil") : "" %>">
                           </p>
                           <p><strong>Importe pedido:</strong> $ 
                               <span id="modalImportePedido"><%= request.getAttribute("cuenta") != null ? request.getAttribute("cuenta") : "0.00" %></span>
                               <input type="hidden" name="cuenta" value="<%= request.getAttribute("cuenta") != null ? request.getAttribute("cuenta") : "" %>">
                           </p>
                       </div>
                       <div class="col-md-6">
                           <p><strong>Importe:</strong> $ 
                               <span id="modalImporte"><%= request.getAttribute("importe") != null ? request.getAttribute("importe") : "0.00" %></span>
                               <input type="hidden" name="importe" value="<%= request.getAttribute("importe") != null ? request.getAttribute("importe") : "0.00" %>">
                           </p>
                           <p><strong>Interes:</strong> $ 
                               <span id="modalInteres"><%= request.getAttribute("interes") != null ? request.getAttribute("interes") : "0.00" %></span>
                               <input type="hidden" name="interes" value="<%= request.getAttribute("interes") != null ? request.getAttribute("interes") : "0.00" %>">
                           </p>
                           <p><strong>Cuotas:</strong> 
                               <span id="modalCuotas"><%= request.getAttribute("coutas") != null ? request.getAttribute("coutas") : "0.00" %></span>
                               <input type="hidden" name="coutas" value="<%= request.getAttribute("coutas") != null ? request.getAttribute("coutas") : "0.00" %>">
                           </p>
                           <p><strong>Total:</strong> $ 
                               <span id="modalTotal"><%= request.getAttribute("Total") != null ? request.getAttribute("Total") : "0.00" %></span>
                               <input type="hidden" name="Total" value="<%= request.getAttribute("Total") != null ? request.getAttribute("Total") : "0.00" %>">
                           </p>
                       </div>
                   </div>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                   <button type="submit" name="btnConfirmarPrestamo" class="btn btn-primary">Confirmar</button>
               </div>
           </div>
       </div>
   </form>
</div>

<div class="modal fade" id="confirmModal1" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
   <form id="prestamoForm" action="ServeltAdminPrestamos" method="post">
       <div class="modal-dialog modal-dialog-centered modal-lg">
           <div class="modal-content">
               <div class="modal-header bg-primary text-white">
                   <h5 class="modal-title" id="confirmModalLabel">RECHAZAR PRESTAMOS</h5>
                   <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body">
                   <div class="row">
                       <div class="col-md-6">
                       <span style="display: none; id="modalNumeroSolicitud">
                           <%= request.getAttribute("NumeroSolicitud") != null ? request.getAttribute("NumeroSolicitud") : "No disponible" %>
                           
                       </span>
                       
                       <input style="display: none; type="hidden" name="NumeroSolicitud" value="<%= request.getAttribute("NumeroSolicitud") != null ? request.getAttribute("NumeroSolicitud") : "" %>">
                       
                           <p><strong>Fecha:</strong> 
                               <span id="modalFecha"><%= request.getAttribute("fecha") != null ? request.getAttribute("fecha") : "No disponible" %></span>
                               <input type="hidden" name="fecha" value="<%= request.getAttribute("fecha") != null ? request.getAttribute("fecha") : "" %>">
                           </p>
                           <p><strong>Cuil:</strong> 
                               <span id="modalCuil"><%= request.getAttribute("Cuil") != null ? request.getAttribute("Cuil") : "No seleccionado" %></span>
                               <input type="hidden" name="Cuil" value="<%= request.getAttribute("Cuil") != null ? request.getAttribute("Cuil") : "" %>">
                           </p>
                           <p><strong>Importe pedido:</strong> $ 
                               <span id="modalImportePedido"><%= request.getAttribute("cuenta") != null ? request.getAttribute("cuenta") : "0.00" %></span>
                               <input type="hidden" name="cuenta" value="<%= request.getAttribute("cuenta") != null ? request.getAttribute("cuenta") : "" %>">
                           </p>
                       </div>
                       <div class="col-md-6">
                           <p><strong>Importe:</strong> $ 
                               <span id="modalImporte"><%= request.getAttribute("importe") != null ? request.getAttribute("importe") : "0.00" %></span>
                               <input type="hidden" name="importe" value="<%= request.getAttribute("importe") != null ? request.getAttribute("importe") : "0.00" %>">
                           </p>
                           <p><strong>Interes:</strong> $ 
                               <span id="modalInteres"><%= request.getAttribute("interes") != null ? request.getAttribute("interes") : "0.00" %></span>
                               <input type="hidden" name="interes" value="<%= request.getAttribute("interes") != null ? request.getAttribute("interes") : "0.00" %>">
                           </p>
                           <p><strong>Cuotas:</strong> 
                               <span id="modalCuotas"><%= request.getAttribute("coutas") != null ? request.getAttribute("coutas") : "0.00" %></span>
                               <input type="hidden" name="coutas" value="<%= request.getAttribute("coutas") != null ? request.getAttribute("coutas") : "0.00" %>">
                           </p>
                           <p><strong>Total:</strong> $ 
                               <span id="modalTotal"><%= request.getAttribute("Total") != null ? request.getAttribute("Total") : "0.00" %></span>
                               <input type="hidden" name="Total" value="<%= request.getAttribute("Total") != null ? request.getAttribute("Total") : "0.00" %>">
                           </p>
                       </div>
                   </div>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                  <button type="submit" name="btnConfirmarRechazar" class="btn btn-danger">Confirmar</button>
                  
               </div>
           </div>
       </div>
   </form>
</div>

<!-- Modal Actual -->

<script>
    // Comprobar si el atributo 'mostrarModal' está presente en el request
    <% if (request.getAttribute("mostrarModal") != null) { %>
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: "btn btn-success me-2",
            cancelButton: "btn btn-danger"
        },
        buttonsStyling: false,
        allowOutsideClick: false  // Evita cerrar el modal al hacer clic afuera
    });

    swalWithBootstrapButtons.fire({
        title: 'Confirmación de Solicitud',
        html: `
            <form id="sweetForm" action="ServeltAdminPrestamos" method="post">
    
        	<span style="display: none; id="modalNumeroSolicitud">
            <%= request.getAttribute("NumeroSolicitud") != null ? request.getAttribute("NumeroSolicitud") : "No disponible" %>
            
       		 </span>
        
       		 <input style="display: none; type="hidden" name="NumeroSolicitud" value="<%= request.getAttribute("NumeroSolicitud") != null ? request.getAttribute("NumeroSolicitud") : "" %>">
       		 <p><strong>Fecha:</strong> 
             <span id="modalFecha"><%= request.getAttribute("fecha") != null ? request.getAttribute("fecha") : "No disponible" %></span>
             <input type="hidden" name="fecha" value="<%= request.getAttribute("fecha") != null ? request.getAttribute("fecha") : "" %>">
         </p>
         <p><strong>Cuil:</strong> 
             <span id="modalCuil"><%= request.getAttribute("Cuil") != null ? request.getAttribute("Cuil") : "No seleccionado" %></span>
             <input type="hidden" name="Cuil" value="<%= request.getAttribute("Cuil") != null ? request.getAttribute("Cuil") : "" %>">
         </p>
         <p><strong>Importe pedido:</strong> $ 
             <span id="modalImportePedido"><%= request.getAttribute("cuenta") != null ? request.getAttribute("cuenta") : "0.00" %></span>
             <input type="hidden" name="cuenta" value="<%= request.getAttribute("cuenta") != null ? request.getAttribute("cuenta") : "" %>">
         </p>
    
         <p><strong>Importe:</strong> $ 
         <span id="modalImporte"><%= request.getAttribute("importe") != null ? request.getAttribute("importe") : "0.00" %></span>
         <input type="hidden" name="importe" value="<%= request.getAttribute("importe") != null ? request.getAttribute("importe") : "0.00" %>">
     </p>
     <p><strong>Interes:</strong> $ 
         <span id="modalInteres"><%= request.getAttribute("interes") != null ? request.getAttribute("interes") : "0.00" %></span>
         <input type="hidden" name="interes" value="<%= request.getAttribute("interes") != null ? request.getAttribute("interes") : "0.00" %>">
     </p>
     <p><strong>Cuotas:</strong> 
         <span id="modalCuotas"><%= request.getAttribute("coutas") != null ? request.getAttribute("coutas") : "0.00" %></span>
         <input type="hidden" name="coutas" value="<%= request.getAttribute("coutas") != null ? request.getAttribute("coutas") : "0.00" %>">
     </p>
     <p><strong>Total:</strong> $ 
         <span id="modalTotal"><%= request.getAttribute("Total") != null ? request.getAttribute("Total") : "0.00" %></span>
         <input type="hidden" name="Total" value="<%= request.getAttribute("Total") != null ? request.getAttribute("Total") : "0.00" %>">
     </p> 
       		 
       		 
       		 
    
                
              
                <input type="hidden" name="btnConfirmarPrestamo" value="true">
            </form>
        `,
        showCancelButton: true,
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cerrar',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            // Agregar input 'btnConfirmar' al formulario
            const form = document.getElementById('sweetForm');

         
            
            form.submit();
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            swalWithBootstrapButtons.fire({
                title: "Cancelado",
                text: "La solicitud ha sido cancelada.",
                icon: "error"
            });
        }
    });
    <% } %>
</script>



<script>
    // Comprobar si el atributo 'mostrarModal' está presente en el request
    <% if (request.getAttribute("MostrarModalRechazar") != null) { %>
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: "btn btn-success me-2",
            cancelButton: "btn btn-danger"
        },
        buttonsStyling: false,
        allowOutsideClick: false  // Evita cerrar el modal al hacer clic afuera
    });

    swalWithBootstrapButtons.fire({
        title: 'Rechazar Prestamo',
        html: `
            <form id="sweetForm" action="ServeltAdminPrestamos" method="post">
    
        	<span style="display: none; id="modalNumeroSolicitud">
            <%= request.getAttribute("NumeroSolicitud") != null ? request.getAttribute("NumeroSolicitud") : "No disponible" %>
            
       		 </span>
        
       		 <input style="display: none; type="hidden" name="NumeroSolicitud" value="<%= request.getAttribute("NumeroSolicitud") != null ? request.getAttribute("NumeroSolicitud") : "" %>">
       		 <p><strong>Fecha:</strong> 
             <span id="modalFecha"><%= request.getAttribute("fecha") != null ? request.getAttribute("fecha") : "No disponible" %></span>
             <input type="hidden" name="fecha" value="<%= request.getAttribute("fecha") != null ? request.getAttribute("fecha") : "" %>">
         </p>
         <p><strong>Cuil:</strong> 
             <span id="modalCuil"><%= request.getAttribute("Cuil") != null ? request.getAttribute("Cuil") : "No seleccionado" %></span>
             <input type="hidden" name="Cuil" value="<%= request.getAttribute("Cuil") != null ? request.getAttribute("Cuil") : "" %>">
         </p>
         <p><strong>Importe pedido:</strong> $ 
             <span id="modalImportePedido"><%= request.getAttribute("cuenta") != null ? request.getAttribute("cuenta") : "0.00" %></span>
             <input type="hidden" name="cuenta" value="<%= request.getAttribute("cuenta") != null ? request.getAttribute("cuenta") : "" %>">
         </p>
    
         <p><strong>Importe:</strong> $ 
         <span id="modalImporte"><%= request.getAttribute("importe") != null ? request.getAttribute("importe") : "0.00" %></span>
         <input type="hidden" name="importe" value="<%= request.getAttribute("importe") != null ? request.getAttribute("importe") : "0.00" %>">
     </p>
     <p><strong>Interes:</strong> $ 
         <span id="modalInteres"><%= request.getAttribute("interes") != null ? request.getAttribute("interes") : "0.00" %></span>
         <input type="hidden" name="interes" value="<%= request.getAttribute("interes") != null ? request.getAttribute("interes") : "0.00" %>">
     </p>
     <p><strong>Cuotas:</strong> 
         <span id="modalCuotas"><%= request.getAttribute("coutas") != null ? request.getAttribute("coutas") : "0.00" %></span>
         <input type="hidden" name="coutas" value="<%= request.getAttribute("coutas") != null ? request.getAttribute("coutas") : "0.00" %>">
     </p>
     <p><strong>Total:</strong> $ 
         <span id="modalTotal"><%= request.getAttribute("Total") != null ? request.getAttribute("Total") : "0.00" %></span>
         <input type="hidden" name="Total" value="<%= request.getAttribute("Total") != null ? request.getAttribute("Total") : "0.00" %>">
     </p> 
       		 
       		 
       		 
    
                
              
                <input type="hidden" name="btnConfirmarRechazar" value="true">
            </form>
        `,
        showCancelButton: true,
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cerrar',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            // Agregar input 'btnConfirmar' al formulario
            const form = document.getElementById('sweetForm');
          
            
            form.submit();
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            swalWithBootstrapButtons.fire({
                title: "Cancelado",
                text: "La solicitud ha sido cancelada.",
                icon: "error"
            });
        }
    });
    <% } %>
</script>
<%if(request.getAttribute("mensajeError")!=null){%>

<script>
Swal.fire({
    icon: "error",
    title: "Oops...",
    text: "<%= request.getAttribute("mensajeError") %>",
    confirmButtonText: "Aceptar",
    customClass: {
        confirmButton: "btn btn-danger"
    }
});
</script>
	<%}%>




<%if(request.getAttribute("mensajeExito")!=null){%>
<script >
Swal.fire({
    title: "<%= request.getAttribute("mensajeExito") %>!",
    text: "Haga clic en el botón",
    icon: "success",
    confirmButtonText: "Aceptar",
    customClass: {
        confirmButton: "btn btn-success"
    }
});

</script>
	<%}%>






<script>
    // Comprobar si el atributo 'mostrarModal' está presente en el request
    <% if (request.getAttribute("mostrarModallll") != null) { %>
        // Si está presente, mostrar el modal automáticamente
        
        var myModal = new bootstrap.Modal(document.getElementById('confirmModal'), {
            keyboard: false
        });
        myModal.show();
    <% } %>
    
    <% if (request.getAttribute("MostrarModalRechazarr") != null) { %>
    // Si está presente, mostrar el modal automáticamente
    
    var myModal = new bootstrap.Modal(document.getElementById('confirmModal1'), {
        keyboard: false
    });
    myModal.show();
<% } %>
</script>

</body>
</html>