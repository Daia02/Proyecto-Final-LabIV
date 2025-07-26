<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="entidades.PagoPrestamo"%>
<%@ include file="usuarioNavbar.jsp"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Confirmar Pago</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
    .btn-container {
		display: flex;
		justify-content: center;
		gap: 5px;
	}
    </style>
</head>
<body class="bg-light">

	<div class="container mt-3">
    <%
        if (request.getAttribute("mensajeExito") != null) {
    %>
    <div class="alert alert-success" role="alert">
        <%=request.getAttribute("mensajeExito")%>
    </div>
    <%
        }
    %>
    <%
        if (request.getAttribute("mensajeError") != null) {
    %>
    <div class="alert alert-danger" role="alert">
        <%=request.getAttribute("mensajeError")%>
    </div>
    <% } %>
	</div>

    <div class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent;">
        <div class="row justify-content-center">
            <div class="col-4 p-4">
                <a href="ServletPagoPrestamos" id="volverPagos"> Volver a la lista de préstamos </a>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-4 p-4 mb-3 bg-dark text-white rounded">
                <h3 class="text-center">Confirmar Pago</h3>
                <form action="ServletConfirmarPago" method="post">
                    <div class="mb-3">
                        <label for="cuentaSeleccionada" class="form-label">Seleccionar Cuenta</label>
                        <select class="form-select" id="cuentaSeleccionada" name="nroCuenta" required>
                            <option value="">Seleccione una cuenta</option>
                            <%
                                List<Map<String, String>> cuentas = (List<Map<String, String>>) request.getAttribute("cuentas");
                                if (cuentas != null) {
                                    for (Map<String, String> cuenta : cuentas) {
                            %>
                            <option value="<%= cuenta.get("Nro_cuenta") %>"><%= cuenta.get("Nro_cuenta") %> - <%= cuenta.get("Tipo_de_cuenta") %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="montoCuota" class="form-label">Importe de la Cuota</label>
                        <input type="text" class="form-control" id="montoCuota" name="montoCuota" value="<%= request.getAttribute("montoCuota") %>" readonly>
                    </div>
                    <input type="hidden" id="hiddenNroPrestamo" name="nroPrestamo" value="<%= request.getAttribute("nroPrestamo") %>">
                    <div class="modal-footer">
                    <div class="btn-container">
                    <button type="submit" class="btn btn-primary">Confirmar</button>
                        <a href="ServletPagoPrestamos" class="btn btn-secondary">Cancelar</a>
                    </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
 
</body>
</html>