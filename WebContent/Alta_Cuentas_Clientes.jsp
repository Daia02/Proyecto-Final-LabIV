<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="entidades.TipoCuenta" %>
<%@ include file="adminNavbar.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <title>Alta Cuenta</title>
</head>
<body class="bg-light">
    <div class="container-fluid p-3 mb-2 text-dark" style="background-color: transparent;">
        <div class="row justify-content-center">
            <div class="col-4 p-4 mb-3 bg-dark text-white rounded">
                <h3 class="text-center">Alta Cuenta</h3>
                
                <%-- Mensaje de resultado --%>
                <% String mensaje = (String) request.getAttribute("mensaje");
                   if (mensaje != null) { %>
                <div class="alert alert-info text-center" role="alert">
                    <%= mensaje %>
                </div>
                <% } %>
                
                <form action="ServletAltaCuenta" method="post">
                    <input type="hidden" name="param" value="1">
                    <div class="form-group mb-3">
                        <label for="cuil">Seleccione un CUIL:</label>
                        <select required id="cuil" class="form-control" name="selectCuil">
                            <option value="0" disabled selected>Seleccione un CUIL</option>
                            <% List<String> cuiles = (List<String>) request.getAttribute("cuiles");
                               if (cuiles != null) {
                                   for (String cuil : cuiles) { %>
                            <option value="<%= cuil %>"><%= cuil %></option>
                            <% } } else { %>
                            <option value="0" disabled>No hay CUILs disponibles</option>
                            <% } %>
                        </select>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="fechaCreacion">Fecha de Creación:</label>
                        <input type="text" class="form-control" id="fechaCreacion" name="txtFechaCreacion" value="<%= LocalDate.now() %>" readonly>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="tipoCuenta">Tipo de Cuenta:</label>
                        <select required id="tipoCuenta" class="form-control" name="selectTipoCuenta">
                            <option value="0" disabled selected>Seleccione un tipo de cuenta</option>
                            <% 
                                List<TipoCuenta> tiposCuenta = (List<TipoCuenta>) request.getAttribute("tiposCuenta");
                                if (tiposCuenta != null) {
                                    for (TipoCuenta tipo : tiposCuenta) { 
                            %>
                            <option value="<%= tipo.getNroTipoCuenta() %>"><%= tipo.getTipoCuenta() %></option>
                            <% 
                                    }
                                } else { 
                            %>
                            <option value="0" disabled>No hay tipos de cuenta disponibles</option>
                            <% } %>
                        </select>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="nroCuenta">Número de Cuenta:</label>
                        <input type="text" id="nroCuenta" class="form-control" name="nroCuenta" value="${ultimoIDCuenta}" readonly>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="cbu">CBU:</label>
                        <input type="text" class="form-control" id="cbu" name="txtCBU" required placeholder="Ingrese el CBU" required>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="saldo">Saldo Inicial:</label>
                        <input type="text" class="form-control" id="saldo" name="txtSaldo" value="10000" readonly>
                    </div>
                    
                    <button type="submit" name="btnAceptar" class="btn btn-primary w-100">Aceptar</button>
                    
                    <%-- Botón Volver al menú principal --%>
                    <a href="adminHome.jsp" class="btn btn-secondary w-100 mt-3">Volver al Menú Principal</a>
                </form>
            </div>
        </div>
    </div>
    
    <!-- JavaScript de Bootstrap 5 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
