<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <title>Perfil de Usuario</title>
    <style>
        /* Estilos generales */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f7f7f7;
        }

   

        .navbar-brand img {
            max-width: 40px;
        }

        .navbar-nav .nav-item .nav-link {
            color: #ffffff;
        }

        .navbar-nav .nav-item .nav-link:hover {
            color: #ff8c00;
        }

        /* Estilos del perfil */
        .profile-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .profile-header {
            background-color: #343a40;
            color: #ff8c00;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 30px;
            text-align: center;
        }

        .profile-header h3 {
            margin: 0;
        }

        .profile-info {
            margin-top: 20px;
        }

        .profile-info h5 {
            margin-bottom: 15px;
            font-size: 1.25rem;
            font-weight: 600;
        }

        .table th, .table td {
            text-align: left;
            padding: 12px;
            font-size: 1rem;
        }

        .table th {
            background-color: #343a40;
            color: white;
        }

        .table td {
            background-color: #f1f1f1;
        }

        .table-striped tbody tr:nth-child(odd) {
            background-color: #e9ecef;
        }

        .table-bordered {
            border: 1px solid #ddd;
        }

        .btn-custom {
            background-color: #ff8c00;
            color: white;
            padding: 10px 20px;
            font-size: 1rem;
            border-radius: 5px;
            text-decoration: none;
        }

        .btn-custom:hover {
            background-color: #cc7000;
            text-decoration: none;
        }
    </style>
</head>
<body>

<!-- PARTE SUPERIOR -->
<div class="container-fluid p-3 mb-2 bg-dark text-light">
    <div class="row">
        <div class="col-6">
            <nav class="navbar navbar-expand-lg navbar-dark ">
                <a href="Home.jsp" class="navbar-brand">
                    <img src="Imagenes/logo_utn_blanco.png" class="img-fluid" style="max-width: 22px; vertical-align: middle;" alt="logo utn">
                </a>
                
                
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

             
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="clientesDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Usuario
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="clientesDropdown">
                                <li><a class="dropdown-item" href="">Inicio</a></li>
                                <li><a class="dropdown-item" href="Alta-Clientes.jsp">Alta de Clientes</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>

<!-- Perfil de Usuario -->
<div class="container mt-4">
    <div class="profile-container">
        <div class="profile-header">
            <h3>Perfil de Usuario</h3>
        </div>

        <div class="profile-info">
            <h5>Datos Personales</h5>
            <!-- Información del Usuario -->
            <table class="table table-striped table-bordered">
                <tbody>
                    <tr>
                        <th>Nombre Completo:</th>
                        <td>Juan Pérez</td>
                    </tr>
                    <tr>
                        <th>DNI:</th>
                        <td>12345678</td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td>juan.perez@banco.com</td>
                    </tr>
                    <tr>
                        <th>Teléfono:</th>
                        <td>+54 9 11 2345-6789</td>
                    </tr>
                    <tr>
                        <th>Dirección:</th>
                        <td>Av. Siempre Viva 123, Ciudad Autónoma de Buenos Aires, Argentina</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Botón para editar perfil -->
        <div class="text-center mt-4">
            <a href="editarPerfil.jsp" class="btn btn-custom">Editar Información</a>
        </div>
    </div>
</div>

<!-- JavaScript de Bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>
    