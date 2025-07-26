<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminNavbar.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css">

    <title>Home</title>

    <style>
        /* Efecto de elevación al pasar el mouse */
        .card {
            transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
        }
        .card:hover {
            transform: translateY(-8px);
            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.3);
        }

        /* Alineación uniforme de botones */
        .card-body {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 100%;
        }

        .btn-group {
            display: flex;
            flex-direction: column;
            gap: 8px;
            margin-top: auto;
        }
    </style>
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex flex-wrap justify-content-center gap-4">
        
        <!-- Card: Clientes -->
        <div class="card text-bg-dark shadow-lg" style="width: 18rem; border-radius: 8px;">
            <div class="card-body text-center">
                <i class="bi bi-people-fill fs-1 mb-2"></i>
                <h5 class="card-title">Clientes</h5>
                <h6 class="card-subtitle mb-3 text-muted">ABML de clientes</h6>
                <p class="card-text">Gestiona los registros de la tabla clientes: listar, dar de alta, modificar o dar de baja.</p>
                <div class="btn-group">
                    <a href="ServletListarClientes" class="btn btn-outline-light btn-sm">Tabla de clientes</a>
                    <a href="ServletAltaCliente" class="btn btn-outline-light btn-sm">Dar de alta clientes</a>
                </div>
            </div>
        </div>

        <!-- Card: Préstamos -->
        <div class="card text-bg-dark shadow-lg" style="width: 18rem; border-radius: 8px;">
            <div class="card-body text-center">
                <i class="bi bi-cash-coin fs-1 mb-2"></i>
                <h5 class="card-title">Préstamos</h5>
                <h6 class="card-subtitle mb-3 text-muted">Aprobación</h6>
                <p class="card-text">Aprobaciones y rechazo de préstamos.</p>
                <div class="btn-group">
                    <a href="ServeltAdminPrestamos" class="btn btn-outline-light btn-sm">Solicitudes Pendientes</a>
                </div>
            </div>
        </div>

        <!-- Card: Cuentas Bancarias -->
        <div class="card text-bg-dark shadow-lg" style="width: 18rem; border-radius: 8px;">
            <div class="card-body text-center">
                <i class="bi bi-bank fs-1 mb-2"></i>
                <h5 class="card-title">Cuentas Bancarias</h5>
                <p class="card-text">Visualice las cuentas de todos los usuarios.</p>
                <div class="btn-group">
                    <a href="ServletAltaCuenta" class="btn btn-outline-light btn-sm">Alta Cuentas</a>
                    <a href="servletCuentaBancaria?Param=1" class="btn btn-outline-light btn-sm">Ver Cuentas</a>
                </div>
            </div>
        </div>

        <!-- Card: Reporte -->
        <div class="card text-bg-dark shadow-lg" style="width: 18rem; border-radius: 8px;">
            <div class="card-body text-center">
                <i class="bi bi-bar-chart-fill fs-1 mb-2"></i>
                <h5 class="card-title">Reporte</h5>
                <p class="card-text">Reporte de cuentas creadas en un periodo de fecha.</p>
                <div class="btn-group">
                    <a href="ReporteBancario.jsp" class="btn btn-outline-light btn-sm">Ver Reporte</a>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- JavaScript de Bootstrap 5 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>
