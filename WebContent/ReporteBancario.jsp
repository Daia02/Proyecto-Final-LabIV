<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminNavbar.jsp" %>
<!DOCTYPE html>
<html lang="es"> 
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Generar Informe de Alta de Cuentas Nuevas</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" crossorigin="anonymous">
    
    <style>
        body {
            background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url(Imagenes/fondo_utn.jpg);
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            color: white;
        }
        .container-custom {
            max-width: 600px;
            background-color: #343a40;
            border-radius: 10px;
            padding: 20px;
            margin-top: 5%;
        }
    </style>
</head>
<body>

<div class="container-fluid p-3 mb-2 text-dark">
    <div class="row justify-content-center">
        <div class="col-4 p-4 bg-dark text-white rounded">
            <h3 class="text-center">Generar Reporte de Alta de Cuentas Nuevas</h3>
            <form action="servletReporte" method="post">
                <div class="form-group">
                    <label for="startDate">Fecha de inicio:</label>
                    <input required type="date" class="form-control" id="startDate" name="startDate">
                </div>
                <div class="form-group">
                    <label for="endDate">Fecha de fin:</label>
                    <input required type="date" class="form-control" id="endDate" name="endDate">
                </div>
              
                <button type="submit" class="btn btn-primary w-100">Generar Reporte</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
