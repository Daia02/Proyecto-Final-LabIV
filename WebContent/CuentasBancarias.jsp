<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="dao.CuentaDao"%>
<%@page import="entidades.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="adminNavbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Listado de Cuentas</title>
  
  <!-- Incluye Bootstrap CSS primero -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
  
  <!-- DataTables CSS -->
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
   
  <!-- Incluir SweetAlert2 CSS -->
  <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
   
  <style type="text/css">
    .btn-container {
        display: flex;  /* Esto hace que los botones estén en fila */
        gap: 10px;      /* Espacio entre los botones */
        justify-content: center; /* Centra los botones */
    }
  </style>
</head>
<body class="bg-light">

  <br/><br/><br/>

  <% 
    ArrayList<Cuenta> listaCuentas = null;
    if(request.getAttribute("listaCuentas") != null) {
        listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
    }
  %>

  <div class="container mt-4 mb-4">
    <div class="table-container">
      <h2 class="text-center mb-4">Lista de Cuentas</h2>
      
  <%
  String mensaje = request.getParameter("mensaje");
  if (mensaje != null) {
%>
  <div class="alert alert-info" id="mensajeAlert">
    <%= mensaje %>
  </div>

  <script>
    // Asegurarnos de que el DOM esté completamente cargado antes de ejecutar el código
    document.addEventListener('DOMContentLoaded', function() {
      setTimeout(function() {
        var mensajeDiv = document.getElementById('mensajeAlert');
        if (mensajeDiv) {
          mensajeDiv.style.display = 'none';
        }
      }, 3000);  // 3000 ms = 3 segundos
    });
  </script>
<% 
  }
%>
  
  <div>
    <table id="tablaCuentas" class="table table-bordered table-striped table-hover mb-4">
      <thead class="table-dark">
        <tr>
          <th>Nro Cuenta</th>
          <th>CUIL</th>
          <th>Tipo Cuenta</th>
          <th>Fecha de Creación</th>
          <th>CBU</th>
          <th>Saldo</th>
          <th>Estado</th>
          <th>Acciones</th> <!-- Nueva columna -->
        </tr>
      </thead>
      <tbody>
        <% 
          if (listaCuentas != null) {
              for (Cuenta cuenta : listaCuentas) {
        %>
          <tr>
            <td><%= cuenta.getNroCuenta() %></td>
            <td><%= cuenta.getCuilCu() %></td>
            <td><%= cuenta.getNroTipoCuenta() %></td>
            <td><%= cuenta.getFechaCreacion() %></td>
            <td><%= cuenta.getCbu() %></td>
            <td><%= cuenta.getSaldo() %></td>
            <td><%= cuenta.getActivo() ? "Activo" : "Inactivo" %></td>
            <td>
              <div class="btn-container">
                <!-- Botón Editar con icono -->
                <form action="ServletModificarCuentas" method="get">
                  <input type="hidden" name="action" value="editCuenta" />
                  <input type="hidden" name="NroCuenta" value="<%=cuenta.getNroCuenta()%>" />
                  <button type="submit" class="btn btn-sm btn-warning">
                    <i class="fas fa-edit"></i>
                  </button>
                </form>

                <!-- Botón Eliminar con icono -->
                <form action="ServletBajaCuentas" method="get" onsubmit="return confirmarEliminacion(event)">
                  <input type="hidden" name="action" value="deleteCuenta" />
                  <input type="hidden" name="NroCuenta" value="<%=cuenta.getNroCuenta()%>" />
                  <button type="submit" class="btn btn-sm btn-danger">
                    <i class="fas fa-trash-alt"></i>
                  </button>
                </form>
              </div>
            </td>
          </tr>
        <%  
          }
          } else {
        %>
          <tr>
            <td colspan="8" class="text-center">No hay cuentas disponibles</td>
          </tr>
        <% 
          }
        %>
      </tbody>
    </table>
  </div>
</div>
</div>

<!-- JavaScript de Bootstrap 5 y jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

<!-- Incluir SweetAlert2 JS -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>

<!-- Inicialización de DataTable -->
<script type="text/javascript">
  $(document).ready(function() {
    $('#tablaCuentas').DataTable({
        "language": {
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registros disponibles",
            "infoFiltered": "(filtrado de _MAX_ registros totales)",
            "paginate": {
                "first": "Primero",
                "last": "Último",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        },
        "searching": false,
        "scrollX": true,
        "autoWidth": false,
        "dom": '<"top"f>rt<"bottom"ip><"clear">',
        "lengthMenu": [ 10, 25, 50, 75, 100 ],
        "pageLength": 10
    });
  });

  function confirmarEliminacion(event) {
    event.preventDefault();  // Evita que el formulario se envíe inmediatamente

    Swal.fire({
      title: '¿Estás seguro?',
      text: "¡No podrás revertir esta acción!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        // Si el usuario confirma, enviamos el formulario
        event.target.submit();
      }
    });
  }
</script>

</body>
</html>
