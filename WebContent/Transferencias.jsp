<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="usuarioNavbar.jsp" %>
    <%@ page import="java.util.List"%>
<%@ page import="entidades.Cuenta"%>
     
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <title>Realizar Transferencia</title>
</head>
<body class="bg-light">
 <!-- Mostrar mensaje de éxito o error usando Bootstrap -->
    <c:if test="${not empty mensaje}">
        <div class="alert alert-${tipoMensaje} alert-dismissible fade show mt-3" role="alert">
            ${mensaje}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

<div class="container mt-5">
    <div class="card text-bg-dark shadow-lg" style="max-width: 400px; margin: auto; border-radius: 8px;">
        <div class="card-body">
            <h5 class="card-title text-center">Realizar Transferencia</h5>
            <form action="ServletTransferencias" method="post">
                
            

 <!-- Desplegable para seleccionar la cuenta de origen -->
                    <div class="mb-3">
                        <label for="cbuOrigen" class="form-label">Seleccionar Cuenta de Origen</label>
                           <select class="form-select" id="cbuOrigen" name="nroCuentaOrigen" required>
                       
                            <option value="" disabled selected>Seleccione una cuenta</option>

                            <% 
                                List<Cuenta> cuentasDisponibles = (List<Cuenta>) session.getAttribute("cuentasDisponibles");
                                if (cuentasDisponibles != null && !cuentasDisponibles.isEmpty()) {
                                    for (Cuenta cuenta : cuentasDisponibles) {
                            %>
                                <option value="<%= cuenta.getNroCuenta() %>">
                                    <%= cuenta.getNroCuenta() %> - <%= cuenta.getSaldo() %> ARS
                                </option>
                            <% 
                                    }
                                } else {
                            %>
                                <option value="" disabled>No hay cuentas disponibles</option>
                            <% 
                                }
                            %>
                        </select>
                    </div>




                <div class="mb-3">
                    <label for="cbuDestino" class="form-label">CBU Destino</label>
                    <input type="text" class="form-control" id="cbuDestino" name="cbuDestino" required>
                </div>

                <div class="mb-3">
                    <label for="monto" class="form-label">Monto a Transferir</label>
                    <input type="number" step="0.01" class="form-control" id="monto" name="monto" required>
                </div>

                <div class="d-grid">
                    <button type="submit" class="btn btn-outline-light btn-sm">Transferir</button>
                </div>

            </form>
        </div>
    </div>
</div>

<!-- JavaScript de Bootstrap 5 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>