<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="usuarioMenu.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Incluye AdminLTE CSS -->
    <link href="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/css/adminlte.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    
    <title>Dashboard - Home</title>
    
    <style>
        /* General body and fonts */
        body {
            font-family: 'Poppins', sans-serif;
            height: 100vh;
            margin: 0;
            background-color: #f4f6f9;
        }

        /* Header */
        .brand-link {
            font-size: 1.5rem;
            color: #ffffff;
            font-weight: 600;
            text-transform: uppercase;
            padding: 15px 20px;
        }

        /* Sidebar */
        .main-sidebar {
            background-color: #343a40;
        }
        .sidebar-menu a {
            color: #b8c7ce;
            font-size: 1.1rem;
            padding: 15px;
            border-radius: 5px;
            margin: 5px 0;
        }
        .sidebar-menu a:hover {
            background-color: #495057;
            color: #ffffff;
        }

        /* Content Wrapper */
        .content-wrapper {
            margin-top: 60px; /* space for the navbar */
            padding: 30px;
        }

        /* Card Styles */
        .card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border-radius: 10px;
            background-color: #f1c40f;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: relative;
            padding-top: 30px;
            margin-right: 20px;
            flex: 0 0 30%; /* Makes cards take 30% of the available space */
        }
        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
        }

        /* Add folder-like effect */
        .card:before {
            content: '';
            position: absolute;
            top: -10px;
            left: 0;
            width: 100%;
            height: 15px;
            background-color: #f39c12;
            border-radius: 5px 5px 0 0;
        }

        .icon-container {
            font-size: 2.5rem;
            margin-bottom: 10px;
            color: #007bff;
        }

        /* Buttons */
        .btn-custom {
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            font-weight: 600;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }

        /* Footer */
        .footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #343a40;
            color: #b8c7ce;
            text-align: center;
            padding: 10px;
        }

        /* Flexbox container for cards */
        .card-container {
            display: flex;
            justify-content: center;
            gap: 20px; /* Adds space between the cards */
        }
        
       
        
          /* Estilos para el contenedor del título */
        .welcome-container {
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #f8f9fa;
            padding: 20px;
           
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

		/* Título de la tarjeta */
    .card-title {
        font-size: 1.4rem;
        font-weight: bold;
        color: #2c3e50;
        margin-bottom: 10px;
        float:none;
    }

    /* Descripción */
    .card-text {
        font-size: 1rem;
        color: #555;
        flex-grow: 1;
        padding: 0 10px;
    }
		

        /* Estilo para el ícono y el texto */
        .welcome-title {
            font-family: 'Poppins', sans-serif;
            font-weight: 600;
            color: #2c3e50;
            margin-left: 15px;
            font-size: 2rem;
        }

        .welcome-title span {
            color: #007bff;
        }

        /* Estilo para el ícono de usuario */
        .welcome-icon {
            font-size: 2.5rem;
            color: #007bff;
        }
    </style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">

  <!-- Contenedor para el título y el ícono de usuario -->
    <div class="welcome-container">
        <i class="fas fa-user welcome-icon"></i>
        <h1 class="welcome-title">
            Bienvenido, 
            <%
                // Verifica si la sesión está activa
                request.getSession(false);
                if (session != null) {
                    // Obtén el usuario de la sesión
                    Usuario usuario = (Usuario) session.getAttribute("usuario");
                    if (usuario != null) {
                        // Muestra el nombre del usuario
                        out.print("<span>" + usuario.getNombre() + "</span>");
                    } else {
                        out.print("<span>Usuario desconocido</span>");
                    }
                } else {
                    out.print("<span>Usuario desconocido</span>");
                }
            %>!
        </h1>
    </div>
    <div class="wrapper">

        <!-- Content Wrapper -->
        <div class="content-wrapper">
            <div class="container-fluid">

                </h1>

                <!-- Contenedor de tarjetas alineadas -->
                <div class="card-container">
                    <!-- Card: Préstamos -->
                    <div class="card text-center shadow-lg">
                        <div class="card-body">
                            <div class="icon-container">
                                <i class="fas fa-hand-holding-usd"></i>
                            </div>
                            <h5 class="card-title">Préstamos</h5>
                            <p class="card-text">Solicite y gestione sus préstamos de manera rápida y segura.</p>
                            <a href="ServletPagoPrestamos" class="btn btn-custom btn-sm">Gestionar préstamos</a>
                            <a href="ServletClientePrestamos" class="btn btn-outline-primary btn-sm mt-2">Solicitar préstamo</a>
                        </div>
                    </div>

                    <!-- Card: Transferencias -->
                    <div class="card text-center shadow-lg">
                        <div class="card-body">
                            <div class="icon-container">
                                <i class="fas fa-exchange-alt"></i>
                            </div>
                            <h5 class="card-title">Transferencias</h5>
                            <p class="card-text">Realice transferencias seguras entre cuentas.</p>
                            <a href="ServletTransferencias" class="btn btn-custom btn-sm">Realizar transferencia</a>
                        </div>
                    </div>

                    <!-- Card: Movimientos -->
                    <div class="card text-center shadow-lg">
                        <div class="card-body">
                            <div class="icon-container">
                                <i class="fas fa-file-invoice-dollar"></i>
                            </div>
                            <h5 class="card-title">Movimientos</h5>
                            <p class="card-text">Consulte los movimientos de su cuenta de manera sencilla.</p>
                            <a href="ServletMovimiento" class="btn btn-custom btn-sm">Consultar Movimientos</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p>&copy; 2025 UTN Home Banking. Todos los derechos reservados.</p>
        </div>

    </div>

    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/js/adminlte.min.js"></script>

</body>
</html>
