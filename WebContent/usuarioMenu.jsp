<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Usuario" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Incluye AdminLTE CSS -->
    <link href="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/css/adminlte.min.css" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini layout-fixed">

<!-- Wrapper que contiene todo -->
<div class="wrapper">
    <!-- Sidebar -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <a href="usuarioHome.jsp" class="brand-link">
            <img src="Imagenes/logo_utn_blanco.png" alt="logo utn" class="img-fluid" style="max-width: 22px; vertical-align: middle;">
            <span class="brand-text font-weight-light">UTN Banking</span>
        </a>
        <div class="sidebar">
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu">
                    <li class="nav-item">
                        <a href="usuarioHome.jsp" class="nav-link">
                            <i class="nav-icon fas fa-tachometer-alt"></i>
                            <p>Inicio</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="ServletPagoPrestamos" class="nav-link">
                            <i class="nav-icon fas fa-hand-holding-usd"></i>
                            <p>Préstamos</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="ServletTransferencias" class="nav-link">
                            <i class="nav-icon fas fa-exchange-alt"></i>
                            <p>Transferencias</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="ServletMovimiento" class="nav-link">
                            <i class="nav-icon fas fa-file-invoice-dollar"></i>
                            <p>Movimientos</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="ServletEstadoPrestamos" class="nav-link">
                            <i class="nav-icon fas fa-check-circle"></i>
                            <p>Estado de Préstamos</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="ServletInfoUsuario" class="nav-link">
                            <i class="nav-icon fas fa-user"></i>
                            <p>Información de Usuario</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="InicioSesion.jsp" class="nav-link">
                            <i class="nav-icon fas fa-sign-out-alt"></i>
                            <p>Cerrar sesión</p>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </aside>

    
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/js/adminlte.min.js"></script>

</body>
</html>
