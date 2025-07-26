<%@page import="entidades.Cuenta"%>
<%@page import="entidades.TipoCuenta"%>
<%@page import="java.util.List"%>
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
    
<title>Prestamos</title>

   <style>
    .icono-rechazo, .icono-aprobado {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        display: flex; /* Centra el contenido */
        align-items: center; /* Centrado vertical */
        justify-content: center; /* Centrado horizontal */
        margin: 10px auto; /* Centrado automático */
        position: relative;
    }
    .icono-rechazo {
        background-color: red;
    }
    .icono-rechazo::before,
    .icono-rechazo::after {
        content: '';
        position: absolute;
        width: 30px;
        height: 5px;
        background-color: white;
    }
    .icono-rechazo::before {
        transform: rotate(45deg);
    }
    .icono-rechazo::after {
        transform: rotate(-45deg);
    }
    .icono-aprobado {
        background-color: green;
    }
    .icono-aprobado::before {
        content: '';
        position: absolute;
        width: 10px;
        height: 20px;
        border: solid white;
        border-width: 0 5px 5px 0;
        transform: rotate(45deg);
    }
</style>
</head>
<body>

<div class="card text-center" style="background-color: rgba(0, 128, 0, 0.3); display:  <%= request.getAttribute("cartelAprobado") != null ? request.getAttribute("cartelAprobado") : "none;" %>;">
  <form action="ServletClientePrestamos" method="post">
  <div class="card-header">
   Estado Prestamos 
  </div>
  <div class="card-body">
    <h5 class="card-title">Prestamo solicitado con exito</h5>
    
     <button  id="btnCartelSolicitudNuevamante" type="submit" name="BtnSolicitarNuevamente" class="btn btn-primary btn-sm">Solicitar Nuevamente </button>
      <button  id="btnCartelEstado" type="submit" name="BtnIrEstadoP" class="btn btn-primary btn-sm">Ir Estado Prestamos </button>
  </div>
  <div class="card-footer text-muted">
   
  </div>
  </form>
</div>
<!-- cartel error -->
<div class="card text-center" style="background-color: rgba(255, 0, 0, 0.3); display:  <%= request.getAttribute("mensajeError") != null ? "block" : "none;" %>;">
  <form action="ServletClientePrestamos" method="post">
    <div class="card-header">
      Estado Préstamos
    </div>
    <div class="card-body">
      <h5 class="card-title"><%=request.getAttribute("mensajeError") %></h5>
      
      <button id="btnCartelSolicitudNuevamante" type="submit" name="BtnSolicitarNuevamente" class="btn btn-primary btn-sm">Solicitar Nuevamente</button>
      <button id="btnCartelEstado" type="submit" name="BtnVolverMenu" class="btn btn-primary btn-sm">Volver Menu</button>
    </div>
    <div class="card-footer text-muted">
      <!-- Puedes agregar más información aquí si es necesario -->
    </div>
  </form>
</div>



<div  class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent; display:<%=request.getAttribute("cartelAprobado") != null || request.getAttribute("mensajeError") != null ? "none" : "block" %>;">
    <div class="row justify-content-center">
        <div class="col-4 p-4 mb-3 bg-dark text-white rounded">
            <h3 class="text-center">Solicitar Prestamos</h3>
            <form action="ServletClientePrestamos" method="post">
                <div class="card-body">
                    <div class="d-grid gap-2">
               
                    
                    
                    
                       <h6>Cantidad</h6>
                        <div class="input-group input-group-sm mb-3">
                          <span class="input-group-text" id="inputGroup-sizing-sm">$</span>
                          <input id="monto" name="Monto" type="number" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm "  
                       			 min="1" step="1" 
                         		value="<%= request.getAttribute("Monto")!=null ? request.getAttribute("Monto"):""%>"
                         
                         		maxlength="10" required oninput="if (this.value.length > 10) this.value = this.value.slice(0, 10);">>
                        </div>
                        <h6>Coutas</h6>
                        <P>el interes aumenta 5% por cada coutas</P>
                         <input id="txtCoutas" name="Coutas" type="number" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" 
                         min="1" step="1"  
                          value="<%= request.getAttribute("Coutas")!=null ? request.getAttribute("Coutas"):""%>"
                         required>
				                       
										              <label for="tipoCuenta">Tipo De CUENTA: </label> 
						<select required id="tipoCuenta" class="form-control" name="selectTipoCuenta"  required>
						     <option value="" disabled selected>Seleccione su tipo de cuenta:</option>
						    <%
						        // Obtener la lista de tipos de cuenta desde el request
						        List<Cuenta> resultado = (List<Cuenta>) request.getAttribute("tipoCuenta");
						        String tipoCuentaSeleccionada = (String) request.getAttribute("tipoCuentaSeleccionada");
						        
						        if (resultado != null) {
						            // Iterar sobre la lista y agregar opciones al select
						            for (Cuenta nac : resultado) {
						                // Compara si la opción actual es la seleccionada
						                String seleccionado = tipoCuentaSeleccionada != null && tipoCuentaSeleccionada.equals(String.valueOf(nac.getNroTipoCuenta())) 
						                    ? "selected" 
						                    : "";
						    %>
						                <option value="<%=nac.getTipoCuenta().getNroTipoCuenta()%>" <%=seleccionado%>>
						                    <%=nac.getTipoCuenta().getNroTipoCuenta()%> - <%=nac.getTipoCuenta().getTipoCuenta() %>
						                </option>
						    <%
						            }
						        } else {
						    %>
						        <option value="0" disabled>No hay tipos de cuenta disponibles</option>
						    <%
						        }
						    %>
						</select>
				                       
                       
                      
                        
                        <button  id="btnCrear" type="submit" name="BtnAceptar" class="btn btn-primary btn-sm">Solicitar</button>
                        <a href="usuarioHome.jsp" id="btnCancelar" type="submit" class="btn btn-danger btn-sm">Cancelar</a>
                      </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- JavaScript de Bootstrap 5 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<!-- modal 1 -->
<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
   <form id="prestamoForm" action="ServletClientePrestamos" method="post">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <h5 class="modal-title" id="confirmModalLabel">Confirmación de Solicitud</h5>
                   <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body">
                   <!-- Información visible al usuario -->
                   
                   <p><strong>Tipo de cuenta:</strong> 
                       <span id="modalCbu">
                           <%= request.getAttribute("tipoCuentaSeleccionada") != null ? request.getAttribute("tipoCuentaSeleccionada") : "No disponible" %>
                           <%= request.getAttribute("nombreTipo") != null ? request.getAttribute("nombreTipo") : "No disponible" %>
                       </span>
                       
                       <input type="hidden" name="tipoCuentaSeleccionada" value="<%= request.getAttribute("tipoCuentaSeleccionada") != null ? request.getAttribute("tipoCuentaSeleccionada") : "" %>">
                   </p>
                   
                   <p><strong>Cantidad de Cuotas:</strong> 
                       <span id="modalCuotas">
                           <%= request.getAttribute("Coutas") != null ? request.getAttribute("Coutas") : "No seleccionado" %>
                       </span>
                       
                       <input type="hidden" name="Coutas" value="<%= request.getAttribute("Coutas") != null ? request.getAttribute("Coutas") : "" %>">
                   </p>
                   <p><strong>Importe pedido:</strong> $ 
                       <span id="modalImporete">
                           <%= request.getAttribute("Monto") != null ? request.getAttribute("Monto") : "0.00" %>
                       </span>
                       
                       <input type="hidden" name="Monto" value="<%= request.getAttribute("Monto") != null ? request.getAttribute("Monto") : "0.00" %>">
                   </p>
                   
                   <p><strong>Total a Pagar:</strong> $ 
                       <span id="modalTotal">
                           <%= request.getAttribute("total") != null ? request.getAttribute("total") : "0.00" %>
                       </span>
                       
                       <input type="hidden" name="total" value="<%= request.getAttribute("total") != null ? request.getAttribute("total") : "0.00" %>">
                   </p>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                   <button type="submit" name="btnConfirmar" class="btn btn-primary">Confirmar</button>
               </div>
           </div>
       </div>
   </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
 
<script>
    // Comprobar si el atributo 'mostrarModal' está presente en el request
    <% if (request.getAttribute("mostrarModa") != null) { %>
        // Si está presente, mostrar el modal automáticamente
        var myModal = new bootstrap.Modal(document.getElementById('confirmModal'), {
            keyboard: false
        });
        myModal.show();
    <% } %>
</script>

<script>
    // Comprobar si el atributo 'mostrarModal' está presente en el request
    <% if (request.getAttribute("mostrarModall") != null) { %>
        Swal.fire({
            title: 'Confirmaci\u00f3n de Solicitud',
            html: `
                <p><strong>Tipo de cuenta:</strong> 
                    <span id="modalCbu">
                        <%= request.getAttribute("tipoCuentaSeleccionada") != null ? request.getAttribute("tipoCuentaSeleccionada") : "No disponible" %> 
                        <%= request.getAttribute("nombreTipo") != null ? request.getAttribute("nombreTipo") : "No disponible" %>
                    </span>
                </p>
                <p><strong>Cantidad de Cuotas:</strong> 
                    <span id="modalCuotas">
                        <%= request.getAttribute("Coutas") != null ? request.getAttribute("Coutas") : "No seleccionado" %>
                    </span>
                </p>
                <p><strong>Importe pedido:</strong> $ 
                    <span id="modalImporete">
                        <%= request.getAttribute("Monto") != null ? request.getAttribute("Monto") : "0.00" %>
                    </span>
                </p>
                <p><strong>Total a Pagar:</strong> $ 
                    <span id="modalTotal">
                        <%= request.getAttribute("total") != null ? request.getAttribute("total") : "0.00" %>
                    </span>
                </p>
            `,
            showCancelButton: true,
            confirmButtonText: 'Confirmar',
            cancelButtonText: 'Cerrar',
            preConfirm: () => {
                // Crear un formulario y enviarlo dinámicamente al servidor
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = 'ServletClientePrestamos';

                // Añadir los datos ocultos
                const fields = [
                    { name: 'tipoCuentaSeleccionada', value: '<%= request.getAttribute("tipoCuentaSeleccionada") != null ? request.getAttribute("tipoCuentaSeleccionada") : "" %>' },
                    { name: 'Coutas', value: '<%= request.getAttribute("Coutas") != null ? request.getAttribute("Coutas") : "" %>' },
                    { name: 'Monto', value: '<%= request.getAttribute("Monto") != null ? request.getAttribute("Monto") : "0.00" %>' },
                    { name: 'total', value: '<%= request.getAttribute("total") != null ? request.getAttribute("total") : "0.00" %>' },
                ];

                fields.forEach(({ name, value }) => {
                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = name;
                    input.value = value;
                    form.appendChild(input);
                });

                document.body.appendChild(form);
                form.submit();
            }
        });
    <% } %>
</script>


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
            <form id="sweetForm" action="ServletClientePrestamos" method="post">
                <p><strong>Tipo de cuenta:</strong> 
                    <span id="modalCbu">
                        <%= request.getAttribute("tipoCuentaSeleccionada") != null ? request.getAttribute("tipoCuentaSeleccionada") : "No disponible" %> 
                        <%= request.getAttribute("nombreTipo") != null ? request.getAttribute("nombreTipo") : "No disponible" %>
                    </span>
                    <input type="hidden" name="tipoCuentaSeleccionada" value="<%= request.getAttribute("tipoCuentaSeleccionada") != null ? request.getAttribute("tipoCuentaSeleccionada") : "" %>">
                </p>
                <p><strong>Cantidad de Cuotas:</strong> 
                    <span id="modalCuotas">
                        <%= request.getAttribute("Coutas") != null ? request.getAttribute("Coutas") : "No seleccionado" %>
                    </span>
                    <input type="hidden" name="Coutas" value="<%= request.getAttribute("Coutas") != null ? request.getAttribute("Coutas") : "" %>">
                </p>
                <p><strong>Importe pedido:</strong> $ 
                    <span id="modalImporete">
                        <%= request.getAttribute("Monto") != null ? request.getAttribute("Monto") : "0.00" %>
                    </span>
                    <input type="hidden" name="Monto" value="<%= request.getAttribute("Monto") != null ? request.getAttribute("Monto") : "0.00" %>">
                </p>
                <p><strong>Total a Pagar:</strong> $ 
                    <span id="modalTotal">
                        <%= request.getAttribute("total") != null ? request.getAttribute("total") : "0.00" %>
                    </span>
                    <input type="hidden" name="total" value="<%= request.getAttribute("total") != null ? request.getAttribute("total") : "0.00" %>">
                </p>
                <input type="hidden" name="btnConfirmar" value="true">
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
            const btnConfirmarInput = document.createElement('input');
            btnConfirmarInput.type = 'hidden';
            btnConfirmarInput.name = 'btnConfirmar';
            btnConfirmarInput.value = 'true';
            form.appendChild(btnConfirmarInput);
            
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


<!-- Modal ACTUAL DE ERROR -->

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
    title: "Solicitud Aceptada!",
    text: "Haga clic en el botón",
    icon: "success",
    confirmButtonText: "Aceptar",
    customClass: {
        confirmButton: "btn btn-success"
    }
});

</script>
	<%}%>


<script type="text/javascript"> window.onbeforeunload = function () { window.location.reload(); } </script>
</body>
</html>