<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entidades.Cuenta, entidades.TipoCuenta" %>
<%@ page import="java.util.List" %>
<%@ include file="adminNavbar.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <title>Editar Cuenta</title>
    
    <style type="text/css">
        .btn-container {
            display: flex;
            justify-content: center;
            gap: 5px;
        }
    </style>
</head>
<body class="bg-light">
    <% 
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
    %>
    <div class="alert alert-info text-center" role="alert">
        <%=mensaje%>
    </div>
    <% } %>

    <!-- Mostrar mensaje de error si existe -->
    <%
        if (request.getAttribute("error") != null) {
    %>
    <div class="alert alert-danger" role="alert">
        <%=request.getAttribute("error")%>
    </div>
    <% } %>

    <div class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent;">
        <div class="row justify-content-center">
            <div class="col-4 p-4">
                <a href="servletCuentaBancaria?Param=1" class="btn btn-secondary w-100 mt-2">Volver a la tabla de cuentas</a>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-4 p-4 mb-3 bg-dark text-white rounded">
                <h3 class="text-center">Editar Cuenta</h3>
                <%
                    Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");
                    List<TipoCuenta> tiposCuenta = (List<TipoCuenta>) request.getAttribute("tiposCuenta");
                %>
                <form action="ServletModificarCuentas" method="post">
                    <input type="hidden" name="accion" value="editar">
                    <input type="hidden" name="nroCuenta" value="<%= cuenta.getNroCuenta() %>">

                    <div class="form-group mb-3">
                        <label for="cuilCu">CUIL:</label>
                        <input type="text" class="form-control" id="cuilCu" name="cuilCu" value="<%= cuenta.getCuilCu() %>" required>
                    </div>

                    <div class="form-group mb-3">
                        <label for="nroTipoCuenta">Tipo de Cuenta:</label>
                        <select class="form-control" id="nroTipoCuenta" name="nroTipoCuenta" required>
                            <% for (TipoCuenta tipo : tiposCuenta) { %>
                                <option value="<%= tipo.getNroTipoCuenta() %>" <%= (tipo.getNroTipoCuenta() == cuenta.getNroTipoCuenta()) ? "selected" : "" %>>
                                    <%= tipo.getTipoCuenta() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group mb-3">
                        <label for="fechaCreacion">Fecha de Creación:</label>
                        <input type="date" class="form-control" id="fechaCreacion" name="fechaCreacion" value="<%= cuenta.getFechaCreacion() %>" readonly>
                    </div>

                    <div class="form-group mb-3">
                        <label for="cbu">CBU:</label>
                        <input type="text" class="form-control" id="cbu" name="cbu" value="<%= cuenta.getCbu() %>" required>
                    </div>

                    <div class="form-group mb-3">
                        <label for="saldo">Saldo:</label>
                        <input type="number" class="form-control" id="saldo" name="saldo" step="0.01" value="<%= cuenta.getSaldo() %>" required>
                    </div>

                    <div class="btn-container">
                        <button type="submit" name="btnAceptar" class="btn btn-warning w-100 mt-2">Actualizar</button>
                        <a href="servletCuentaBancaria?Param=1" class="btn btn-secondary w-100 mt-2">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- JavaScript de Bootstrap 5 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
