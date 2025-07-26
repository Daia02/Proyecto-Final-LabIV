<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="entidades.PagoPrestamo"%>
<%@ include file="usuarioNavbar.jsp"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pago de Préstamos</title>

    <!-- Incluye Bootstrap CSS -->
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
        .dataTables_wrapper .dataTables_length, .dataTables_wrapper .dataTables_filter {
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
        
		/* Estilos para los labels */
		.form-control-plaintext {
		    border: none;
		    background-color: transparent;
		    padding: 0;
		    font-weight: bold;
		    text-align: right;
		}
		
		.font-weight-bold {
		    font-weight: bold;
		}
		
		.table-dark {
		    color: #ffffff;
		    background-color: #343a40;
		}
		
        
    </style>
    
    <script type="text/javascript">
    $(document).ready(function() {
        $('#tablaPrestamos').DataTable({
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
            "scrollX": true,
            "autoWidth": false,
            "dom": '<"top"f>rt<"bottom"ip><"clear">'
        });
    });
</script>
    
</head>
<body class="bg-light">

<% if (request.getAttribute("mensaje") != null) { %>
    <div class="alert alert-success">
        <%= request.getAttribute("mensaje") %>
    </div>
<% } %>

<% if (request.getAttribute("mensajeError") != null) { %>
    <div class="alert alert-danger">
        <%= request.getAttribute("mensajeError") %>
    </div>
<% } %>


<form action="ServletPagoPrestamos" method="post">
    <div class="container mt-4 mb-4">
    
        <div class="table-container">
            <h2 class="text-center mb-4">Pago de Préstamos</h2>      
            <a href="ServletClientePrestamos" class="btn btn-link mb-4">Solicitar Préstamo</a>
			<!-- Contenedor de información de saldos -->
			<div class="d-flex justify-content-start mb-4">
			    <table class="table table-bordered table-sm w-auto">
			        <thead class="table-dark">
			            <tr>
			                <th colspan="2">Sus saldos</th>
			            </tr>
			        </thead>
			        <tbody>
			            <%
			                String saldoCuentaCorriente = (String) request.getAttribute("saldoCuentaCorriente");
			                String saldoCajaAhorro = (String) request.getAttribute("saldoCajaAhorro");
			            %>
			            <% if (saldoCuentaCorriente != null && !saldoCuentaCorriente.isEmpty()) { %>
			                <tr id="cuentaCorrienteRow">
			                    <td class="font-weight-bold">Cuenta corriente:</td>
			                    <td>
			                        <label class="d-inline-block" style="width: 80px;"><%= saldoCuentaCorriente %></label>
			                    </td>
			                </tr>
			            <% } %>
			            <% if (saldoCajaAhorro != null && !saldoCajaAhorro.isEmpty()) { %>
			                <tr id="cajaAhorroRow">
			                    <td class="font-weight-bold">Caja de ahorro:</td>
			                    <td>
			                        <label class="d-inline-block" style="width: 80px;"><%= saldoCajaAhorro %></label>
			                    </td>
			                </tr>
			            <% } %>
			        </tbody>
			    </table>
			</div>
            <div class="table-responsive">
                <table id="tablaPrestamos" class="table table-bordered table-striped table-hover mb-4">
                    <thead class="table-dark">
                        <tr>
                            <th>Nro. Préstamo</th>
                            <th>Tipo de Cuenta</th>
                            <th>Importe Aprobado</th>
                            <th>Total a Pagar</th>
                            <th>Plazo de Pago</th>
                            <th>Interés Aplicado</th>
                            <th>Cantidad de Cuotas</th>
                            <th>Cuotas Restantes</th>
                            <th>Fecha de Aprobación</th>
                            <th>Fecha de Vencimiento</th>
                            <th>Monto por Cuota</th>
                            <th>Estado</th>
                            <th>Seleccionar Cuota</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<PagoPrestamo> prestamos = (List<PagoPrestamo>) request.getAttribute("prestamos");
                            boolean hayPrestamos = (boolean) request.getAttribute("hayPrestamos");
                            if (prestamos != null && !prestamos.isEmpty()) {
                                for (PagoPrestamo prestamo : prestamos) {
                                    if (prestamo.getCuotasRestantes() > 0) {
                        %>
                        <tr>
                            <td><%=prestamo.getNroPrestamo()%></td>
                            <td><%=prestamo.getTipoCuenta()%></td>
                            <td><%=prestamo.getImporteAprobado()%></td>
                            <td><%=prestamo.getTotalAPagar()%></td>
                            <td><%=prestamo.getPlazoDePago()%></td>
                            <td><%=prestamo.getInteresAplicado()%></td>
                            <td><%=prestamo.getCantidadCuotas()%></td>
                            <td><%=prestamo.getCuotasRestantes()%></td>
                            <td><%=prestamo.getFechaAprobacion()%></td>
                            <td><%=prestamo.getFechaVencimiento()%></td>
                            <td><%=prestamo.getMontoPorCuota()%></td>
                            <td><%=prestamo.getEstado()%></td>
                            <td>
                                <input type="radio" name="nroPrestamo" value="<%=prestamo.getNroPrestamo()%>" required>
                                <input type="hidden" name="montoCuota_<%=prestamo.getNroPrestamo()%>" value="<%=prestamo.getMontoPorCuota()%>">
                            </td>
                        </tr>
                        <%
                                }
                            }
                        }
                        %>
                        <tr id="sinPrestamos" style="display: <%=hayPrestamos ? "none" : "table-row"%>;">
                            <td colspan="13" class="text-center">No hay préstamos registrados.</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <!-- Controles de paginación -->
            <div class="dataTables_wrapper">
                <div class="dataTables_length" id="tablaPrestamos_length"></div>
                <div class="dataTables_filter" id="tablaPrestamos_filter"></div>
                <div class="dataTables_info" id="tablaPrestamos_info"></div>
                <div class="dataTables_paginate paging_simple_numbers" id="tablaPrestamos_paginate"></div>
            </div>

            <%
                if (hayPrestamos) {
            %>
            <button type="submit" id="btnAceptar" class="btn btn-primary mt-3">Aceptar</button>
            <%
                }
            %>
            <a href="usuarioHome.jsp" class="btn btn-secondary mt-3">Volver a Inicio</a>
        </div>
    </div>
</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>



</body>
</html>